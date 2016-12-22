package com.dashlane.populartvshows.presentation.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dashlane.populartvshows.data.entities.TvShowEntity;
import com.dashlane.populartvshows.domain.TvShowData;
import com.dashlane.populartvshows.presentation.ui.fragments.TvShowDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diego.galico
 *
 * SimilarTvShowsAdapter is an adapter class in charge of showing similar tv shows items
 *
 */
public class SimilarTvShowsAdapter extends FragmentStatePagerAdapter {

    private List<TvShowData> mTvShows;

    /**
     * Add selected tv show ro tv show list
     * @param fm
     * @param selectedTvShow
     */
    public SimilarTvShowsAdapter(FragmentManager fm, TvShowData selectedTvShow) {
        super(fm);
        mTvShows = new ArrayList<>();
        mTvShows.add(0, selectedTvShow);
    }

    /**
     * Append similar tv shows to actual list
     * @param tvShows
     */
    public void setSimilarTvShows(List<TvShowData> tvShows) {
            mTvShows.addAll(tvShows);
            notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTvShows.size();
    }

    /**
     * Get item due to position
     * @param position
     * @return TvShowDetailFragment instance
     */
    @Override
    public Fragment getItem(int position) {
        return TvShowDetailFragment.newInstance(mTvShows.get(position));
    }

}