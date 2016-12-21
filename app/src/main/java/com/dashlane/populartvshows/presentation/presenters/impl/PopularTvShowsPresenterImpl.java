package com.dashlane.populartvshows.presentation.presenters.impl;

import com.dashlane.populartvshows.presentation.app.Constants;
import com.dashlane.populartvshows.domain.interactors.TvShowsInteractor;
import com.dashlane.populartvshows.data.entities.TvShow;
import com.dashlane.populartvshows.presentation.presenters.PopularTvShowsPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * @author diego.galico
 *
 * PopularTvShowsPresenterImpl class is in charge of calling {@link TvShowsInteractor} to obtain tv shows response
 *
 */
public class PopularTvShowsPresenterImpl implements PopularTvShowsPresenter, TvShowsInteractor.PopularTvShowsResponse {

    private PopularTvShowsView mTvShowsView;
    private boolean mIsLoading = false;
    private TvShowsInteractor mGetPopularTvShowsInteractor;
    private boolean updateTvShowsList = false;
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
        updateTvShowsList = false;
        if(Constants.START_PAGINATION){
            mGetPopularTvShowsInteractor.setPage(PAGE_NUMBER);
        }
        if (mTvShowsView.isTheListEmpty()) {
            mTvShowsView.showProgress();
            mGetPopularTvShowsInteractor.setPresenter(this);
            mGetPopularTvShowsInteractor.execute();
        }
    }

    public void updateTvShowsList(){
        updateTvShowsList = true;
        mGetPopularTvShowsInteractor.setPage(PAGE_NUMBER);
        mGetPopularTvShowsInteractor.setPresenter(this);
        mGetPopularTvShowsInteractor.execute();
    }

    /**
     * Popular tv shows response
     * @param popularTvShowList
     */
    @Override
    public void onPopularTvShowsResponse(List<TvShow> popularTvShowList) {
        if(updateTvShowsList){
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

    /**
     * Error response
     * @param error
     */
    @Override
    public void onErrorResponse(String error) {
        mTvShowsView.hideProgress();
        mTvShowsView.showError(error);
    }

    @Override
    public void stop() {
    }

    public void showMoreTvShows() {
        updateTvShowsList = false;
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
