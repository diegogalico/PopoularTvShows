package com.letgo.populartvshows.domain.model.rest;

import com.letgo.populartvshows.domain.interactors.impl.ConfigurationInteractorImpl;
import com.letgo.populartvshows.domain.interactors.impl.SimilarTvShowsInteractorImpl;
import com.letgo.populartvshows.domain.interactors.impl.TvShowsInteractorImpl;

/**
 * @author diego.galico
 */
public interface RestData {

    void getTvShowsByPage(int page, TvShowsInteractorImpl presenter);

    void getConfiguration(ConfigurationInteractorImpl interator);

    void getSimilarTvShows(SimilarTvShowsInteractorImpl interactor, int tvShowId);
}
