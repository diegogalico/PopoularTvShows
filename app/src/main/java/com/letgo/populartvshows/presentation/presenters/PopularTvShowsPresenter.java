package com.letgo.populartvshows.presentation.presenters;

import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.presentation.presenters.base.BasePresenter;
import com.letgo.populartvshows.presentation.ui.BaseView;

import java.util.List;

/**
 * @author diego.galico
 */
public interface PopularTvShowsPresenter extends BasePresenter {

    interface PopularTvShowsView extends BaseView {

        void showPopularTvShows(List<TvShow> tvShowList);

        boolean isTheListEmpty();

        void appendPopularTvShows(List<TvShow> tvShowList);
    }
}
