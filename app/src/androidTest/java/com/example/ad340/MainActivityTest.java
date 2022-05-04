package com.example.ad340;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testTextInput() {
        onView(withId(R.id.editTextName)).perform(typeText("Vladislav Zakharov"));
        onView(withId(R.id.editTextName)).check(matches(withText("Vladislav Zakharov")));
        onView(withId(R.id.editTextDescription)).perform(typeText("human being"));
        onView(withId(R.id.editTextDescription)).check(matches(withText("human being")));
        onView(withId(R.id.editTextOccupation)).perform(typeText("developer"));
        onView(withId(R.id.editTextOccupation)).check(matches(withText("developer")));
    }

}
