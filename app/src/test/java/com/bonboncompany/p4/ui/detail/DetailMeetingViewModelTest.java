package com.bonboncompany.p4.ui.detail;

import static org.junit.Assert.assertEquals;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.bonboncompany.p4.data.MeetingRepository;
import com.bonboncompany.p4.data.model.Meeting;
import com.bonboncompany.p4.data.model.Room;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DetailMeetingViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    private MeetingRepository meetingRepository;
    private DetailMeetingViewModel viewModel;

    @Before
    public void setUp() {

        meetingRepository = new MeetingRepository();
        viewModel = new DetailMeetingViewModel(meetingRepository);

    }

    @Test
    public void getMeetingByIdTest() {

        // Given
        meetingRepository.getMeetingsLiveData().getValue().clear();

        long position = 4;

       meetingRepository.getMeetingsLiveData().getValue().addAll(sixMeeting());


        // When
        assertEquals(meetingRepository.getMeetingsLiveData().getValue().get(4).getId(),meetingRepository.getMeetingById(position).getId());

    }
    //Meeting for test
    private List<Meeting> sixMeeting() {
        List<Meeting> selected = new ArrayList<Meeting>();
        String topicTest = "Dérapage";
        selected.add (new Meeting(0, topicTest, LocalTime.of(18, 00), Room.LUIGI,
                "lucas@yahoo.fr, henry@LIVE.fr, george@game.com, george@game.com"));
        selected.add (new Meeting(1, "Vitesse", LocalTime.of(10, 00), Room.BOWSER,
                "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com"));
        selected.add (new Meeting(2, "Piège", LocalTime.of(12, 00), Room.ZELDA,
                "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com"));
        selected.add (new Meeting(3, "banane", LocalTime.of(18, 00), Room.MARIO,
                "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com"));
        selected.add (new Meeting(4, "Bombe", LocalTime.of(18, 00), Room.LINK,
                "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com"));
        selected.add (new Meeting(5, "Circuit", LocalTime.of(8, 00), Room.ZELDA,
                "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com"));
        return selected;
    }
}