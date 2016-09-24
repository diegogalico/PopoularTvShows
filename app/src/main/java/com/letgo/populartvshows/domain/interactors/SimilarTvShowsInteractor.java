package com.letgo.populartvshows.domain.interactors;


import com.letgo.populartvshows.domain.interactors.base.Interactor;
import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.domain.model.rest.RestData;
import com.letgo.populartvshows.presentation.presenters.impl.SimilarTvShowsPresenterImpl;

import java.util.List;

/**
 * @author diego.galico
 *
 * SimilarTvShowsInteractor is an interface that define methods to obtain similar tv shows information
 *
 */
public interface SimilarTvShowsInteractor extends Interactor {

    /**
     * Request similar tv shows detail to {@link RestData} class
     * @param tvShowId
     */
    void requestSimilarTvShowsDetail(int tvShowId);

    /**
     * Set presenter to class variable in order to return information to {@link SimilarTvShowsPresenterImpl} class
     * @param presenter
     */
    void setPresenter(SimilarTvShowsPresenterImpl presenter);

    /**
     * Interface implemented by {@link SimilarTvShowsPresenterImpl} class
     */
    interface SimilarTvShowsResponse {

        /**
         * Retrieve List<TvShow> response
         * @param similarTvShowList
         */
        void onSimilarTvShowsResponse(List<TvShow> similarTvShowList);

        /**
         * Retrieve error response
         * @param error
         */
        void onErrorResponse(String error);
    }
}
