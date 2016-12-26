package com.dashlane.populartvshows.presentation.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.dashlane.populartvshows.R;
import com.dashlane.populartvshows.presentation.app.Constants;
import com.dashlane.populartvshows.presentation.models.TvShowModel;
import com.dashlane.populartvshows.presentation.ui.fragments.PopularTvShowsFragment;
import com.google.gson.Gson;

/**
 * @author diego.galico
 *
 * Activity in charge of showing popular tv shows
 *
 */
public class PopularTvShowsActivity extends BaseActivity implements PopularTvShowsFragment.OnTvShowListener{

    private static final String TV_SHOW_OBJECT = "tv_show_object";

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

    @Override
    public void onTvShowClicked(TvShowModel tvShow) {
        Intent tvShowDetailActivityIntent = new Intent(this, TvShowDetailActivity.class);

        // Send tvShowObject via intent
        tvShowDetailActivityIntent.putExtra(TV_SHOW_OBJECT, new Gson().toJson(tvShow));

        startActivityForResult(tvShowDetailActivityIntent, 1);

        // Left to right transition animation when activity started
        overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
    }
}
