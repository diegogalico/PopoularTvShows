package com.letgo.populartvshows.presentation.ui.activities;

import android.os.Bundle;

import com.letgo.populartvshows.R;
import com.letgo.populartvshows.presentation.ui.fragments.PopularTvShowsFragment;

/**
 * @author diego.galico
 *
 * Activity in charge of showing popular tv shows
 *
 */
public class PopularTvShowsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            addInfoFragment();
        }
    }

    public void addInfoFragment() {
        PopularTvShowsFragment popularTvShowsFragment = PopularTvShowsFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, popularTvShowsFragment)
                .commit();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_container;
    }

}
