package com.bonboncompany.p4.ui.list;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.bonboncompany.p4.data.MeetingRepository;
import com.bonboncompany.p4.data.model.Meeting;
import com.bonboncompany.p4.data.model.Room;
import com.bonboncompany.p4.ui.list.dialogfilter.CustomRoomSpinnerDialog;
import com.bonboncompany.p4.ui.list.dialogfilter.CustomTimePickerDialog;

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
                combineRoom(meetings, currentlySelectedRoom.getValue());
                combineHour(meetings, chosenTimeSlot.getValue());
            }
        });

        meetingListMediatorLiveData.addSource(currentlySelectedRoom, new Observer<Room>() {
            @Override
            public void onChanged(Room room) {
                combineRoom(meetingListLiveData.getValue(), room);
            }
        });

        meetingListMediatorLiveData.addSource(chosenTimeSlot, new Observer<LocalTime>() {
            @Override
            public void onChanged(LocalTime time) {
                combineHour(meetingListLiveData.getValue(), time);
            }
        });

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
                                MeetingViewModel.this.getMeetingInfo(meeting),
                                meeting.getParticipantMail(),
                                meeting.getRoom().getColor()
                        )
                );
            }
        }
        meetingListMediatorLiveData.setValue(meetingViewStateItems);
    }

    public String getMeetingInfo(Meeting meeting) {

        String topic = meeting.getMeetingTopic().substring(0, 1).toUpperCase()
                + meeting.getMeetingTopic().substring(1).toLowerCase();

        LocalTime hour = meeting.getTime();

        String room = meeting.getRoom().toString().substring(0, 1).toUpperCase()
                + meeting.getRoom().toString().substring(1).toLowerCase();


        return topic + " - " + hour.getHour() + "h00" + " - " + room;
    }

    public void roomButtonClicked(Context context) {

        CustomRoomSpinnerDialog.MyRoomDialogListener listener = new CustomRoomSpinnerDialog.MyRoomDialogListener() {
            @Override
            public void userSelectedAValue(Room value) {
                Toast.makeText(context, "Réunion en salle "
                        + value.toString().toLowerCase(), Toast.LENGTH_LONG).show();
                onRoomChanged(value);
            }

        };
        CustomRoomSpinnerDialog dialog = new CustomRoomSpinnerDialog(context, listener);
        dialog.show();
    }

    public void timeButtonClicked(Context context) {
        CustomTimePickerDialog.MyTimeDialogListener listener = new CustomTimePickerDialog.MyTimeDialogListener() {
            @Override
            public void userSelectedAValue(LocalTime value) {
                Toast.makeText(context, "Réunion à " + value.getHour()
                        + " heure", Toast.LENGTH_LONG).show();
                onHourChanged(value);
            }
        };
        CustomTimePickerDialog dialog = new CustomTimePickerDialog(context, listener);
        dialog.show();
    }

    public void refreshButtonClicked(Context context) {
        onRoomChanged(null);
        onHourChanged(null);
        Toast.makeText(context, "Liste complète des réunions du jour", Toast.LENGTH_SHORT).show();
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
