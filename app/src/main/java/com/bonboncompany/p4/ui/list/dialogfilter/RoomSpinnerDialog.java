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

public class RoomSpinnerDialog extends Dialog {

    public interface RoomSpinnerListener {
        public void roomSpinner(Spinner room);
    }

    public Context context;

    private Spinner roomSpinnerFilter;
    private Button buttonOK;
    private Button buttonCancel;

    private RoomSpinnerListener listener;

    public RoomSpinnerDialog(@NonNull Context context, RoomSpinnerListener listener) {
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
                buttonOKClicked();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCancelClick();
            }
        });


    }

    private void buttonOKClicked() {

        Spinner room = this.roomSpinnerFilter;

        if (room == null) {
            Toast.makeText(this.context, "select a room", Toast.LENGTH_LONG).show();
            return;
        }
        this.dismiss();

        if (this.listener != null) {
            this.listener.roomSpinner(room);
        }

    }

    private void buttonCancelClick() {
        this.dismiss();
    }
}


