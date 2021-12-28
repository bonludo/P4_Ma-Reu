package com.bonboncompany.p4.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.bonboncompany.p4.data.MeetingRepository;
import com.bonboncompany.p4.data.model.Meeting;

import java.util.Locale;

public class DetailMeetingViewModel extends ViewModel {

    private MeetingRepository meetingRepository;

    private final MediatorLiveData<DetailMeetingViewState> MeetingDetailViewStateMutableLiveData
            = new MediatorLiveData<>();


    public DetailMeetingViewModel(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;


    }

    private DetailMeetingViewState displayMeeting(Meeting meeting) {
        return new DetailMeetingViewState(
                meeting.getMeetingTopic(),
                meeting.getTime().toString(),
                capitalize(meeting.getRoom().toString()),
                meeting.getParticipantMail());
    }

    private String capitalize(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public void getMeetingById(long meetingId) {

        MeetingDetailViewStateMutableLiveData.addSource(meetingRepository.getMeetingsLiveData(), meetings -> {

            MeetingDetailViewStateMutableLiveData.setValue(displayMeeting(meetingRepository.getMeetingById(meetingId)));

        });
    }
    public LiveData<DetailMeetingViewState> meetingDetailViewStateLiveData() {
        return MeetingDetailViewStateMutableLiveData;
    }


}