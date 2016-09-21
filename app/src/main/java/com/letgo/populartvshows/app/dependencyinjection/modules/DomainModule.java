package com.letgo.populartvshows.app.dependencyinjection.modules;

import com.letgo.populartvshows.app.PopularTvShowsApplication;
import com.letgo.populartvshows.domain.model.rest.RestTvShows;

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
