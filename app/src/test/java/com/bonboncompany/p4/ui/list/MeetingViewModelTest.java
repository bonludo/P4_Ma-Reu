package com.bonboncompany.p4.ui.list;

import static org.junit.Assert.*;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bonboncompany.p4.data.MeetingRepository;
import com.bonboncompany.p4.data.model.Meeting;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MeetingViewModelTest {

    // Add a meeting
    // Delete a meeting
    // Filter the meeting by hour
    // Filter the meeting by room
    // display management for all phone and tablet sizes
    // in landscape or portrait mode

    private MeetingRepository meetingRepository;
    private MeetingViewModel viewModel;
    private MutableLiveData<List<Meeting>> meetingsMutableLiveData;


    @Before
    public void setUp() {
        meetingsMutableLiveData = new MutableLiveData<>();
        viewModel = new MeetingViewModel(meetingRepository);


        List<Meeting> meetings = meetingsMutableLiveData.getValue();
                meetingsMutableLiveData.setValue(meetings);

    }

    @Test
    public void testGetMeetingListLiveData() {

        // Given
        meetingsMutableLiveData.setValue(null);

        // When
        MeetingViewStateItem result = viewModel.getMeetingListLiveData();

        // Then
        assertEquals(
                testNeighbour,
                service.getNeighbours().get(service.getNeighbours().size() - 1));
    }

    @Test
    public void testOnDeleteMeetingClicked() {

        // Given


        // When


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
}
