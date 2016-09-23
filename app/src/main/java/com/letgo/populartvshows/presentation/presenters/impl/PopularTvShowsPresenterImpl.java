package com.letgo.populartvshows.presentation.presenters.impl;

import com.letgo.populartvshows.domain.interactors.TvShowsInteractor;
import com.letgo.populartvshows.domain.model.entities.TvShowsWrapper;
import com.letgo.populartvshows.presentation.presenters.PopularTvShowsPresenter;
import com.letgo.populartvshows.utils.ApiStatusCode;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;

/**
 * @author diego.galico
 */
public class PopularTvShowsPresenterImpl implements PopularTvShowsPresenter, Observer<TvShowsWrapper> {

    private PopularTvShowsView mTvShowsView;
    private boolean mIsLoading = false;
    private TvShowsInteractor mGetPopularTvShows;

    @Inject
    public PopularTvShowsPresenterImpl(TvShowsInteractor getPopularTvShows) {
        mGetPopularTvShows = getPopularTvShows;
    }

    public void attachView(PopularTvShowsView tvShowsView) {
        mTvShowsView = tvShowsView;
    }

    @Override
    public final void onCompleted() {
        // do nothing
    }

    @Override
    public final void onError(Throwable e) {
        try {
            mTvShowsView.showError(ApiStatusCode.getApiStatusByCode(((HttpException) e).code()));
        } catch (Exception exc) {
            mTvShowsView.showError(e.getMessage());
        }
    }

    @Override
    public final void onNext(TvShowsWrapper tvShowsWrapper) {

        if (mTvShowsView.isTheListEmpty()) {
            mTvShowsView.hideProgress();
            mTvShowsView.showPopularTvShows(tvShowsWrapper.getTvShowInfo());
        } else {
            mTvShowsView.appendPopularTvShows(tvShowsWrapper.getTvShowInfo());
        }
        mIsLoading = false;
    }

    @Override
    public void start() {
        if (mTvShowsView.isTheListEmpty()) {
            mTvShowsView.showProgress();
            mGetPopularTvShows.setPresenter(this);
            mGetPopularTvShows.execute();
        }
    }

    public void showMoreTvShows() {
        mGetPopularTvShows.execute();
        mTvShowsView.showProgress();
        mIsLoading = true;
    }

    public boolean isLoading() {

        return mIsLoading;
    }

    public void setLoading(boolean isLoading) {

        this.mIsLoading = isLoading;
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
