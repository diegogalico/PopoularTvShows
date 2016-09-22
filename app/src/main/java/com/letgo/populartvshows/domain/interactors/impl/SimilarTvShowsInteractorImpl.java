package com.letgo.populartvshows.domain.interactors.impl;

import com.letgo.populartvshows.domain.interactors.SimilarTvShowsInteractor;
import com.letgo.populartvshows.domain.model.rest.RestData;
import com.letgo.populartvshows.presentation.presenters.impl.SimilarTvShowsPresenterImpl;

import javax.inject.Inject;

/**
 * @author diego.galico
 */
public class SimilarTvShowsInteractorImpl implements SimilarTvShowsInteractor {

    private final RestData mRestData;
    private final int mTvShowId;
    private SimilarTvShowsPresenterImpl mSimilarTvShowsPresenter;

    @Inject
    public SimilarTvShowsInteractorImpl(RestData mRestData, int tvShowId) {
        this.mRestData = mRestData;
        this.mTvShowId = tvShowId;
    }

    @Override
    public void requestSimilarTvShowsDetail(int tvShowId) {
        mRestData.getSimilarTvShows(mSimilarTvShowsPresenter, tvShowId);
    }

    public void setPresenter(SimilarTvShowsPresenterImpl similarTvShowPresenter) {
        this.mSimilarTvShowsPresenter = similarTvShowPresenter;
    }

    @Override
    public void execute() {
        requestSimilarTvShowsDetail(mTvShowId);
    }
}
