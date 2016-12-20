package com.dashlane.populartvshows.presentation.presenters;

import com.dashlane.populartvshows.presentation.presenters.base.BasePresenter;
import com.dashlane.populartvshows.presentation.ui.BaseView;
import com.dashlane.populartvshows.presentation.ui.fragments.SplashFragment;

/**
 * @author diego.galico
 *
 * ConfigurationPresenter is an interface that define methods to obtain configuration information
 *
 */
public interface ConfigurationPresenter extends BasePresenter {

    /**
     * Interface implemented by {@link SplashFragment} class
     */
    interface ConfigurationView extends BaseView {

        /**
         * Start Popular tv shows Activity
         */
        void startPopularTvShowsActivity();
    }
}
