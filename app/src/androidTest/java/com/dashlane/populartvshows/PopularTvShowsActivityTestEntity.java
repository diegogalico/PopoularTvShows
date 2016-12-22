package com.dashlane.populartvshows;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.dashlane.populartvshows.presentation.ui.activities.PopularTvShowsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;

/**
 * @author diego.galico
 *
 * PopularTvShowsActivityTestEntity test class
 *
 */
@RunWith(AndroidJUnit4.class)
public class PopularTvShowsActivityTestEntity {

    @Rule
    public ActivityTestRule<PopularTvShowsActivity> mActivityRule =
            new ActivityTestRule<>(PopularTvShowsActivity.class);

    @Test
    public void testRecyclerViewIsShown() {

        onView(withId(R.id.recycler_view_popular_tv_shows))
                .check(matches(isDisplayed()));

        onView(withId(R.id.progress_bar))
                .check(matches(not(isDisplayed())));

    }

    @Test
    public void testDetailActivityOpen () {

        onView(withId(R.id.recycler_view_popular_tv_shows)).perform(
                RecyclerViewActions.actionOnItemAtPosition(2, ViewActions.click()));

        onView(withId(R.id.scroll_view_detail))
                .check(matches(isDisplayed()));
    }

}
