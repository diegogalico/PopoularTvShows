package com.android.populartvshows.presentation.ui.activities;


import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import com.android.populartvshows.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;

/**
 * @author diego.galico
 *
 * PopularTvShowsActivityTest test class
 *
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class PopularTvShowsActivityTest {

    @Rule
    public ActivityTestRule<PopularTvShowsActivity> mActivityTestRule = new ActivityTestRule<>(PopularTvShowsActivity.class);

    @Test
    public void testRecyclerViewIsShown() {

        onView(withId(R.id.recycler_view_popular_tv_shows))
                .check(matches(isDisplayed()));

        onView(withId(R.id.progress_bar))
                .check(matches(not(isDisplayed())));

    }

    @Test
    public void testPopularTvShowRecyclerViewItemCount() {
        onView(withId(R.id.recycler_view_popular_tv_shows)).
                check(new RecyclerViewItemCountAssertion(20));
    }

    private class RecyclerViewItemCountAssertion implements ViewAssertion {
        private final int expectedCount;

        public RecyclerViewItemCountAssertion(int expectedCount) {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            assertThat(adapter.getItemCount(), is(expectedCount));
        }
    }
}
