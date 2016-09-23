package com.letgo.populartvshows.presentation.presenters.impl;

import com.letgo.populartvshows.domain.interactors.SimilarTvShowsInteractor;
import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.presentation.presenters.SimilarTvShowsPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * @author diego.galico
 */
public class SimilarTvShowsPresenterImpl implements SimilarTvShowsPresenter, SimilarTvShowsInteractor.SimilarTvShowsResponse {

    private SimilarTvShowsView mSimilarTvShowsView;
    private SimilarTvShowsInteractor mGetSimilarTvShowsInteractor;

    @Inject
    public SimilarTvShowsPresenterImpl(SimilarTvShowsInteractor getSimilarTvShowsInteractor) {
        mGetSimilarTvShowsInteractor = getSimilarTvShowsInteractor;
    }

    public void attachView(SimilarTvShowsView tvShowsView) {
        mSimilarTvShowsView = tvShowsView;
    }

    @Override
    public void start() {
        mSimilarTvShowsView.showProgress();
        mGetSimilarTvShowsInteractor.setPresenter(this);
        mGetSimilarTvShowsInteractor.execute();
    }

    @Override
    public void onSimilarTvShowsResponse(List<TvShow> similarTvShowList) {
        mSimilarTvShowsView.hideProgress();
        mSimilarTvShowsView.showSimilarTvShows(similarTvShowList);
    }

    @Override
    public void onErrorResponse(String error) {
        mSimilarTvShowsView.showError(error);
    }

    @Override
    public void stop() {

    }
}
