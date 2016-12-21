package com.dashlane.populartvshows.app.dependencyinjection.modules;

import com.dashlane.populartvshows.app.PopularTvShowsApplication;
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
