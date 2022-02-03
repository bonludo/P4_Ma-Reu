package com.bonboncompany.p4.ui.list;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

import java.util.Objects;

public class MeetingViewStateItem {

    private final long id;

    private final String meetingTopic;

    private final String participants;

    @ColorRes
    private final int imageColorRes;

    public MeetingViewStateItem(long id, String meetingTopic, String participants, int imageColorRes) {
        this.id = id;
        this.meetingTopic = meetingTopic;
        this.participants = participants;
        this.imageColorRes = imageColorRes;
    }

    public long getId() {
        return id;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public String getParticipants() {
        return participants;
    }

    public int getImageColorRes() {
        return imageColorRes;
    }

    @NonNull
    @Override
    public String toString() {
        return "MeetingViewStateItem{" +
            "id=" + id +
            ", meetingTopic='" + meetingTopic + '\'' +
            ", participants='" + participants + '\'' +
            ", imageColorRes=" + imageColorRes +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingViewStateItem that = (MeetingViewStateItem) o;
        return id == that.id &&
            imageColorRes == that.imageColorRes &&
            Objects.equals(meetingTopic, that.meetingTopic) &&
            Objects.equals(participants, that.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meetingTopic, participants, imageColorRes);
    }
}
