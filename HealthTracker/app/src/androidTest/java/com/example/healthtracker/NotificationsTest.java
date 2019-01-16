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
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagKey;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NotificationsTest {

    @Rule
    public IntentsTestRule<Notifications> mIntentsRule =
            new IntentsTestRule<>(Notifications.class);

    @Test
    public void onCreate() {

        onView(allOf(withId(R.id.button5), withText("Enable Notifications")))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.textView4), withText("Set a reminder to drink water every 2 hours: ")))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.textView3), withText("Drinking water is vital to your health. Studies have shown that it can reduce hunger and increase hydration. "))).check(matches(isDisplayed()));
    }
}