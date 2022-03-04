package com.bonboncompany.p4.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bonboncompany.p4.R;
import com.bonboncompany.p4.ui.ViewModelFactory;

public class DetailMeetingActivity extends AppCompatActivity {

    private static final String MEETING_ID = "MEETING_ID";

    public static Intent navigate(Context context, long meetingId) {
        Intent intent = new Intent(context, DetailMeetingActivity.class);
        intent.putExtra(MEETING_ID, meetingId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_meeting);

        long meetingId = getIntent().getLongExtra(MEETING_ID, -1);

        DetailMeetingViewModel viewModel = new ViewModelProvider(this,
                ViewModelFactory.getInstance()).get(DetailMeetingViewModel.class);
        viewModel.getMeetingById(meetingId);

        ImageView roomImageView = findViewById(R.id.imageroom_detail);
        TextView topicMeetingTextView = findViewById(R.id.meetingtopic_detail);
        TextView timeTextView = findViewById(R.id.time_detail);
        TextView roomTextView = findViewById(R.id.room_detail);
        TextView mailParticipantTextView = findViewById(R.id.mailparticipant_detail);

        viewModel.meetingDetailViewStateLiveData().observe(this, detailMeetingViewState -> {
            topicMeetingTextView.setText(getString(R.string.sujetdetail) + detailMeetingViewState.getMeetingTopic());
            timeTextView.setText(getString(R.string.reunion_adetail) + detailMeetingViewState.getTime());
            roomTextView.setText(getString(R.string.salledetail) + detailMeetingViewState.getRoom());
            mailParticipantTextView.setText(detailMeetingViewState.getParticipantMail());
            roomImageView.setImageResource(detailMeetingViewState.getIcon());
        });
    }
}
