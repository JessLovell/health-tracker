package com.example.healthtracker;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class StopWatchTest {

    @Rule
    public IntentsTestRule<StopWatch> mIntentsRule =
            new IntentsTestRule<>(StopWatch.class);

    @Test
    public void testOnCreate() {

        onView(allOf(withId(R.id.btStart), withText("Start")))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.btReset), withText("Reset")))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.tvTimer), withText("00:00:00.000")))
                .check(matches(isDisplayed()));
    }
}