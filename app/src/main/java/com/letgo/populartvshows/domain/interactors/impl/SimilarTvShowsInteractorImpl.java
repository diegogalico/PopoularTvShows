package com.letgo.populartvshows.domain.interactors.impl;

import com.letgo.populartvshows.domain.interactors.SimilarTvShowsInteractor;
import com.letgo.populartvshows.domain.model.entities.TvShowsWrapper;
import com.letgo.populartvshows.domain.model.rest.RestData;
import com.letgo.populartvshows.presentation.presenters.impl.SimilarTvShowsPresenterImpl;

import javax.inject.Inject;

import rx.Observer;
import timber.log.Timber;

/**
 * @author diego.galico
 *
 * SimilarTvShowsInteractorImpl class is in charge of calling {@link RestData} to obtain similar tv shows response
 *
 */
public class SimilarTvShowsInteractorImpl implements SimilarTvShowsInteractor, Observer<TvShowsWrapper> {

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
        mRestData.getSimilarTvShows(this, tvShowId);
    }

    @Override
    public void setPresenter(SimilarTvShowsPresenterImpl presenter) {
        mSimilarTvShowsPresenter = presenter;
    }

    @Override
    public void execute() {
        requestSimilarTvShowsDetail(mTvShowId);
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        mSimilarTvShowsPresenter.onErrorResponse(e.getMessage());
        Timber.e(e, "onError");
    }

    @Override
    public void onNext(TvShowsWrapper tvShowsWrapper) {
        mSimilarTvShowsPresenter.onSimilarTvShowsResponse(tvShowsWrapper.getTvShowInfo());
        Timber.d(tvShowsWrapper.toString());
    }
}
