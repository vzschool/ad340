package com.example.ad340;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SingInActivityTest {

    @Rule
    public ActivityScenarioRule<SignInActivity> activityScenarioRule
            = new ActivityScenarioRule<>(SignInActivity.class);

    @Test
    public void testSignIn() {
        onView(withId(R.id.buttonSignIn)).perform(click());
    }
}
