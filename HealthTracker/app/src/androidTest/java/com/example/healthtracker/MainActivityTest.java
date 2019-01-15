package com.example.healthtracker;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> mIntentsRule =
            new IntentsTestRule<>(MainActivity.class);

    @Test
    public void testOnCreate() {
        onView(allOf(withId(R.id.button3), withText("Finger Exercises")))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.button6), withText("Journal")))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.button2), withText("Reminders")))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.button4), withText("STOPWATCH")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testStopWatchClicked() {
    }

    @Test
    public void testOnFingerExerciseClick() {
        onView(withId(R.id.button3)).perform(click());
        intended(hasComponent(FingerExercises.class.getName()));
    }

    @Test
    public void testOnRemindersClick() {
    }

    @Test
    public void testOnJournalClick() {
    }
}