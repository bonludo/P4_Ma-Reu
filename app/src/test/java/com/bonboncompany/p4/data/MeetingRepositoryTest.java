package com.bonboncompany.p4.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.bonboncompany.p4.data.model.Meeting;
import com.bonboncompany.p4.data.model.Room;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalTime;

public class MeetingRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private MeetingRepository meetingRepository;

    @Before
    public void setUp() throws Exception {
        meetingRepository = new MeetingRepository();
    }





    @Test
    public void testGetMeetingListLiveData() {

        // Given
        newMeetings();

        // When
//        meetingsLiveData.setValue();
//        meetingRepository.getMeetingsLiveData();
        // Then
        assertFalse(meetingRepository.getMeetingsLiveData().getValue().isEmpty());
    }

    @Test
    public void testOnDeleteMeetingClicked() {

        // Given
//        meetingsLiveData.setValue(newMeetings().getValue());
        Meeting meetingToDelete = meetingRepository.getMeetingsLiveData().getValue().get(0);

        // When
        meetingRepository.deleteMeeting(meetingToDelete.getId());

        // Then
        assertFalse(meetingRepository.getMeetingsLiveData().getValue().contains(meetingToDelete));

    }

    @Test
    public void testAddMeeting() {

        // Given
        Meeting meetingToAdd = meetingRepository.addMeeting("Réunion A",
                LocalTime.of(8, 00),
                Room.DIDDY,
                "lucas@yahoo.fr, henry@LIVE.fr, george@game.com, george@game.com");

        // When

        // Then
        assertTrue(meetingRepository.getMeetingsLiveData().getValue().contains(meetingToAdd));
    }

    private void  newMeetings() {
        meetingRepository.addMeeting(
                "Réunion A",
                LocalTime.of(8, 00),
                Room.DIDDY,
                "lucas@yahoo.fr, henry@LIVE.fr, george@game.com, george@game.com");

    }
}



