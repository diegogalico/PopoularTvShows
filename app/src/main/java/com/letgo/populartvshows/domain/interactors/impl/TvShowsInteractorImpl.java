package com.letgo.populartvshows.domain.interactors.impl;

import com.letgo.populartvshows.domain.executor.Executor;
import com.letgo.populartvshows.domain.executor.MainThread;
import com.letgo.populartvshows.domain.interactors.TvShowsInteractor;
import com.letgo.populartvshows.domain.interactors.base.AbstractInteractor;
import com.letgo.populartvshows.domain.repository.Repository;

/**
 * @author diego.galico
 *
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class TvShowsInteractorImpl extends AbstractInteractor implements TvShowsInteractor {

    private TvShowsInteractor.Callback mCallback;
    private Repository                mRepository;

    public TvShowsInteractorImpl(Executor threadExecutor,
                                MainThread mainThread,
                                Callback callback, Repository repository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mRepository = repository;
    }

    @Override
    public void run() {
        // TODO: Implement this with your business logic
    }
}
