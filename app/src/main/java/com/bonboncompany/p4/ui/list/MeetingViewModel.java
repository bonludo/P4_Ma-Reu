package com.bonboncompany.p4.ui.list;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.bonboncompany.p4.data.MeetingRepository;
import com.bonboncompany.p4.data.model.Meeting;
import com.bonboncompany.p4.data.model.Room;

import java.time.LocalTime;
import java.util.List;

public class MeetingViewModel extends ViewModel {

    private MeetingRepository meetingRepository = new MeetingRepository();

    private final LiveData<String> meetingList = Transformations.map(meetingRepository.getMeetingsLiveData(), new Function<List<Meeting>, List<Meeting>>() {
        @Override
        public List<String> apply(List<Meeting> input) {
            return input;
        }
    })

    public MeetingViewModel(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }


    public String infoMeeting(Meeting meeting) {
        String topic = meeting.getMeetingTopic();
        LocalTime hour = meeting.getTime();
        Room room = meeting.getRoom();

        return topic + " - " + hour + " - " + room;
    }


}
