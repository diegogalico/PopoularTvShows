package com.dashlane.populartvshows.presentation.presenters.base;

import android.support.annotation.NonNull;

/**
 * @author diego.galico
 *
 * Presenter factory class
 *
 */
public interface PresenterFactory<T extends BasePresenter> {

    /**
     * Create a new instance of a Presenter
     *
     * @return The Presenter instance
     */
    @NonNull
    T createPresenter();
}