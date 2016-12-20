package com.dashlane.populartvshows.presentation.presenters;

import com.dashlane.populartvshows.domain.model.entities.TvShow;
import com.dashlane.populartvshows.presentation.presenters.base.BasePresenter;
import com.dashlane.populartvshows.presentation.ui.BaseView;
import com.dashlane.populartvshows.presentation.ui.activities.TvShowDetailActivity;

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
