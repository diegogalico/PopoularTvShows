package com.letgo.populartvshows.presentation.ui.activities;

import android.os.Bundle;

import com.letgo.populartvshows.R;
import com.letgo.populartvshows.presentation.ui.fragments.TvShowDetailFragment;

/**
 * @author diego.galico
 */
public class TvShowDetailActivity extends SecondLevelActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addInfoFragment();

    }

    public void addInfoFragment() {
        TvShowDetailFragment tvShowDetailFragment = TvShowDetailFragment.newInstance();

        tvShowDetailFragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, tvShowDetailFragment)
                .commit();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_container;
    }

}
