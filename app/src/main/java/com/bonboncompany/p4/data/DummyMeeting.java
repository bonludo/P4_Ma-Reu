package com.bonboncompany.p4.data;

import com.bonboncompany.p4.data.model.Meeting;
import com.bonboncompany.p4.data.model.Room;

import java.util.Arrays;
import java.util.List;

public abstract class DummyMeeting extends Meeting{

    //public static List<Meeting> DUMMY_MEETING = new ArrayList<>();
    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(1,"shoua",12,Room.getRoom().get(1),"george"),
            new Meeting(1,"shoua",13,Room.getRoom().get(1),"julius"));

    public DummyMeeting(int id, String meetingTopic, int time, Room room, String participantMail) {
        super(id, meetingTopic, time, room, participantMail);
    }
}
