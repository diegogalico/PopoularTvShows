package com.letgo.populartvshows.presentation.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.letgo.populartvshows.R;
import com.letgo.populartvshows.app.Constants;
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Constants.START_PAGINATION = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Constants.START_PAGINATION = false;
            }
            if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }
}
