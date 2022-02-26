package com.bonboncompany.p4.ui.list;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.app.Dialog;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.bonboncompany.p4.R;
import com.bonboncompany.p4.data.model.Room;
import com.bonboncompany.p4.ui.list.dialogfilter.CustomRoomSpinnerDialog;

@RunWith(AndroidJUnit4.class)
public class MeetingActivityTest {

    private MeetingActivity actualActivityTest;

    @Before
    public void setUp() throws Exception {

        ActivityScenario<MeetingActivity> mainActivity = ActivityScenario.launch(MeetingActivity.class);
        mainActivity.onActivity(activity -> actualActivityTest = activity);

    }

    @Test
    public void Meeting_shouldNotBeEmpty() {
        onView(allOf(withId(R.id.meeting_recyclerView), isCompletelyDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void Meeting_shouldFilterOnClickOnFiltre() throws InterruptedException {

        onView(allOf(withId(R.id.filter_room), isCompletelyDisplayed())).perform(click());

        onView(withId(R.id.spinner_room_filter)).perform(click());

        // click on spinner on dialog
        onData(allOf(is(instanceOf(Room.class)),
                is(Room.ZELDA)))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());

        onView(withId(R.id.spinner_room_filter)).check(matches(withSpinnerText(containsString("Zelda"))));

        onView(withId(R.id.button_ok)).perform(click());

        Thread.sleep(200);

        onView(allOf(withId(R.id.meeting_recyclerView), isCompletelyDisplayed())).check(matches(hasChildCount(2)));
    }

    @Test
    public void Meeting_shouldrefreshfilter() throws InterruptedException {

        onView(allOf(withId(R.id.filter_room), isCompletelyDisplayed())).perform(click());

        onView(withId(R.id.spinner_room_filter)).perform(click());

        // click on spinner on dialog
        onData(allOf(is(instanceOf(Room.class)),
                is(Room.LUIGI)))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());

        onView(withId(R.id.spinner_room_filter)).check(matches(withSpinnerText(containsString("Luigi"))));

        onView(withId(R.id.button_ok)).perform(click());

        Thread.sleep(200);

        onView(allOf(withId(R.id.meeting_recyclerView), isCompletelyDisplayed())).check(matches(hasChildCount(1)));

        onView(allOf(withId(R.id.refresh_all), isCompletelyDisplayed())).perform(click());

        Thread.sleep(200);

        onView(allOf(withId(R.id.meeting_recyclerView), isCompletelyDisplayed())).check(matches(hasChildCount(8)));
    }

    @Test
    public void Meeting_OnclickedshouldAddNewMeeting() throws InterruptedException {

        onView(withId(R.id.main_fab_add)).perform(click());

        onView(withId(R.id.addmeetingtopic)).perform(replaceText("kong"), closeSoftKeyboard());

        onView(withId(R.id.addparticipantmail)).perform(replaceText("kong@gmail.com")
                , closeSoftKeyboard());

        onView(withId(R.id.spinnerRoom)).perform(click());

        onData(allOf(is(instanceOf(Room.class)), is(Room.DIDDY))).perform(click());

        onView(withId(R.id.spinnerRoom)).check(matches(withSpinnerText(containsString("Diddy"))));

        onView(withId(R.id.addButton)).perform(click());

        onView(allOf(withId(R.id.meeting_recyclerView), isCompletelyDisplayed())).perform(scrollToPosition(8));

        onView(allOf(withId(R.id.item_list_mail_participant), withText("kong@gmail.com"),
                withParent(withParent(withId(R.id.meeting_recyclerView))),
                isDisplayed())).check(matches(withText("kong@gmail.com")));
    }

    @Test
    public void Meeting_deleteAction_shouldRemoveItem() throws InterruptedException {

        onView((withId(R.id.meeting_recyclerView))).check(matches(hasChildCount(8)));

        onView(allOf(withId(R.id.meeting_recyclerView), isCompletelyDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
    }

    @Test
    public void Meeting_shouldShowDetailOnClickOnMeeting() {

        onView(allOf(withId(R.id.meeting_recyclerView), isCompletelyDisplayed())).perform(actionOnItemAtPosition(0, click()));

        onView(allOf(withId(R.id.meetingtopic_detail))).check(matches(withText("Sujet : Réunion A")));
    }

}