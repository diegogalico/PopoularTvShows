package com.letgo.populartvshows.presentation.presenters.impl;

import android.util.Log;

import com.letgo.populartvshows.domain.interactors.SimilarTvShowsInteractor;
import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.domain.model.entities.TvShowsWrapper;
import com.letgo.populartvshows.presentation.presenters.SimilarTvShowsPresenter;

import javax.inject.Inject;

import rx.Observer;

/**
 * @author diego.galico
 */
public class SimilarTvShowsPresenterImpl implements SimilarTvShowsPresenter, Observer<TvShowsWrapper> {

    private SimilarTvShowsView mSimilarTvShowsView;
    private SimilarTvShowsInteractor mGetSimilarTvShows;

    @Inject
    public SimilarTvShowsPresenterImpl(SimilarTvShowsInteractor getSimilarTvShows) {
        mGetSimilarTvShows = getSimilarTvShows;
    }

    public void attachView(SimilarTvShowsView tvShowsView) {
        mSimilarTvShowsView = tvShowsView;
    }

    @Override
    public final void onCompleted() {
        // do nothing
    }

    @Override
    public final void onError(Throwable e) {
        Log.e("TvShowDetail", e.getMessage());
    }

    @Override
    public void onNext(TvShowsWrapper tvShowsWrapper) {
        mSimilarTvShowsView.hideProgress();
    }

    @Override
    public void start() {
        mSimilarTvShowsView.showProgress();
        mGetSimilarTvShows.setPresenter(this);
        mGetSimilarTvShows.execute();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }
}
