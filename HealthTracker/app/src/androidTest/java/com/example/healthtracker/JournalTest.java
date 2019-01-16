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
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class JournalTest {

    @Rule
    public IntentsTestRule<Journal> mIntentsRule =
            new IntentsTestRule<>(Journal.class);

    @Test
    public void onCreate() {

        //Check to the fields on the page
        onView(allOf(withId(R.id.textView6), withText("Your Exercise Journal")))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.editText4), withHint("Journal Title")))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.editText5), withHint("Quantity")))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.editText6), withHint("Exercise Description")))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.button7), withText("Add Journal Entry")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void addJournalEntry() {

        //check that the page is refreshed on button click
        onView(withId(R.id.button7)).perform(click());
        intended(hasComponent(Journal.class.getName()));
    }
}