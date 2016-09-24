package com.letgo.populartvshows.presentation.presenters;

import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.presentation.presenters.base.BasePresenter;
import com.letgo.populartvshows.presentation.ui.BaseView;
import com.letgo.populartvshows.presentation.ui.fragments.PopularTvShowsFragment;

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
        void showPopularTvShows(List<TvShow> tvShowList);

        /**
         * Return true if the list of tv shows is empty
         * @return
         */
        boolean isTheListEmpty();

        /**
         * Append new pagination of tv shows to actual tv shows list
         * @param tvShowList
         */
        void appendPopularTvShows(List<TvShow> tvShowList);
    }
}
