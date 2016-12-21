package com.dashlane.populartvshows.data.rest;

import com.dashlane.populartvshows.domain.interactors.impl.ConfigurationInteractorImpl;
import com.dashlane.populartvshows.domain.interactors.impl.SimilarTvShowsInteractorImpl;
import com.dashlane.populartvshows.domain.interactors.impl.TvShowsInteractorImpl;

/**
 * @author diego.galico
 *
 * RestData is an interface that define methods to obtain API responses
 */
public interface RestData {

    /**
     * Request popular tv shows by page and suscribe the response to {@link TvShowsInteractorImpl} observer
     *
     * @param page
     * @param interactor
     */
    void getTvShowsByPage(int page, TvShowsInteractorImpl interactor);

    /**
     * Request configuration and suscribe the response to {@link ConfigurationInteractorImpl} observer
     *
     * @param interator
     */
    void getConfiguration(ConfigurationInteractorImpl interator);

    /**
     * Request similar tv shows for given tv show id and suscribe the response to {@link SimilarTvShowsInteractorImpl} observer
     *
     * @param interactor
     * @param tvShowId
     */
    void getSimilarTvShows(SimilarTvShowsInteractorImpl interactor, int tvShowId);
}
