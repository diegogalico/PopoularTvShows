package com.dashlane.populartvshows.presentation.presenters.base;

/**
 * @author diego.galico
 */
public interface BasePresenter {

    /**
     * Called when the presenter is initialized
     */
    void start();

    /**
     * Called when the presenter is stopped, i.e when an activity
     * or a fragment finishes
     */
    void stop();

}
