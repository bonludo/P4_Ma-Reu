package com.bonboncompany.p4.ui.add;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bonboncompany.p4.R;
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

       // TextInputEditText room_avatar = findViewById(R.id.addtime);
        TextInputEditText roomEditText = findViewById(R.id.addroom);
        TextInputEditText topicEditText = findViewById(R.id.addmeetingtopic);
        TextInputEditText participantEditText = findViewById(R.id.addparticipantmail1);
        Button addMeetingButton = findViewById(R.id.addButton);

        //bindAddButton(viewModel,topicEditText,roomEditText,participantEditText, addMeetingButton);

    }

//    private void bindAddButton(AddMeetingViewModel viewModel, TextInputEditText topicEditText, TextInputEditText roomEditText, TextInputEditText participantEditText, Button addMeetingButton) {
//        addMeetingButton.setOnClickListener(v -> viewModel.onAddButtonClicked(
//                topicEditText.getText().toString(),
//                e.g., Integer. parseInt(myEditText. getText(),
//                roomEditText.getText().toString(),
//                participantEditText.getText().toString()
//                ));
//        viewModel.getIsSaveButtonEnabledLiveData().observe(this, isSaveButtonEnabled -> addMeetingButton.setEnabled(isSaveButtonEnabled));
//    }
}