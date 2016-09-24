package com.letgo.populartvshows.presentation.ui;

/**
 * @author diego.galico
 *
 * This interface represents a basic view. All views should implement these common methods.
 *
 */
public interface BaseView {

    /**
     * This is a general method used for showing some kind of progress during a background task.
     */
    void showProgress();

    /**
     * This is a general method used for hiding progress information after a background task finishes.
     */
    void hideProgress();

    /**
     * This method is used for showing error messages on the UI.
     *
     * @param message The error message to be displayed.
     */
    void showError(String message);
}
