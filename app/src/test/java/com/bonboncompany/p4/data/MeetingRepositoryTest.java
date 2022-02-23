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
import java.util.ArrayList;
import java.util.List;

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

        // When
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
    }


    @Test
    public void testAddMeeting() {

        // Given
        meetingRepository.getMeetingsLiveData().getValue().clear();

        // When
        newMeetings();

        // Then
        assertFalse(meetingRepository.getMeetingsLiveData().getValue().isEmpty());
        assertEquals(2, meetingRepository.getMeetingsLiveData().getValue().size());
    }

    @Test
    public void getMeetingByIdTest() {

        // Given
        meetingRepository.getMeetingsLiveData().getValue().clear();
        meetingRepository.getMeetingsLiveData().getValue().addAll(sixMeeting());
        long position = 4;

        // Then
        assertEquals(meetingRepository.getMeetingsLiveData().getValue().get(4).getId(), meetingRepository.getMeetingById(position).getId());

    }

    //Meeting for test
    private List<Meeting> sixMeeting() {
        List<Meeting> selected = new ArrayList<Meeting>();
        String topicTest = "Dérapage";
        selected.add(new Meeting(0, topicTest, LocalTime.of(18, 00), Room.LUIGI,
                "lucas@yahoo.fr, henry@LIVE.fr, george@game.com, george@game.com"));
        selected.add(new Meeting(1, "Vitesse", LocalTime.of(10, 00), Room.BOWSER,
                "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com, " +
                        "george@game.com, george@game.com, george@game.com, george@game.com, george@game.com"));
        selected.add(new Meeting(2, "Piège", LocalTime.of(12, 00), Room.ZELDA,
                "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com, " +
                        "george@game.com, george@game.com, george@game.com, george@game.com, george@game.com"));
        selected.add(new Meeting(3, "banane", LocalTime.of(18, 00), Room.MARIO,
                "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com," +
                        " george@game.com, george@game.com, george@game.com, george@game.com, george@game.com"));
        selected.add(new Meeting(4, "Bombe", LocalTime.of(18, 00), Room.LUIGI,
                "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com," +
                        " george@game.com, george@game.com, george@game.com, george@game.com, george@game.com"));
        selected.add(new Meeting(5, "Circuit", LocalTime.of(8, 00), Room.ZELDA,
                "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com," +
                        " george@game.com, george@game.com, george@game.com, george@game.com, george@game.com"));
        return selected;
    }

    private void newMeetings() {
        meetingRepository.addMeeting(
                "Réunion A",
                LocalTime.of(8, 00),
                Room.DIDDY,
                "lucas@yahoo.fr, henry@LIVE.fr, george@game.com, george@game.com");
        meetingRepository.addMeeting(
                "Vitesse",
                LocalTime.of(10, 00),
                Room.BOWSER,
                "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com," +
                        " george@game.com, george@game.com, george@game.com, george@game.com, " +
                        "george@game.com");

    }
}



