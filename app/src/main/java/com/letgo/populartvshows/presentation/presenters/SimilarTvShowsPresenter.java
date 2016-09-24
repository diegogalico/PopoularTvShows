package com.letgo.populartvshows.presentation.presenters;

import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.presentation.presenters.base.BasePresenter;
import com.letgo.populartvshows.presentation.ui.BaseView;
import com.letgo.populartvshows.presentation.ui.activities.TvShowDetailActivity;

import java.util.List;

/**
 * @author diego.galico
 *
 * SimilarTvShowsPresenter is an interface that define methods to obtain similar tv shows information
 *
 */
public interface SimilarTvShowsPresenter extends BasePresenter {

    /**
     * Interface implemented by {@link TvShowDetailActivity} class
     */
    interface SimilarTvShowsView extends BaseView {

        /**
         * Show similar tv shows
         * @param similarTvShowList
         */
        void showSimilarTvShows(List<TvShow> similarTvShowList);
    }
}
