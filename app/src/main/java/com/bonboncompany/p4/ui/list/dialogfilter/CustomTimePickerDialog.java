package com.bonboncompany.p4.ui.list.dialogfilter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bonboncompany.p4.R;

import java.time.LocalTime;

public class CustomTimePickerDialog extends Dialog {


    public Context context;
    private TimePicker timeSpinnerFilter;
    private final CustomTimePickerDialog.MyTimeDialogListener listener;

    public interface MyTimeDialogListener {
        void userSelectedAValue(LocalTime value);
    }

    public CustomTimePickerDialog(@NonNull Context context, MyTimeDialogListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.hour_custom_dialog);

        timeSpinnerFilter = findViewById(R.id.timePickerFilter);
        timeSpinnerFilter.setIs24HourView(true); // Mode 24H

        Button buttonOK = (Button) findViewById(R.id.button_ok);
        Button buttonCancel = (Button) findViewById(R.id.button_cancel);


        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTimeButtonOKClicked();
            }
        });


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonCancelClick();
            }
        });
    }

    public void selectedTimeButtonOKClicked() {

        LocalTime timeSelected = LocalTime.of(timeSpinnerFilter.getCurrentHour(), timeSpinnerFilter.getCurrentMinute());

        if (timeSelected == null) {
            Toast.makeText(this.context, "select a time", Toast.LENGTH_LONG).show();
        }
        this.dismiss(); // Close dialog

        this.listener.userSelectedAValue(timeSelected);
    }

    private void buttonCancelClick() {
        this.dismiss(); // Close dialog
    }
}
