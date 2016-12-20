package com.dashlane.populartvshows;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.dashlane.populartvshows.presentation.ui.activities.SplashActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * @author diego.galico
 *
 * SplashActivityTest test class
 *
 */
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest  {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityRule =
            new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void ensureSplashTextProgressIsDisplayed() {
        // Type text and then press the button.
        onView(withId(R.id.text_view_splash_title))
                .check(matches(withText("Popular Tv Shows")));
    }

}
