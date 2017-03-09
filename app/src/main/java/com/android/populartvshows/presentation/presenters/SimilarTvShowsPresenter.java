package com.android.populartvshows.presentation.presenters;

import com.android.populartvshows.presentation.models.TvShowModel;
import com.android.populartvshows.presentation.presenters.base.BasePresenter;
import com.android.populartvshows.presentation.ui.BaseView;
import com.android.populartvshows.presentation.ui.activities.TvShowDetailActivity;

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
        void showSimilarTvShows(List<TvShowModel> similarTvShowList);
    }
}
