package com.bonboncompany.p4.ui.list;

import static org.junit.Assert.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.bonboncompany.p4.data.MeetingRepository;
import com.bonboncompany.p4.data.model.Meeting;
import com.bonboncompany.p4.data.model.Room;
import com.bonboncompany.p4.ui.detail.DetailMeetingViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingViewModelTest {

    // Add a meeting
    // Delete a meeting
    // Filter the meeting by hour
    // Filter the meeting by room
    // display management for all phone and tablet sizes
    // in landscape or portrait mode


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MeetingRepository meetingRepository;
    private MeetingViewModel viewModel;
    private MutableLiveData<List<Meeting>> meetingsMutableLiveData;

    @Before
    public void setUp() {

        meetingRepository = new MeetingRepository();
        viewModel = new MeetingViewModel(meetingRepository);
        meetingsMutableLiveData = new MutableLiveData<>();

        meetingsMutableLiveData.setValue(nineMeeting());

    }

    @Test
    public void testGetMeetingListLiveData() {

        // Given
        meetingRepository.getMeetingsLiveData().getValue().clear();

        meetingRepository.getMeetingsLiveData().getValue().addAll(nineMeeting());
        viewModel.getMeetingListLiveData().getValue().size();
        // When

        // Then
        assertEquals(meetingRepository.getMeetingsLiveData().getValue().size(),viewModel.getMeetingListLiveData().getValue().size());
    }

    @Test
    public void testOnDeleteMeetingClicked() {

        // Given
//        meetingsMutableLiveData.setValue(nineMeeting());

        // When
        viewModel.onDeleteMeetingClicked(1);

        // Then
        assertFalse(true);
    }

    @Test
    public void testOnRoomChanged() {

        // Given


        // When


        // Then
        assertFalse(true);
    }

    @Test
    public void testOnHourChanged() {

        // Given


        // When


        // Then
        assertFalse(true);
    }

    @Test
    public void testRefreshButtonClicked() {

        // Given


        // When


        // Then
        assertFalse(true);
    }

    private List<Meeting> nineMeeting() {
        List<Meeting> meetings = new ArrayList<Meeting>();
        meetings.add(new Meeting(1,"Réunion A", LocalTime.of(8, 00), Room.DIDDY, "lucas@yahoo.fr, henry@LIVE.fr, george@game.com, george@game.com"));
        meetings.add(new Meeting(2,"Réunion B", LocalTime.of(11, 00), Room.KIRBY, "lucas@yahoo.fr, henry@LIVE.fr, george@game.com, george@game.com"));
        meetings.add(new Meeting(3,"Réunion C", LocalTime.of(13, 00), Room.DONKEY, "lucas@yahoo.fr, henry@LIVE.fr, george@game.com, george@game.com"));
        meetings.add(new Meeting(4,"dérapage", LocalTime.of(18, 00), Room.LUIGI, "lucas@yahoo.fr, henry@LIVE.fr, george@game.com, george@game.com"));
        meetings.add(new Meeting(5,"Vitesse", LocalTime.of(10, 00), Room.BOWSER, "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com"));
        meetings.add(new Meeting(6,"Piège", LocalTime.of(12, 00), Room.ZELDA, "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com"));
        meetings.add(new Meeting(7,"banane", LocalTime.of(18, 00), Room.MARIO, "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com"));
        meetings.add(new Meeting(8,"Bombe", LocalTime.of(18, 00), Room.LINK, "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com"));
        meetings.add(new Meeting(9,"Circuit", LocalTime.of(8, 00), Room.ZELDA, "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com"));
        return meetings;
    }
}
