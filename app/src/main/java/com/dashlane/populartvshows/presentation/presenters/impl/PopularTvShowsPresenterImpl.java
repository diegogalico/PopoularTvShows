package com.dashlane.populartvshows.presentation.presenters.impl;

import com.dashlane.populartvshows.domain.exception.DefaultErrorBundle;
import com.dashlane.populartvshows.domain.exception.ErrorBundle;
import com.dashlane.populartvshows.domain.interactors.DefaultSubscriber;
import com.dashlane.populartvshows.domain.TvShowData;
import com.dashlane.populartvshows.domain.interactors.TvShowsInteractor;
import com.dashlane.populartvshows.presentation.app.Constants;
import com.dashlane.populartvshows.presentation.exception.ErrorMessageFactory;
import com.dashlane.populartvshows.presentation.presenters.PopularTvShowsPresenter;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * @author diego.galico
 *
 * PopularTvShowsPresenterImpl class is in charge of calling {@link TvShowsInteractor} to obtain tv shows response
 *
 */
public class PopularTvShowsPresenterImpl implements PopularTvShowsPresenter {

    private PopularTvShowsView mTvShowsView;
    private TvShowsInteractor mGetPopularTvShowsInteractor;
    private boolean mIsLoading = false;
    private boolean mUpdateTvShowsList = false;
    private static final int PAGE_NUMBER = 1;

    @Inject
    public PopularTvShowsPresenterImpl(TvShowsInteractor getPopularTvShowsInteractor) {
        mGetPopularTvShowsInteractor = getPopularTvShowsInteractor;
    }

    /**
     * Attach view to presenter
     * @param tvShowsView
     */
    public void attachView(PopularTvShowsView tvShowsView) {
        mTvShowsView = tvShowsView;
    }

    @Override
    public void start() {
        mUpdateTvShowsList = false;
        if(Constants.START_PAGINATION){
            mGetPopularTvShowsInteractor.setPage(PAGE_NUMBER);
        }
        if (mTvShowsView.isTheListEmpty()) {
            mTvShowsView.showProgress();
            mGetPopularTvShowsInteractor.execute(new PopularTvShowsPresenterImpl.PopularTvShowsSubscriber());
        }
    }

    public void updateTvShowsList(){
        mUpdateTvShowsList = true;
        mGetPopularTvShowsInteractor.setPage(PAGE_NUMBER);
        mGetPopularTvShowsInteractor.execute(new PopularTvShowsPresenterImpl.PopularTvShowsSubscriber());
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(mTvShowsView.getContext(),
                errorBundle.getException());
        mTvShowsView.showError(errorMessage);
    }

    private final class PopularTvShowsSubscriber extends DefaultSubscriber<List<TvShowData>> {

        @Override public void onCompleted() {
            mTvShowsView.hideProgress();
        }

        /**
         * Error response
         * @param e
         */
        @Override public void onError(Throwable e) {
            mTvShowsView.hideProgress();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
            Timber.e(e, "onError");
        }

        /**
         * Popular tv shows response
         * @param popularTvShowList
         */
        @Override public void onNext(List<TvShowData> popularTvShowList) {
            mGetPopularTvShowsInteractor.addPageNumber();
            if(mUpdateTvShowsList){
                mTvShowsView.showPopularTvShows(popularTvShowList);
            }else{
                if (mTvShowsView.isTheListEmpty()) {
                    mTvShowsView.hideProgress();
                    mTvShowsView.showPopularTvShows(popularTvShowList);
                } else {
                    mTvShowsView.appendPopularTvShows(popularTvShowList);
                }
            }
            mIsLoading = false;
        }
    }


    @Override
    public void stop() {
    }

    @Override
    public void destroy() {
        mGetPopularTvShowsInteractor.unsubscribe();
        mTvShowsView = null;
    }

    public void showMoreTvShows() {
        mUpdateTvShowsList = false;
        mGetPopularTvShowsInteractor.execute(new PopularTvShowsPresenterImpl.PopularTvShowsSubscriber());
        mIsLoading = true;
    }

    public boolean isLoading() {

        return mIsLoading;
    }

    public void setLoading(boolean isLoading) {

        this.mIsLoading = isLoading;
    }


}
