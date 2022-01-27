package com.bonboncompany.p4.ui.list.dialogfilter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bonboncompany.p4.R;
import com.bonboncompany.p4.data.model.Room;

public class CustomRoomSpinnerDialog extends Dialog {


    public Context context;
    private Spinner roomSpinnerFilter;
    private Button buttonOK;
    private Button buttonCancel;
    private Room roomSelected;
    private MyRoomDialogListener listener;



    public interface MyRoomDialogListener
    {
        public void userSelectedAValue(Room value);
    }

    public CustomRoomSpinnerDialog(@NonNull Context context, MyRoomDialogListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.room_spinner_custom_dialog);

        roomSpinnerFilter = (Spinner) findViewById(R.id.spinner_room_filter);
        roomSpinnerFilter.setAdapter(new ArrayAdapter<Room>(getContext(),
                android.R.layout.simple_spinner_item,
                Room.values()));

        buttonOK = (Button) findViewById(R.id.button_ok);
        buttonCancel = (Button) findViewById(R.id.button_cancel);


        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedRoomButtonOKClicked();
            }
        });


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonCancelClick();
            }
        });
    }

    private void selectedRoomButtonOKClicked() {

        roomSelected = (Room) roomSpinnerFilter.getSelectedItem();

        if (roomSelected == null) {
            Toast.makeText(this.context, "select a room", Toast.LENGTH_LONG).show();
        }
        this.dismiss(); // Close dialog

        this.listener.userSelectedAValue(roomSelected);
    }

    private void buttonCancelClick() {
        this.dismiss(); // Close dialog
    }
}


