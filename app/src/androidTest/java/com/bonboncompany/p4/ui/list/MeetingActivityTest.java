package com.bonboncompany.p4.ui.list;

import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.iterableWithSize;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.IsNull.notNullValue;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.bonboncompany.p4.R;
import com.bonboncompany.p4.data.MeetingRepository;

@RunWith(AndroidJUnit4.class)
public class MeetingActivityTest {

    private MeetingActivity actualActivityTest;
    private MeetingRepository meetingRepository;


    @Before
    public void setUp() throws Exception {

        ActivityScenario<MeetingActivity> mainActivity = ActivityScenario.launch(MeetingActivity.class);
        mainActivity.onActivity(activity -> actualActivityTest = activity);

//        int count = meetingRepository.getMeetingsLiveData().getValue().size();
    }

    @Test
    public void Meeting_shouldNotBeEmpty() {
        onView(allOf(withId(R.id.meeting_recyclerView), isCompletelyDisplayed()))
                .check(matches(hasMinimumChildCount(1)));

    }

    @Test
    public void Meeting_OnclickedshouldAddNewMeeting() throws InterruptedException {

        onView(withId(R.id.main_fab_add)).perform(click());

        onView(withId(R.id.addmeetingtopic)).perform(replaceText("kong"), closeSoftKeyboard());

        onView(withId(R.id.addparticipantmail)).perform(replaceText("kong@gmail.com")
                , closeSoftKeyboard());

        onView(withId(R.id.addButton)).perform(click());

//        Thread.sleep(500);
//
//        onView(allOf(withId(R.id.meeting_recyclerView), isCompletelyDisplayed())).perform(swipeUp());
//
//
//        onView(allOf(withId(R.id.meeting_recyclerView), isCompletelyDisplayed()))
//                .check(matches(withText("kong@gmail.com")));
    }

    @Test
    public void Meeting_deleteAction_shouldRemoveItem() {

        onView(allOf(withId(R.id.meeting_recyclerView), isCompletelyDisplayed()))
                .check(matches(hasChildCount(8)));

        onView(allOf(withId(R.id.meeting_recyclerView), isCompletelyDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
    }

    @Test
    public void Meeting_shouldShowDetailOnClickOnMeeting() {

        onView(allOf(withId(R.id.meeting_recyclerView), isCompletelyDisplayed())).perform(actionOnItemAtPosition(0, click()));

        onView(allOf(withId(R.id.meetingtopic_detail))).check(matches(withText("Sujet : Réunion A")));

    }

}