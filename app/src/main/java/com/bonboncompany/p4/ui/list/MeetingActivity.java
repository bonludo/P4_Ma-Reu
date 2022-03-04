package com.bonboncompany.p4.ui.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.bonboncompany.p4.R;
import com.bonboncompany.p4.ui.ViewModelFactory;
import com.bonboncompany.p4.ui.add.AddMeetingActivity;
import com.bonboncompany.p4.ui.detail.DetailMeetingActivity;
import com.bonboncompany.p4.ui.list.dialogfilter.CustomRoomSpinnerDialog;
import com.bonboncompany.p4.ui.list.dialogfilter.CustomTimePickerDialog;
import com.bonboncompany.p4.util.App;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalTime;

public class MeetingActivity extends AppCompatActivity implements OnMeetingClickedListener {

    private MeetingViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.main_fab_add);
        fab.setOnClickListener(v -> startActivity(AddMeetingActivity.navigate(this)));

        MeetingAdapter adapter = new MeetingAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.meeting_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(
                MeetingViewModel.class);
        viewModel.getMeetingListLiveData().observe(this,
                meetingViewStateItems -> adapter.submitList(meetingViewStateItems));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.filter_hour:

                timeButtonClicked();
                return true;

            case R.id.filter_room:

                roomButtonClicked();
                return true;

            case R.id.refresh_all:

                refreshButtonClicked();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void roomButtonClicked() {

        CustomRoomSpinnerDialog.MyRoomDialogListener listener = value -> {
            Toast.makeText(this, this.getString(R.string.reunion_en_salle)
                    + value.toString(), Toast.LENGTH_LONG).show();
            viewModel.onRoomChanged(value);
        };
        CustomRoomSpinnerDialog dialog = new CustomRoomSpinnerDialog(this, listener);
        dialog.show();
    }

    public void timeButtonClicked() {
        CustomTimePickerDialog.MyTimeDialogListener listener = new CustomTimePickerDialog.MyTimeDialogListener() {
            @Override
            public void userSelectedAValue(LocalTime value) {
                viewModel.onHourChanged(value);
            }
        };
        CustomTimePickerDialog dialog = new CustomTimePickerDialog(this, listener);
        dialog.show();
    }

    public void refreshButtonClicked() {
        viewModel.onHourChanged(null);
        viewModel.onRoomChanged(null);
        Toast.makeText(this, R.string.liste_complete, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMeetingClicked(long meetingId) {
        startActivity(DetailMeetingActivity.navigate(this, meetingId));
    }

    @Override
    public void onDeleteMeetingClicked(long meetingId) {
        viewModel.onDeleteMeetingClicked(meetingId);
    }
}