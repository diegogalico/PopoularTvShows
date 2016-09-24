package com.letgo.populartvshows.presentation.presenters.impl;

import com.letgo.populartvshows.domain.interactors.SimilarTvShowsInteractor;
import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.presentation.presenters.SimilarTvShowsPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * @author diego.galico
 *
 * SimilarTvShowsPresenterImpl class is in charge of calling {@link SimilarTvShowsInteractor} to obtain similar tv shows response
 *
 */
public class SimilarTvShowsPresenterImpl implements SimilarTvShowsPresenter, SimilarTvShowsInteractor.SimilarTvShowsResponse {

    private SimilarTvShowsView mSimilarTvShowsView;
    private SimilarTvShowsInteractor mGetSimilarTvShowsInteractor;

    @Inject
    public SimilarTvShowsPresenterImpl(SimilarTvShowsInteractor getSimilarTvShowsInteractor) {
        mGetSimilarTvShowsInteractor = getSimilarTvShowsInteractor;
    }

    /**
     * Attach view to presenter
     * @param tvShowsView
     */
    public void attachView(SimilarTvShowsView tvShowsView) {
        mSimilarTvShowsView = tvShowsView;
    }

    @Override
    public void start() {
        mSimilarTvShowsView.showProgress();
        mGetSimilarTvShowsInteractor.setPresenter(this);
        mGetSimilarTvShowsInteractor.execute();
    }

    /**
     * Similar tv shows response
     * @param similarTvShowList
     */
    @Override
    public void onSimilarTvShowsResponse(List<TvShow> similarTvShowList) {
        mSimilarTvShowsView.hideProgress();
        mSimilarTvShowsView.showSimilarTvShows(similarTvShowList);
    }

    /**
     * Error response
     * @param error
     */
    @Override
    public void onErrorResponse(String error) {
        mSimilarTvShowsView.showError(error);
    }

    @Override
    public void stop() {

    }
}
