package com.dashlane.populartvshows.presentation.presenters.impl;

import com.dashlane.populartvshows.domain.TvShowData;
import com.dashlane.populartvshows.domain.exception.DefaultErrorBundle;
import com.dashlane.populartvshows.domain.exception.ErrorBundle;
import com.dashlane.populartvshows.domain.interactors.DefaultSubscriber;
import com.dashlane.populartvshows.domain.interactors.SimilarTvShowsInteractor;
import com.dashlane.populartvshows.presentation.exception.ErrorMessageFactory;
import com.dashlane.populartvshows.presentation.presenters.SimilarTvShowsPresenter;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * @author diego.galico
 *
 * SimilarTvShowsPresenterImpl class is in charge of calling {@link SimilarTvShowsInteractor} to obtain similar tv shows response
 *
 */
public class SimilarTvShowsPresenterImpl implements SimilarTvShowsPresenter {

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
        mGetSimilarTvShowsInteractor.execute(new SimilarTvShowsPresenterImpl.SimilarTvShowsSubscriber());
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(mSimilarTvShowsView.getContext(),
                errorBundle.getException());
        mSimilarTvShowsView.showError(errorMessage);
    }

    private final class SimilarTvShowsSubscriber extends DefaultSubscriber<List<TvShowData>> {

        @Override public void onCompleted() {
            mSimilarTvShowsView.hideProgress();
        }

        /**
         * Error response
         * @param e
         */
        @Override public void onError(Throwable e) {
            mSimilarTvShowsView.hideProgress();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
            Timber.e(e, "onError");
        }

        /**
         * Similar tv shows response
         * @param similarTvShowList
         */
        @Override public void onNext(List<TvShowData> similarTvShowList) {
            mSimilarTvShowsView.hideProgress();
            mSimilarTvShowsView.showSimilarTvShows(similarTvShowList);
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {
        mGetSimilarTvShowsInteractor.unsubscribe();
        mSimilarTvShowsView = null;
    }
}
