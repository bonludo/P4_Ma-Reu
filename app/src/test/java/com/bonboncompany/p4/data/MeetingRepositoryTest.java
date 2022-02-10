package com.bonboncompany.p4.data;

import static org.junit.Assert.assertEquals;
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
        meetingRepository.getMeetingsLiveData().getValue().clear();


        newMeetings();

        // Then
        assertFalse(meetingRepository.getMeetingsLiveData().getValue().isEmpty());
    }


    @Test
    public void testOnDeleteMeetingClicked() {

        // Given
        meetingRepository.getMeetingsLiveData().getValue().clear();
        newMeetings();
        Meeting meetingToDelete = meetingRepository.getMeetingsLiveData().getValue().get(0);




        // When
        meetingRepository.deleteMeeting(meetingToDelete.getId());

        // Then
        assertFalse(meetingRepository.getMeetingsLiveData().getValue().contains(meetingToDelete));
        assertTrue(meetingRepository.getMeetingsLiveData().getValue().isEmpty());

    }


    @Test
    public void testAddMeeting() {

        // Given
        meetingRepository.getMeetingsLiveData().getValue().clear();

        // When
        meetingRepository.addMeeting("Vitesse", LocalTime.of(10, 00), Room.BOWSER,
                "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com," +
                        " george@game.com, george@game.com, george@game.com, george@game.com, george@game.com");
        newMeetings();

        // Then
        assertFalse(meetingRepository.getMeetingsLiveData().getValue().isEmpty());
        assertEquals(2,meetingRepository.getMeetingsLiveData().getValue().size());
    }



    private void  newMeetings() {
        meetingRepository.addMeeting(
                "Réunion A",
                LocalTime.of(8, 00),
                Room.DIDDY,
                "lucas@yahoo.fr, henry@LIVE.fr, george@game.com, george@game.com");

    }
}



