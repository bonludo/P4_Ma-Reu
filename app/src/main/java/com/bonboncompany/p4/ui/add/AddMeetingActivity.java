package com.bonboncompany.p4.ui.add;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.bonboncompany.p4.R;
import com.bonboncompany.p4.data.model.Room;
import com.bonboncompany.p4.ui.ViewModelFactory;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalTime;

public class AddMeetingActivity extends AppCompatActivity {

    public static Intent navigate(Context context) {
        return new Intent(context, AddMeetingActivity.class);
    }

    private LocalTime time;
    private Room room;
    NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_meeting);


        AddMeetingViewModel viewModel =
                new ViewModelProvider(this, ViewModelFactory.getInstance()).get(AddMeetingViewModel.class);

        TextInputEditText topicEditText = findViewById(R.id.addmeetingtopic);
        Spinner roomSpinner = findViewById(R.id.spinnerRoom);
        TextInputEditText participantEditText = findViewById(R.id.addparticipantmail);
        Button addMeetingButton = findViewById(R.id.addButton);
        numberPicker = findViewById(R.id.adddatePicker);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(viewModel.data.length-1);
        numberPicker.setDisplayedValues(viewModel.data);
//        timePicker.setIs24HourView(true); // Mode 24H
//        timePicker.setCurrentMinute(0);

        roomSpinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Room.values()));

        bindName(viewModel, topicEditText);


       roomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               room = (Room) roomSpinner.getSelectedItem();
               viewModel.onRoomSelected((Room) roomSpinner.getSelectedItem());
               //line 60 not utility for the moment
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {
           }
       });


        bindAddButton(viewModel,topicEditText,participantEditText, addMeetingButton);

        viewModel.getCloseActivitySingleLiveEvent().observe(this,aVoid -> finish());

    }

    private void bindName(AddMeetingViewModel viewModel, TextInputEditText topicEditText) {
        topicEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.onTopicChanged(s.toString());
            }
        });
    }

    private void bindAddButton (AddMeetingViewModel viewModel,
                                TextInputEditText topicEditText,
                                TextInputEditText participantEditText,
                                Button  addMeetingButton)
    {
        addMeetingButton.setOnClickListener(v -> {
            time = LocalTime.of(numberPicker.getValue() + 7, 0);
            viewModel.onAddButtonClicked(
                    topicEditText.getText().toString(),
                    time,
                    room,
                    participantEditText.getText().toString()
            );
        });
        viewModel.getIsSaveButtonEnabledLiveData().observe(this,
                isSaveButtonEnabled -> addMeetingButton.setEnabled(isSaveButtonEnabled));

    }


}