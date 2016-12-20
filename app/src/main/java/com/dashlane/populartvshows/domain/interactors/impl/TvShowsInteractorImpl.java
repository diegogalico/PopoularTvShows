package com.dashlane.populartvshows.domain.interactors.impl;

import com.dashlane.populartvshows.domain.interactors.TvShowsInteractor;
import com.dashlane.populartvshows.domain.model.entities.TvShowsWrapper;
import com.dashlane.populartvshows.domain.model.rest.RestData;
import com.dashlane.populartvshows.presentation.presenters.impl.PopularTvShowsPresenterImpl;

import javax.inject.Inject;

import rx.Observer;
import timber.log.Timber;

/**
 * @author diego.galico
 *
 * TvShowsInteractorImpl class is in charge of calling {@link RestData} to obtain popular tv shows response
 *
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
    public void setPage(int page) {
        mCurrentPage = 1;
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
        Timber.e(e, "onError");
    }

    @Override
    public void onNext(TvShowsWrapper tvShowsWrapper) {
        mPopularTvShowsPresenter.onPopularTvShowsResponse(tvShowsWrapper.getTvShowInfo());
    }
}
