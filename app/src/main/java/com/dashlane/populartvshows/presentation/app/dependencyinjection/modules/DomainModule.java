package com.dashlane.populartvshows.presentation.app.dependencyinjection.modules;

import com.dashlane.populartvshows.presentation.app.PopularTvShowsApplication;
import com.dashlane.populartvshows.data.rest.RestTvShows;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author diego.galico
 */
@Module
public class DomainModule {

    @Provides @Singleton
    RestTvShows provideDataSource (PopularTvShowsApplication app) { return new RestTvShows(app); }

}
