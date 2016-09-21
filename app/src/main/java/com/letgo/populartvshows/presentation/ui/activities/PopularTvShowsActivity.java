package com.letgo.populartvshows.presentation.ui.activities;

import android.os.Bundle;

import com.letgo.populartvshows.R;
import com.letgo.populartvshows.presentation.presenters.PopularTvShowsPresenter.View;
import com.letgo.populartvshows.presentation.ui.fragments.SplashFragment;

public class PopularTvShowsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            addInfoFragment();
        }
    }

    public void addInfoFragment() {
        SplashFragment splashFragment = SplashFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, splashFragment)
                .commit();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_container;
    }

}
