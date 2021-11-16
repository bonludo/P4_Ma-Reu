package com.bonboncompany.p4.ui.detail;

import androidx.lifecycle.ViewModel;

import com.bonboncompany.p4.data.MeetingRepository;

public class DetailMeetingViewModel extends ViewModel {

    private final MeetingRepository meetingRepository ;

    public DetailMeetingViewModel(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }
}
