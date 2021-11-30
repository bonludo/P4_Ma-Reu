package com.bonboncompany.p4.ui.list;

import com.bonboncompany.p4.data.model.Room;

import java.time.LocalTime;
import java.util.Objects;

public class MeetingViewStateItem {

    private final long id;

    private final String meetingTopic;

    private final LocalTime time;

    private final Room room;

    private final String participantMail;

    public MeetingViewStateItem(long id, String meetingTopic, LocalTime time, Room room, String participantMail) {
        this.id = id;
        this.meetingTopic = meetingTopic;
        this.time = time;
        this.room = room;
        this.participantMail = participantMail;
    }

    public long getId() {

        return id;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public String getParticipantMail() {

        return participantMail;
    }

    public LocalTime getTime() {
        return time;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingViewStateItem that = (MeetingViewStateItem) o;
        return id == that.id && Objects.equals(meetingTopic, that.meetingTopic) && Objects.equals(time, that.time) && room == that.room && Objects.equals(participantMail, that.participantMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meetingTopic, time, room, participantMail);
    }

    @Override
    public String toString() {
        return "MeetingViewStateItem{" +
                "id=" + id +
                ", meetingTopic='" + meetingTopic + '\'' +
                ", time=" + time +
                ", room=" + room +
                ", participantMail='" + participantMail + '\'' +
                '}';
    }
}
