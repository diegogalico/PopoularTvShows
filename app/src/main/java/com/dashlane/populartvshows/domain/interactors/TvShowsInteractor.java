package com.dashlane.populartvshows.domain.interactors;


import com.dashlane.populartvshows.domain.executors.PostExecutionThread;
import com.dashlane.populartvshows.domain.executors.ThreadExecutor;
import com.dashlane.populartvshows.domain.interactors.base.Interactor;
import com.dashlane.populartvshows.presentation.presenters.impl.PopularTvShowsPresenterImpl;

/**
 * @author diego.galico
 *
 * TvShowsInteractor is an interface that define methods to obtain popular tv shows information
 *
 */
public abstract class TvShowsInteractor extends Interactor {

    protected TvShowsInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    /**
     * Set page variable in order to return first tv show pagination to {@link PopularTvShowsPresenterImpl} class
     * @param page
     */
    public abstract void setPage(int page);

    /**
     * Set page variable in order to return first tv show pagination to {@link PopularTvShowsPresenterImpl} class
     *
     */
    public abstract void addPageNumber();

}
