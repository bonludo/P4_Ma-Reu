package com.bonboncompany.p4.ui.detail;

import com.bonboncompany.p4.data.model.Room;

import java.util.Objects;

public class MeetingDetailViewState {

    private final String meetingTopic;
    int time;
    private final Room room;
    private final String participantMail;

    public MeetingDetailViewState(String meetingTopic, Room room, String participantMail) {
        this.meetingTopic = meetingTopic;
        this.room = room;
        this.participantMail = participantMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingDetailViewState that = (MeetingDetailViewState) o;
        return time == that.time &&
                Objects.equals(meetingTopic, that.meetingTopic) &&
                Objects.equals(room, that.room) &&
                Objects.equals(participantMail, that.participantMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingTopic, time, room, participantMail);
    }

    @Override
    public String toString() {
        return "MeetingDetailViewState{" +
                "meetingTopic='" + meetingTopic + '\'' +
                ", time=" + time +
                ", room=" + room +
                ", participantMail='" + participantMail + '\'' +
                '}';
    }
}
