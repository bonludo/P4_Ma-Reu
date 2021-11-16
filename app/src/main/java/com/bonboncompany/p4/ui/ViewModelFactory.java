package com.bonboncompany.p4.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bonboncompany.p4.data.MeetingRepository;
import com.bonboncompany.p4.ui.add.AddMeetingViewModel;
import com.bonboncompany.p4.ui.detail.DetailMeetingViewModel;
import com.bonboncompany.p4.ui.list.MeetingViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory factory;
    private final MeetingRepository meetingRepository;

    public ViewModelFactory(@NonNull MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public static ViewModelFactory getInstance() {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(
                            new MeetingRepository(
                            )
                    );
                }
            }
        }

        return factory;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MeetingViewModel.class)) {
            return (T) new MeetingViewModel(
                    meetingRepository
            );
        } else if (modelClass.isAssignableFrom(AddMeetingViewModel.class)) {
            return (T) new AddMeetingViewModel(
                    meetingRepository
            );
        } else if (modelClass.isAssignableFrom(DetailMeetingViewModel.class)) {
            return (T) new DetailMeetingViewModel(
                    meetingRepository
            );
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

