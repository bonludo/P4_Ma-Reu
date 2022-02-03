package com.bonboncompany.p4.ui.add;

import static org.junit.Assert.*;

import androidx.lifecycle.MutableLiveData;

import com.bonboncompany.p4.data.MeetingRepository;
import com.bonboncompany.p4.data.model.Meeting;
import com.bonboncompany.p4.ui.list.MeetingViewModel;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AddMeetingViewModelTest {

    private MeetingRepository meetingRepository;
    private MeetingViewModel viewModel;
    private MutableLiveData<List<Meeting>> meetingMutableLiveData;


    @Before
    public void setUp() {

        meetingMutableLiveData = new MutableLiveData<>();
    }

    @Test
    public void testAddMeetingInListLiveData() {

        // Given


        // When


        // Then
        assertFalse(true);
    }
}

