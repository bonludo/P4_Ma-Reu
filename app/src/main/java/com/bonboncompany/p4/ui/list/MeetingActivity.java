package com.bonboncompany.p4.ui.list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bonboncompany.p4.R;
import com.bonboncompany.p4.ui.add.AddMeetingActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MeetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.main_fab_add);
        fab.setOnClickListener(v -> startActivity(AddMeetingActivity.navigate(this)));


    }
}