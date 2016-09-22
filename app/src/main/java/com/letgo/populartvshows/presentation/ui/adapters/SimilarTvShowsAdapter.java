package com.letgo.populartvshows.presentation.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.presentation.ui.fragments.TvShowDetailFragment;

import java.util.List;

/**
 * @author diego.galico
 */
public class SimilarTvShowsAdapter extends FragmentStatePagerAdapter {

    private static int NUM_SIMILAR_TV_SHOWS = 0;
    private List<TvShow> mTvShows;

    public SimilarTvShowsAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setSimilarTvShowsCount(int similarTvShowsCount) {
        NUM_SIMILAR_TV_SHOWS = similarTvShowsCount;
    }

    public void setSimilarTvShows(List<TvShow> tvShows) {
        mTvShows = tvShows;
    }

    @Override
    public int getCount() {
        return NUM_SIMILAR_TV_SHOWS;
    }

    @Override
    public Fragment getItem(int position) {
        return TvShowDetailFragment.newInstance(mTvShows.get(position).getId());
    }
}