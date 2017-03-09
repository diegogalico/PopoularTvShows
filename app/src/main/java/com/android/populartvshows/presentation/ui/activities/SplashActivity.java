package com.android.populartvshows.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.android.populartvshows.R;
import com.android.populartvshows.presentation.ui.fragments.SplashFragment;

/**
 * @author diego.galico
 *
 * Activity in charge of showing splash screen
 *
 */
public class SplashActivity extends BaseActivity implements SplashFragment.NavigationHandler{
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

    @Override
    public void navigateToTvShowActivity() {
        Intent myIntent = new Intent(this, PopularTvShowsActivity.class);
        startActivity(myIntent);
        finish();
    }
}
