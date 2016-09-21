package com.letgo.populartvshows.presentation.presenters.impl;

import com.letgo.populartvshows.domain.executor.Executor;
import com.letgo.populartvshows.domain.executor.MainThread;
import com.letgo.populartvshows.domain.interactors.SampleInteractor;
import com.letgo.populartvshows.presentation.presenters.base.AbstractPresenter;
import com.letgo.populartvshows.presentation.presenters.PopularTvShowsPresenter;

/**
 * @author diego.galico
 */
public class PopularTvShowsPresenterImpl extends AbstractPresenter implements PopularTvShowsPresenter,
        SampleInteractor.Callback {

    private PopularTvShowsPresenter.View mView;

    public PopularTvShowsPresenterImpl(Executor executor,
                             MainThread mainThread,
                             View view) {
        super(executor, mainThread);
        mView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }
}
