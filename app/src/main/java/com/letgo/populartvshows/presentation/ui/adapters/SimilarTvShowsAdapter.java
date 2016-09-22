package com.letgo.populartvshows.presentation.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.presentation.ui.fragments.TvShowDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diego.galico
 */
public class SimilarTvShowsAdapter extends FragmentStatePagerAdapter {

    private List<TvShow> mTvShows;

    public SimilarTvShowsAdapter(FragmentManager fm, TvShow selectedTvShow) {
        super(fm);
        mTvShows = new ArrayList<>();
        mTvShows.add(0, selectedTvShow);
    }

    public void setSimilarTvShows(List<TvShow> tvShows) {
            mTvShows.addAll(tvShows);
            notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTvShows.size();
    }

    @Override
    public Fragment getItem(int position) {
        return TvShowDetailFragment.newInstance(mTvShows.get(position));
    }

}