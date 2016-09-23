package com.letgo.populartvshows.domain.interactors;


import com.letgo.populartvshows.domain.interactors.base.Interactor;
import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.presentation.presenters.impl.PopularTvShowsPresenterImpl;
import com.letgo.populartvshows.presentation.ui.BaseView;

import java.util.List;

/**
 * @author diego.galico
 */
public interface TvShowsInteractor extends Interactor {

    void requestPopularTvShows();

    void setPresenter(PopularTvShowsPresenterImpl presenter);

    interface PopularTvShowsResponse {

        void onPopularTvShowsResponse(List<TvShow> popularTvShowList);

        void onErrorResponse(String error);
    }

}
