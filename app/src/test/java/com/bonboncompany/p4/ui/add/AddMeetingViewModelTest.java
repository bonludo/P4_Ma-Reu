package com.bonboncompany.p4.ui.add;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.bonboncompany.p4.data.MeetingRepository;
import com.bonboncompany.p4.data.model.Room;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalTime;


public class AddMeetingViewModelTest {


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    private MeetingRepository meetingRepository;
    private AddMeetingViewModel viewModel;

    @Before
    public void setUp() {

        meetingRepository = new MeetingRepository();
        viewModel = new AddMeetingViewModel(meetingRepository);

    }

    @Test
    public void testAddMeetingWhenAddButtonClicked() {

        // Given
        meetingRepository.getMeetingsLiveData().getValue().clear();
        String topicTest = "daphné";

        // When
        viewModel.onAddButtonClicked(topicTest, LocalTime.of(10, 10),Room.KIRBY,
                "marcopolo@google.com");

        // Then
        assertTrue(meetingRepository.getMeetingsLiveData().getValue().get(0).getMeetingTopic().contains(topicTest));
    }

    @Test
    public void testOnTopicChangedIsSaveButtonEnabled() {

        // Given
        meetingRepository.getMeetingsLiveData().getValue().clear();
        String topicTest = "daphné";

        // When
       viewModel.onTopicChanged(topicTest);

        // Then
        assertEquals(viewModel.getIsSaveButtonEnabledLiveData().getValue(), true);
    }

}

