package com.letgo.populartvshows.domain.interactors.impl;

import com.letgo.populartvshows.domain.interactors.TvShowsInteractor;
import com.letgo.populartvshows.domain.model.entities.TvShowsWrapper;
import com.letgo.populartvshows.domain.model.rest.RestData;
import com.letgo.populartvshows.presentation.presenters.impl.PopularTvShowsPresenterImpl;

import javax.inject.Inject;

import rx.Observer;

/**
 * @author diego.galico
 */
public class TvShowsInteractorImpl implements TvShowsInteractor, Observer<TvShowsWrapper> {

    private int mCurrentPage = 1;
    private final RestData mRestData;
    private PopularTvShowsPresenterImpl mPopularTvShowsPresenter;

    @Inject
    public TvShowsInteractorImpl(RestData mRestData) {
        this.mRestData = mRestData;
    }

    @Override
    public void requestPopularTvShows() {
        mRestData.getTvShowsByPage(mCurrentPage, this);
    }

    @Override
    public void setPresenter(PopularTvShowsPresenterImpl presenter) {
        mPopularTvShowsPresenter = presenter;
    }

    @Override
    public void execute() {
        requestPopularTvShows();
        mCurrentPage++;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        mPopularTvShowsPresenter.onErrorResponse(e.getMessage());
    }

    @Override
    public void onNext(TvShowsWrapper tvShowsWrapper) {
        mPopularTvShowsPresenter.onPopularTvShowsResponse(tvShowsWrapper.getTvShowInfo());
    }
}
