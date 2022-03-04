package com.bonboncompany.p4.ui.list;


import static android.content.res.Resources.getSystem;

import android.content.res.Resources;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bonboncompany.p4.R;
import com.bonboncompany.p4.data.MeetingRepository;
import com.bonboncompany.p4.data.model.Meeting;
import com.bonboncompany.p4.data.model.Room;
import com.bonboncompany.p4.util.App;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingViewModel extends ViewModel {


    private final MediatorLiveData<List<MeetingViewStateItem>> meetingListMediatorLiveData
            = new MediatorLiveData<>();
    private final MutableLiveData<Room> currentlySelectedRoom = new MutableLiveData<>();
    private final MutableLiveData<LocalTime> chosenTimeSlot = new MutableLiveData<>();
    private final MeetingRepository meetingRepository;


    public MeetingViewModel(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;

        LiveData<List<Meeting>> meetingListLiveData = meetingRepository.getMeetingsLiveData();

        meetingListMediatorLiveData.addSource(meetingListLiveData, meetings -> {
            combineRoom(meetings, currentlySelectedRoom.getValue());
            combineHour(meetings, chosenTimeSlot.getValue());
        });

        meetingListMediatorLiveData.addSource(currentlySelectedRoom, room -> combineRoom(meetingListLiveData.getValue(), room));
        meetingListMediatorLiveData.addSource(chosenTimeSlot, time -> combineHour(meetingListLiveData.getValue(), time));
    }

    public void combineRoom(@Nullable List<Meeting> meetings, @Nullable Room room) {

        if (meetings == null) {
            return;
        }

        List<MeetingViewStateItem> meetingViewStateItems = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (room == null || room == meeting.getRoom()) {
                meetingViewStateItems.add(new MeetingViewStateItem(
                                meeting.getId(),
                                MeetingViewModel.this.getMeetingInfo(meeting),
                                meeting.getParticipantMail(),
                                meeting.getRoom().getColor()
                        )
                );
            }
        }
        meetingListMediatorLiveData.setValue(meetingViewStateItems);
    }

    public void combineHour(@Nullable List<Meeting> meetings, @Nullable LocalTime time) {
        if (meetings == null) {
            return;
        }

        List<MeetingViewStateItem> meetingViewStateItems = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (time == null || time.getHour() == meeting.getTime().getHour()) {
                meetingViewStateItems.add(new MeetingViewStateItem(
                                meeting.getId(),
                                getMeetingInfo(meeting),
                                meeting.getParticipantMail(),
                                meeting.getRoom().getColor()
                        )
                );
            }
        }
        meetingListMediatorLiveData.setValue(meetingViewStateItems);
    }

    private String capitalize(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public String getMeetingInfo(Meeting meeting) {
//      works but not testable (nullpointerException)
//      String tiret = App.getContext().getString(R.string.tiret);
//      String hourS = App.getContext().getString(R.string.h_for_hour);
        String tiret = " _ ";
        String hourS = " h ";
        String topic = capitalize(meeting.getMeetingTopic());

        LocalTime hour = meeting.getTime();

        String room = capitalize(meeting.getRoom().getName());

        return topic + tiret + hour.getHour() + hourS + hour.getMinute() + hour.getMinute() + tiret + room;
    }

    public void onRoomChanged(Room room) {
        currentlySelectedRoom.setValue(room);
    }

    public void onHourChanged(LocalTime time) {
        chosenTimeSlot.setValue(time);
    }

    public void onDeleteMeetingClicked(long meetingId) {
        meetingRepository.deleteMeeting(meetingId);
    }

    public LiveData<List<MeetingViewStateItem>> getMeetingListLiveData() {
        return meetingListMediatorLiveData;
    }
}
