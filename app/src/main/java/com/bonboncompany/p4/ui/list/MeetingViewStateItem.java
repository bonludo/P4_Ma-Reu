package com.bonboncompany.p4.ui.list;

import androidx.annotation.NonNull;

import com.bonboncompany.p4.data.model.Room;

import java.time.LocalTime;
import java.util.Objects;

public class MeetingViewStateItem {

    private final long id;

    private final String topic;

    private final Room room;

    private final LocalTime time;

    private final String participantMail;

    public MeetingViewStateItem(long id, String topic, Room room, LocalTime time, String participantMail) {
        this.id = id;
        this.topic = topic;
        this.room = room;
        this.time = time;
        this.participantMail = participantMail;
    }

    public long getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public Room getRoom() {
        return room;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getParticipantMail() {
        return participantMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingViewStateItem that = (MeetingViewStateItem) o;
        return id == that.id &&
                Objects.equals(topic, that.topic) &&
                Objects.equals(room, that.room) &&
                Objects.equals(time, that.time) &&
                Objects.equals(participantMail, that.participantMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, room, time, participantMail);
    }

    @NonNull
    @Override
    public String toString() {
        return "MeetingViewStateItem{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", room=" + room +
                ", time=" + time +
                ", participantMail='" + participantMail + '\'' +
                '}';
    }
}
