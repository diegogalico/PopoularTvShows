package com.dashlane.populartvshows.presentation.ui.activities;

import android.os.Bundle;

import com.dashlane.populartvshows.R;
import com.dashlane.populartvshows.presentation.ui.fragments.SplashFragment;

/**
 * @author diego.galico
 *
 * Activity in charge of showing splash screen
 *
 */
public class SplashActivity extends BaseActivity {
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
