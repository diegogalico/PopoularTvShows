package com.dashlane.populartvshows.app.dependencyinjection.modules;

import com.dashlane.populartvshows.domain.interactors.TvShowsInteractor;
import com.dashlane.populartvshows.domain.interactors.impl.TvShowsInteractorImpl;
import com.dashlane.populartvshows.domain.model.rest.RestTvShows;

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
