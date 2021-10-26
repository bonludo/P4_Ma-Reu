package com.bonboncompany.p4.data;

import java.util.List;

public class Meeting {

    private final int id;
    private final Room room;
    private final String meetingTopic ;
    private final List<String> participantMail;

    public Meeting (int id, Room room, String meetingTopic, List<String> participantMail) {

        this.id = id;
        this.room = room;
        this.meetingTopic = meetingTopic;
        this.participantMail = participantMail;

    }

    public int getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public List<String> getParticipantMail() {
        return participantMail;
    }

}
