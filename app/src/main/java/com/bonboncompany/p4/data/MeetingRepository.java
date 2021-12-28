package com.bonboncompany.p4.data;

import androidx.lifecycle.MutableLiveData;

import com.bonboncompany.p4.BuildConfig;
import com.bonboncompany.p4.data.model.Meeting;
import com.bonboncompany.p4.data.model.Room;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MeetingRepository {

    private final MutableLiveData<List<Meeting>> meetingsLiveData = new MutableLiveData<>(new ArrayList<>());

    private long id = 0;

    public MeetingRepository() {
        if (BuildConfig.DEBUG) {
            addMeeting("dérapage", LocalTime.of(13, 10), Room.LUIGI, "lucas@yahoo.fr");
            addMeeting("Vitesse", LocalTime.of(10, 45), Room.BOWSER, "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com");
            addMeeting("Piège", LocalTime.of(12, 50), Room.ZELDA, "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com");
            addMeeting("banane", LocalTime.of(18, 30), Room.MARIO, "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com");
            addMeeting("Bombe", LocalTime.of(15, 20), Room.LUIGI, "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com");
            addMeeting("Circuit", LocalTime.of(8, 0), Room.ZELDA, "george@yahoo.fr , henry@LIVE.fr, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com, george@game.com");
        }
    }

    public MutableLiveData<List<Meeting>> getMeetingsLiveData() {
        return meetingsLiveData;
    }

    public void addMeeting(
            String meetingTopic,
            LocalTime time,
            Room room,
            String participantMail
    ) {
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

    public Meeting getMeetingById (long meetingId) {

        List<Meeting> meetings = meetingsLiveData.getValue();

        Meeting foundMeeting = null;
            for (Meeting meeting : meetings) {
                if (meetingId == meeting.getId()) {
                    foundMeeting = meeting;
                    break;
                }
            }
            return foundMeeting;
        }

    public void getSameMeetingRoomFilter (Room metingRoom) {

        List<Meeting> meetings = meetingsLiveData.getValue();

        List<Meeting> sameRoomMeeting = new ArrayList<>();
        Meeting foundMeeting = null;
        for (Meeting meeting : meetings) {
            if (metingRoom == meeting.getRoom()) {
              sameRoomMeeting.add(meeting);
            }
        }
        meetingsLiveData.setValue(sameRoomMeeting);
    }



    public void deleteMeeting(long meetingId) {
        List<Meeting> meetings = meetingsLiveData.getValue();

        if (meetings == null) return;

        for (Iterator<Meeting> iterator = meetings.iterator(); iterator.hasNext(); ) {
            Meeting meeting = iterator.next();

            if (meeting.getId() == meetingId) {
                iterator.remove();
                break;
            }
        }
        meetingsLiveData.setValue(meetings);
    }
}
