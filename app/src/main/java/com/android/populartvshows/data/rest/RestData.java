package com.android.populartvshows.data.rest;

import com.android.populartvshows.data.entities.ConfigurationEntity;
import com.android.populartvshows.data.entities.TvShowsWrapperEntity;
import com.android.populartvshows.domain.interactors.impl.ConfigurationInteractorImpl;
import com.android.populartvshows.domain.interactors.impl.SimilarTvShowsInteractorImpl;
import com.android.populartvshows.domain.interactors.impl.TvShowsInteractorImpl;

import rx.Observable;

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
     */
    Observable<TvShowsWrapperEntity> getTvShowsByPage(int page);

    /**
     * Request configuration and suscribe the response to {@link ConfigurationInteractorImpl} observer
     *
     */
    Observable<ConfigurationEntity> getConfiguration();

    /**
     * Request similar tv shows for given tv show id and suscribe the response to {@link SimilarTvShowsInteractorImpl} observer
     *
     * @param tvShowId
     */
    Observable<TvShowsWrapperEntity> getSimilarTvShows(int tvShowId);
}
