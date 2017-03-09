package com.android.populartvshows.presentation.app.dependencyinjection.modules;

import com.android.populartvshows.presentation.app.PopularTvShowsApplication;
import com.android.populartvshows.data.rest.RestTvShows;

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
