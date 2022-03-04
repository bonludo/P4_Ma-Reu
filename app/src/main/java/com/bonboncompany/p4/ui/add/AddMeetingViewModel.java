package com.bonboncompany.p4.ui.add;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bonboncompany.p4.data.MeetingRepository;
import com.bonboncompany.p4.data.model.Room;
import com.bonboncompany.p4.util.SingleLiveEvent;

import java.time.LocalTime;

public class AddMeetingViewModel extends ViewModel {

    private final MeetingRepository meetingRepository;
    private final MutableLiveData<Boolean> isSaveButtonEnabledMutableLiveData = new MutableLiveData<>(false);
    private final SingleLiveEvent<Void> closeActivitySingleLiveEvent = new SingleLiveEvent<>();
    private Room selectedRoom;


    public AddMeetingViewModel(@NonNull MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    // activate add button !
    public MutableLiveData<Boolean> getIsSaveButtonEnabledLiveData() {
        return isSaveButtonEnabledMutableLiveData;
    }

    public void onTopicChanged(String topic) {
        if (!topic.isEmpty()) {
            isSaveButtonEnabledMutableLiveData.setValue(true);
        } else {
            isSaveButtonEnabledMutableLiveData.setValue(false);
        }
    }

    String[] data = new String[]{"7 : 00", "8 : 00", "9 : 00", "10 : 00","11 : 00", "12 : 00"
            , "13 : 00", "14 : 00", "15 : 00", "16 : 00", "17 : 00", "18 : 00", "19 : 00", "20 : 00"
            , "21 : 00", "22 : 00", "23 : 00"};
    // single event close activity
    public SingleLiveEvent<Void> getCloseActivitySingleLiveEvent() {
        return closeActivitySingleLiveEvent;
    }

    //method creation
    public void onAddButtonClicked(
            @NonNull String topic,
            @Nullable LocalTime time,
            @Nullable Room room,
            @NonNull String participantMail
    ) {
        meetingRepository.addMeeting(topic, time, room, participantMail);
        closeActivitySingleLiveEvent.call();
    }

    public void onRoomSelected(Room selectedItem) {
        selectedRoom = selectedItem;
    }

}
