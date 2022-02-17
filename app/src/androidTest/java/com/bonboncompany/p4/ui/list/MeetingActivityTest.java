package com.bonboncompany.p4.ui.list;

import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.iterableWithSize;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.bonboncompany.p4.R;

@RunWith(AndroidJUnit4.class)
public class MeetingActivityTest {

    private MeetingActivity actualActivityTest;

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

        // timepicker

        // spinner room
        onView(withId(R.id.spinnerRoom)).perform(click());

//        onView(withId(R.id.spinnerRoom))
 //               .check(matches(withText("ZELDA")));

        onView(allOf(withId(R.id.spinnerRoom), withText("ZELDA"))).perform(click());

        onView(withId(R.id.addButton)).perform(click());

        onView(allOf(withId(R.id.meeting_recyclerView),isCompletelyDisplayed())).perform(scrollToPosition(9));

        onView(allOf(withId(R.id.item_list_mail_participant), withText("kong@gmail.com"),
                        withParent(withParent(withId(R.id.meeting_recyclerView))),
                        isDisplayed())).check(matches(withText("kong@gmail.com")));
    }

    @Test
    public void Meeting_deleteAction_shouldRemoveItem() throws InterruptedException {

        onView(allOf(withId(R.id.meeting_recyclerView), isCompletelyDisplayed())).check(matches(hasChildCount(9)));

        onView(allOf(withId(R.id.meeting_recyclerView), isCompletelyDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));

        Thread.sleep(200);

        onView(allOf(withId(R.id.meeting_recyclerView), isCompletelyDisplayed())).check(matches(hasChildCount(8)));
    }

    @Test
    public void Meeting_shouldShowDetailOnClickOnMeeting() {

        onView(allOf(withId(R.id.meeting_recyclerView), isCompletelyDisplayed())).perform(actionOnItemAtPosition(0, click()));

        onView(allOf(withId(R.id.meetingtopic_detail))).check(matches(withText("Sujet : Réunion A")));

    }

}