package com.bonboncompany.p4.data;

import androidx.lifecycle.MutableLiveData;

import com.bonboncompany.p4.data.model.Meeting;
import com.bonboncompany.p4.data.model.Room;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MeetingRepository {

    private final MutableLiveData <List<Meeting>> meetingLiveData = new MutableLiveData<>(new ArrayList<>());

    private long id = 0;

    public void addMeeting (
            String meetingTopic,
            int time,
            Room room,
            String participantMail
    ){
        List<Meeting> meeting = meetingLiveData.getValue();
        meeting.add(
                new Meeting(
                        id++,
                        meetingTopic,
                        time,
                        room,
                        participantMail
                )
        );
        meetingLiveData.setValue(meeting);
    }

    public void deleteMeeting (long meetingId) {
        List<Meeting> meeting = meetingLiveData.getValue();

        if (meeting == null) return;

        for (
                Iterator<Meeting> iterator = meeting.iterator(); iterator.hasNext(); ) {
            Meeting meetings = iterator.next();

            if (meetings.getId() == meetingId) {
                iterator.remove();
                break;
            }

            meetingLiveData.setValue(meeting);
        }
    }
}
