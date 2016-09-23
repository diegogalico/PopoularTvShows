package com.letgo.populartvshows.presentation.presenters.impl;

import com.letgo.populartvshows.domain.interactors.TvShowsInteractor;
import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.presentation.presenters.PopularTvShowsPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * @author diego.galico
 */
public class PopularTvShowsPresenterImpl implements PopularTvShowsPresenter, TvShowsInteractor.PopularTvShowsResponse {

    private PopularTvShowsView mTvShowsView;
    private boolean mIsLoading = false;
    private TvShowsInteractor mGetPopularTvShowsInteractor;

    @Inject
    public PopularTvShowsPresenterImpl(TvShowsInteractor getPopularTvShowsInteractor) {
        mGetPopularTvShowsInteractor = getPopularTvShowsInteractor;
    }

    public void attachView(PopularTvShowsView tvShowsView) {
        mTvShowsView = tvShowsView;
    }

    @Override
    public void start() {
        if (mTvShowsView.isTheListEmpty()) {
            mTvShowsView.showProgress();
            mGetPopularTvShowsInteractor.setPresenter(this);
            mGetPopularTvShowsInteractor.execute();
        }
    }

    @Override
    public void onPopularTvShowsResponse(List<TvShow> popularTvShowList) {
        if (mTvShowsView.isTheListEmpty()) {
            mTvShowsView.hideProgress();
            mTvShowsView.showPopularTvShows(popularTvShowList);
        } else {
            mTvShowsView.appendPopularTvShows(popularTvShowList);
        }
        mIsLoading = false;
    }

    @Override
    public void onErrorResponse(String error) {
        mTvShowsView.hideProgress();
        mTvShowsView.showError(error);
    }

    @Override
    public void stop() {
    }

    public void showMoreTvShows() {
        mGetPopularTvShowsInteractor.execute();
        mIsLoading = true;
    }

    public boolean isLoading() {

        return mIsLoading;
    }

    public void setLoading(boolean isLoading) {

        this.mIsLoading = isLoading;
    }


}
