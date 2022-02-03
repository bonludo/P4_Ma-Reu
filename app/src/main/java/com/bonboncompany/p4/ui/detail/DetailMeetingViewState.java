package com.bonboncompany.p4.ui.detail;

import androidx.annotation.NonNull;

import java.util.Objects;

public class DetailMeetingViewState {

    private final String meetingTopic;
    private final String time;
    private final String room;
    private final String participantMail;
    private final int icon;

    public int getIcon() {
        return icon;
    }

    public DetailMeetingViewState(String meetingTopic, String time, String room, String participantMail, int icon) {
        this.meetingTopic = meetingTopic;
        this.time = time;
        this.room = room;
        this.participantMail = participantMail;
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailMeetingViewState that = (DetailMeetingViewState) o;
        return icon == that.icon && Objects.equals(meetingTopic, that.meetingTopic) && Objects.equals(time, that.time) && Objects.equals(room, that.room) && Objects.equals(participantMail, that.participantMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingTopic, time, room, participantMail,icon);
    }

    @NonNull
    @Override
    public String toString() {
        return "DetailMeetingViewState{" +
                "meetingTopic='" + meetingTopic + '\'' +
                ", time='" + time + '\'' +
                ", room='" + room + '\'' +
                ", participantMail='" + participantMail + '\'' +
                ", icon=" + icon +
                '}';
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public String  getTime() {
        return time;
    }
    public String getRoom() {
        return room;
    }

    public String getParticipantMail() {
        return participantMail;
    }
}
