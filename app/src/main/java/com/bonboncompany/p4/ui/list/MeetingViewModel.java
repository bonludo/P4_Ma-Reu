package com.bonboncompany.p4.ui.list;

import android.app.TimePickerDialog;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.bonboncompany.p4.data.MeetingRepository;
import com.bonboncompany.p4.data.model.Meeting;
import com.bonboncompany.p4.data.model.Room;
import com.bonboncompany.p4.ui.list.dialogfilter.RoomSpinnerDialog;
import com.bonboncompany.p4.util.SingleLiveEvent;

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

        meetingListMediatorLiveData.addSource(meetingListLiveData, new Observer<List<Meeting>>() {
            @Override
            public void onChanged(List<Meeting> meetings) {
                combine(meetings, currentlySelectedRoom.getValue());
            }
        });

        meetingListMediatorLiveData.addSource(currentlySelectedRoom, new Observer<Room>() {
            @Override
            public void onChanged(Room room) {
                combine(meetingListLiveData.getValue(), room);
            }
        });

//        meetingListMediatorLiveData.addSource(meetingListLiveData, new Observer<List<Meeting>>() {
//            @Override
//            public void onChanged(List<Meeting> meetings) {
//                combine2(meetings, chosenTimeSlot.getValue());
//            }
//        });
//
//        meetingListMediatorLiveData.addSource(chosenTimeSlot, new Observer<LocalTime>() {
//            @Override
//            public void onChanged(LocalTime time) {
//                combine2(meetingListLiveData.getValue(), time);
//            }
//        });
    }

    private void combine(@Nullable List<Meeting> meetings, @Nullable Room room) {

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

//    private void combine2(@Nullable List<Meeting> meetings, @Nullable LocalTime time) {
//        if (meetings == null) {
//            return;
//        }
//
//        List<MeetingViewStateItem> meetingViewStateItems = new ArrayList<>();
//        for (Meeting meeting : meetings) {
//            if (time == null || time.getHour() == meeting.getTime().getHour()) {
//                meetingViewStateItems.add(new MeetingViewStateItem(
//                                meeting.getId(),
//                                MeetingViewModel.this.getMeetingInfo(meeting),
//                                meeting.getParticipantMail(),
//                                meeting.getRoom().getColor()
//                        )
//                );
//            }
//        }
//        meetingListMediatorLiveData.setValue(meetingViewStateItems);
//    }

    public String getMeetingInfo(Meeting meeting) {
        String topic = meeting.getMeetingTopic();
        LocalTime hour = meeting.getTime();
        Room room = meeting.getRoom();

        return topic + " - " + hour + " - " + room;
    }

    public void onRoomChanged(Room room) {
        currentlySelectedRoom.setValue(room);
    }

    public void onHourChanged(LocalTime time) {
        chosenTimeSlot.setValue(time);
    }

    public void onDeleteMeetingClicked(long meetingId) { meetingRepository.deleteMeeting(meetingId); }

    public LiveData<List<MeetingViewStateItem>> getMeetingListLiveData() {
        return meetingListMediatorLiveData;
    }
}
