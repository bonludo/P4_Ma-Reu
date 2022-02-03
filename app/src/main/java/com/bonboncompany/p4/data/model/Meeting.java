package com.bonboncompany.p4.data.model;

import androidx.annotation.NonNull;

import java.time.LocalTime;
import java.util.Objects;

public class Meeting {

    private final long id;
    private final String meetingTopic;
    LocalTime time;
    private final Room room;
    private final String participantMail;

    public Meeting(long id,
                   String meetingTopic,
                   LocalTime time,
                   Room room,
                   String participantMail
    ) {
        this.id = id;
        this.time = time;
        this.meetingTopic = meetingTopic;
        this.room = room;
        this.participantMail = participantMail;

    }

    public long getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }

    public Room getRoom() {
        return room;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public String getParticipantMail() {
        return participantMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id == meeting.id &&
                time == meeting.time &&
                Objects.equals(meetingTopic, meeting.meetingTopic) &&
                Objects.equals(room, meeting.room) &&
                Objects.equals(participantMail, meeting.participantMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meetingTopic, time, room, participantMail);
    }

    @NonNull
    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", meetingTopic='" + meetingTopic + '\'' +
                ", time=" + time +
                ", room=" + room +
                ", participantMail='" + participantMail + '\'' +
                '}';
    }
}
//TODO : Localtime and List participantMail