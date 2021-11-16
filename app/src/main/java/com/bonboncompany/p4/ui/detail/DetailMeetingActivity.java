package com.bonboncompany.p4.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bonboncompany.p4.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailMeetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_meeting);

        ImageView roomImageView = findViewById(R.id.imageroom_detail);
        TextView topicMeetingTextView = findViewById(R.id.meetingtopic_detail);
        TextView roomTextView = findViewById(R.id.room_detail);
        TextView mailParticipantTextView = findViewById(R.id.mailparticipant_detail);

        // todo after detailviewstate
    }
}