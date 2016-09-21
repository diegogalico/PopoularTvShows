package com.letgo.populartvshows.app.dependencyinjection.modules;

import com.letgo.populartvshows.domain.interactors.TvShowsInteractor;
import com.letgo.populartvshows.domain.interactors.impl.TvShowsInteractorImpl;
import com.letgo.populartvshows.domain.model.rest.RestTvShows;

import dagger.Module;
import dagger.Provides;

/**
 * @author diego.galico
 */
@Module
public class TvShowsModule {

    @Provides
    TvShowsInteractor provideTvShowsInteractor(RestTvShows tvShows) {
        return new TvShowsInteractorImpl(tvShows);
    }
}
