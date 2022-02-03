package com.bonboncompany.p4.data;

import static org.junit.Assert.*;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bonboncompany.p4.data.model.Meeting;
import com.bonboncompany.p4.data.model.Room;

import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingRepositoryTest {

    MeetingRepository meetingRepository = new MeetingRepository();
    LiveData<List<Meeting>> meeting = meetingRepository.getMeetingsLiveData();

    private MutableLiveData<List<Meeting>> meetingsLiveData = new MutableLiveData<>(new ArrayList<>());


    @Test
    public void testGetMeetingListLiveData() {

        // Given
        meeting = newMeetings();

        // When
        meetingsLiveData.setValue(newMeetings().getValue());

        // Then
        assertFalse(meetingsLiveData.getValue().isEmpty());
    }

    @Test
    public void testOnDeleteMeetingClicked() {

        // Given
        meetingsLiveData.setValue(newMeetings().getValue());
        Meeting meetingToDelete = meetingsLiveData.getValue().get(0);

        // When
        meetingRepository.deleteMeeting(meetingToDelete.getId());

        // Then
        assertFalse(meetingsLiveData.getValue().contains(meetingToDelete));

    }

    @Test
    public void testAddMeeting() {

        Meeting meetingToAdd = meetingRepository.addMeeting("Réunion A",
                LocalTime.of(8, 00),
                Room.DIDDY,
                "lucas@yahoo.fr, henry@LIVE.fr, george@game.com, george@game.com");
        // Given


        // When


        // Then

    }

    private LiveData<List<Meeting>> newMeetings() {
        meetingRepository.addMeeting(
                "Réunion A",
                LocalTime.of(8, 00),
                Room.DIDDY,
                "lucas@yahoo.fr, henry@LIVE.fr, george@game.com, george@game.com");
        return null;
    }
}