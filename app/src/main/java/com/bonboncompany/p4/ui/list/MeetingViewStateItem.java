package com.bonboncompany.p4.ui.list;

import androidx.annotation.NonNull;

import com.bonboncompany.p4.data.model.Room;

import java.time.LocalTime;
import java.util.Objects;

public class MeetingViewStateItem {

    private final long id;

    private final String topic;

    private final String participantMail;

    public MeetingViewStateItem(long id, String topic,  String participantMail) {
        this.id = id;
        this.topic = topic;
        this.participantMail = participantMail;
    }

    public long getId() {
        return id;
    }

    public String getTopic() {
        return topic;
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
                Objects.equals(participantMail, that.participantMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, participantMail);
    }

    @NonNull
    @Override
    public String toString() {
        return "MeetingViewStateItem{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", participantMail='" + participantMail + '\'' +
                '}';
    }
}
