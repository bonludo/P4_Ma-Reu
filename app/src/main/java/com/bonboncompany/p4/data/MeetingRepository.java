package com.bonboncompany.p4.data;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.bonboncompany.p4.data.model.Meeting;
import com.bonboncompany.p4.data.model.Room;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MeetingRepository {

    private final MutableLiveData <List<Meeting>> meetingsLiveData = new MutableLiveData<>(new ArrayList<>());

    private long id = 0;

    public MutableLiveData<List<Meeting>> getMeetingsLiveData (){
        return meetingsLiveData;
    };

    public void addMeeting (
            String meetingTopic,
            LocalTime time,
            Room room,
            String participantMail
    ){
        List<Meeting> meetings = meetingsLiveData.getValue();
        meetings.add(
                new Meeting(
                        id++,
                        meetingTopic,
                        time,
                        room,
                        participantMail
                )
        );
        meetingsLiveData.setValue(meetings);
    }

    public void deleteMeeting (long meetingId) {
        List<Meeting> meetings = meetingsLiveData.getValue();

        if (meetings == null) return;

        for (
                Iterator<Meeting> iterator = meetings.iterator(); iterator.hasNext(); ) {
            Meeting meetingnext = iterator.next();

            if (meetingnext.getId() == meetingId) {
                iterator.remove();
                break;
            }

            meetingsLiveData.setValue(meetings);
        }
    }

    private static final LocalTime localTime1 = LocalTime.of(13, 10);

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(1,"dérapage",localTime1,Room.getRoom().get(1),"george"),
            new Meeting(2,"vitesse",localTime1,Room.getRoom().get(2),"julius"));

}
