package com.bonboncompany.p4.ui.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.bonboncompany.p4.data.MeetingRepository;
import com.bonboncompany.p4.data.model.Meeting;
import com.bonboncompany.p4.data.model.Room;

import java.time.LocalTime;
import java.util.List;

public class MeetingViewModel extends ViewModel {

    private final MeetingRepository meetingRepository;

    public MeetingViewModel(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public LiveData<List<MeetingViewStateItem>> getMeetingViewStateItemLiveData() {
        return new MeetingViewStateItem(
                getMeetingViewStateItemLiveData()


        )
    }

    public String infoMeeting() {
        String topic = meeting.get;
        LocalTime hour = null;
        Room room = null;
        return topic + " - " + hour + " - " + room;
    }


}
