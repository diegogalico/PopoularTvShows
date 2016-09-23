package com.letgo.populartvshows.domain.interactors;


import com.letgo.populartvshows.domain.interactors.base.Interactor;
import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.presentation.presenters.impl.SimilarTvShowsPresenterImpl;

import java.util.List;

/**
 * @author diego.galico
 */
public interface SimilarTvShowsInteractor extends Interactor {

    void requestSimilarTvShowsDetail(int tvShowId);

    void setPresenter(SimilarTvShowsPresenterImpl presenter);

    interface SimilarTvShowsResponse {

        void onSimilarTvShowsResponse(List<TvShow> similarTvShowList);

        void onErrorResponse(String error);
    }
}
