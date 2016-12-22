package com.dashlane.populartvshows.domain.interactors;


import com.dashlane.populartvshows.domain.executors.PostExecutionThread;
import com.dashlane.populartvshows.domain.executors.ThreadExecutor;
import com.dashlane.populartvshows.domain.interactors.base.Interactor;

/**
 * @author diego.galico
 *
 * SimilarTvShowsInteractor is an interface that define methods to obtain similar tv shows information
 *
 */
public abstract class SimilarTvShowsInteractor extends Interactor {

    protected SimilarTvShowsInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

}
