package com.bonboncompany.p4.ui.add;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bonboncompany.p4.R;
import com.bonboncompany.p4.data.model.Room;
import com.bonboncompany.p4.ui.ViewModelFactory;
import com.google.android.material.textfield.TextInputEditText;

public class AddMeetingActivity extends AppCompatActivity {

    public static Intent navigate(Context context) {
        return new Intent(context, AddMeetingActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);



        ImageView room_avatar = findViewById(R.id.addavatarroom);
        //Todo Image change when a room is take

        AddMeetingViewModel viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(AddMeetingViewModel.class);

        TextInputEditText topicEditText = findViewById(R.id.addmeetingtopic);
        Spinner roomSpinner = findViewById(R.id.spinnerRoom);
        TextInputEditText participantEditText = findViewById(R.id.addparticipantmail);
        Button addMeetingButton = findViewById(R.id.addButton);

        roomSpinner.setAdapter(new ArrayAdapter<Room>(this, android.R.layout.simple_spinner_item, Room.values()));

        //bindAddButton(viewModel,topicEditText,roomSpinner,participantEditText, addMeetingButton);

    }
}