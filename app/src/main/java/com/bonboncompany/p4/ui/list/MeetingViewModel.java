package com.bonboncompany.p4.ui.list;

import androidx.lifecycle.ViewModel;

import com.bonboncompany.p4.data.MeetingRepository;

public class MeetingViewModel extends ViewModel {
    private final MeetingRepository meetingRepository ;

    public MeetingViewModel(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }
}
