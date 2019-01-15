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
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FingerExercisesTest {

    @Rule
    public IntentsTestRule<FingerExercises> mIntentsRule =
            new IntentsTestRule<>(FingerExercises.class);

    @Test
    public void testOnCreate() {

        //Test that there is text and a button on Finger Exercises page
        onView(allOf(withId(R.id.textView2), withText("Get those thumbs clicking!")))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.button), withText("Exercise!")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testFingerExercise() {

        //Test that things change when button is clicked
        for (int i = 1; i < 101; i++){
            onView(withId(R.id.button)).perform(click());

            if (i % 50 == 0 && i != 0){
                onView(allOf(withId(R.id.textView2), withText("Look at those slim fingers! " + i)))
                        .check(matches(isDisplayed()));
            } else if (i % 10 == 0){
                onView(allOf(withId(R.id.textView2), withText("The fat is melting away! " + i)))
                        .check(matches(isDisplayed()));
            } else {
                onView(allOf(withId(R.id.textView2), withText("Get those fingers clicking! " + i)))
                        .check(matches(isDisplayed()));
            }
        }
    }
}