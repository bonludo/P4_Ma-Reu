package com.bonboncompany.p4.ui.list;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MediatorLiveData;

import com.bonboncompany.p4.data.MeetingRepository;
import com.bonboncompany.p4.data.model.Meeting;
import com.bonboncompany.p4.data.model.Room;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingViewModelTest {


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    private MeetingRepository meetingRepository;
    private MeetingViewModel viewModel;

    @Before
    public void setUp() throws Exception {

        meetingRepository = new MeetingRepository();
        viewModel = new MeetingViewModel(meetingRepository);

    }

    @Test
    public void testHourFilter() {

        //Given
        LocalTime testTime = LocalTime.of(10, 00);
        meetingRepository.getMeetingsLiveData().getValue().clear();
        meetingRepository.getMeetingsLiveData().getValue().addAll(sixMeeting());
        int sizeTest = 1;

        //When
        viewModel.combineHour(meetingRepository.getMeetingsLiveData().getValue(), testTime);

        //Then
        assertEquals(viewModel.getMeetingListLiveData().getValue().size(), sizeTest);

    }

    @Test
    public void testRoomFilter() {

        //Given
        int sizeTest = 2;
        Room testroom = Room.ZELDA;
        meetingRepository.getMeetingsLiveData().getValue().clear();
        meetingRepository.getMeetingsLiveData().getValue().addAll(sixMeeting());

        //When
        viewModel.combineRoom(meetingRepository.getMeetingsLiveData().getValue(), testroom);

        //Then
        assertEquals(viewModel.getMeetingListLiveData().getValue().size(), sizeTest);
    }


    //Meeting for testing
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
        selected.add(new Meeting(4, "Bombe", LocalTime.of(18, 00), Room.LINK,
                "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com," +
                        " george@game.com, george@game.com, george@game.com, george@game.com, george@game.com"));
        selected.add(new Meeting(5, "Circuit", LocalTime.of(8, 00), Room.ZELDA,
                "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com," +
                        " george@game.com, george@game.com, george@game.com, george@game.com, george@game.com"));
        return selected;
    }

}