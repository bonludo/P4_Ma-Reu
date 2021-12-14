package com.bonboncompany.p4.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.bonboncompany.p4.data.MeetingRepository;
import com.bonboncompany.p4.data.model.Meeting;

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
                meeting.getTime(),
                meeting.getRoom(),
                meeting.getParticipantMail());
    }

    public void getMeetingById(long meetingId) {

        MeetingDetailViewStateMutableLiveData.addSource(meetingRepository.getMeetingsLiveData(), meetings -> {

            Meeting foundMeeting = null;

            for (Meeting meeting : meetings) {
                if (meetingId == meeting.getId()) {
                    foundMeeting = meeting;
                    break;
                }
            }
            if (foundMeeting != null) {
                MeetingDetailViewStateMutableLiveData.setValue(displayMeeting(foundMeeting));
            }
        });
    }

    public LiveData<DetailMeetingViewState> meetingDetailViewStateLiveData(){
        return MeetingDetailViewStateMutableLiveData;
    }





}