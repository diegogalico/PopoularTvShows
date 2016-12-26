package com.dashlane.populartvshows.presentation.presenters;

import com.dashlane.populartvshows.presentation.models.TvShowModel;
import com.dashlane.populartvshows.presentation.presenters.base.BasePresenter;
import com.dashlane.populartvshows.presentation.ui.BaseView;
import com.dashlane.populartvshows.presentation.ui.fragments.PopularTvShowsFragment;

import java.util.List;

/**
 * @author diego.galico
 *
 * PopularTvShowsPresenter is an interface that define methods to obtain popular tv shows information
 *
 */
public interface PopularTvShowsPresenter extends BasePresenter {

    /**
     * Interface implemented by {@link PopularTvShowsFragment} class
     */
    interface PopularTvShowsView extends BaseView {

        /**
         * Show popular tv shows
         * @param tvShowList
         */
        void showPopularTvShows(List<TvShowModel> tvShowList);

        /**
         * Return true if the list of tv shows is empty
         * @return
         */
        boolean isTheListEmpty();

        /**
         * Append new pagination of tv shows to actual tv shows list
         * @param tvShowList
         */
        void appendPopularTvShows(List<TvShowModel> tvShowList);
    }
}
