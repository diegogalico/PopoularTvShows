package com.letgo.populartvshows.presentation.presenters.base;

/**
 * @author diego.galico
 */
public interface BasePresenter {
    /**
     * Called when the presenter is initialized
     */
    void start();

    /**
     * Called when the presenter is on resumed, i.e when an activity
     * or a fragment is resumed
     */
    void resume();

    /**
     * Called when the presenter is on paused, i.e when an activity
     * or a fragment is paused
     */
    void pause();

    /**
     * Called when the presenter is stopped, i.e when an activity
     * or a fragment finishes
     */
    void stop();

    /**
     * Called when the presenter is on destoyed, i.e when an activity
     * or a fragment is destoyed
     */
    void destroy();

    /**
     * Method that should signal the appropriate view to show the appropriate error with the provided message.
     */
    void onError(String message);
}
