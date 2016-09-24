package com.letgo.populartvshows.domain.model.rest;

import com.letgo.populartvshows.domain.interactors.impl.ConfigurationInteractorImpl;
import com.letgo.populartvshows.domain.interactors.impl.SimilarTvShowsInteractorImpl;
import com.letgo.populartvshows.domain.interactors.impl.TvShowsInteractorImpl;

/**
 * @author diego.galico
 *         <p/>
 *         RestData is an interface that define methods to obtain API responses
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
