package com.bonboncompany.p4.ui.list;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.bonboncompany.p4.data.MeetingRepository;
import com.bonboncompany.p4.data.model.Meeting;
import com.bonboncompany.p4.data.model.Room;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingViewModel extends ViewModel {


    public LiveData<List<MeetingViewStateItem>> meetingList;


    public MeetingViewModel(MeetingRepository meetingRepository) {

        meetingList = Transformations.map(
                meetingRepository.getMeetingsLiveData(), meetings -> {
                    List<MeetingViewStateItem> meetingViewStateItems = new ArrayList<>();
                    for (Meeting meeting : meetings) {
                        meetingViewStateItems.add(new MeetingViewStateItem(
                                        meeting.getId(),
                                        MeetingViewModel.this.getMeetingInfo(meeting),
                                        meeting.getParticipantMail(),
                                        meeting.getRoom().getColor()
                                )
                        );
                    }
                    return meetingViewStateItems;
                });


    }

    public String getMeetingInfo(Meeting meeting) {
        String topic = meeting.getMeetingTopic();
        LocalTime hour = meeting.getTime();
        Room room = meeting.getRoom();

        return topic + " - " + hour + " - " + room;
    }

//    public void onDeleteMeetingClicked(long meetingId) {
//        meetingRepository.deleteMeeting(meetingId);
//    }
}