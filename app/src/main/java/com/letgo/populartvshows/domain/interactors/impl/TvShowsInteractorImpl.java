package com.letgo.populartvshows.domain.interactors.impl;

import com.letgo.populartvshows.domain.interactors.TvShowsInteractor;
import com.letgo.populartvshows.domain.model.rest.RestData;
import com.letgo.populartvshows.presentation.presenters.impl.PopularTvShowsPresenterImpl;

import javax.inject.Inject;

/**
 * @author diego.galico
 */
public class TvShowsInteractorImpl implements TvShowsInteractor {

    private int mCurrentPage = 1;
    private final RestData mRestData;
    private PopularTvShowsPresenterImpl mPopularTvShowsPresenter;

    @Inject
    public TvShowsInteractorImpl(RestData mRestData) {
        this.mRestData = mRestData;
    }

    @Override
    public void requestPopularTvShows() {
        mRestData.getTvShowsByPage(mCurrentPage, mPopularTvShowsPresenter);
    }

    public void setPresenter(PopularTvShowsPresenterImpl popularTvShowsPresenter) {
        this.mPopularTvShowsPresenter = popularTvShowsPresenter;
    }

    @Override
    public void execute() {
        requestPopularTvShows();
        mCurrentPage++;
    }
}
