package com.letgo.populartvshows.app.dependencyinjection.modules;

import com.letgo.populartvshows.domain.interactors.ConfigurationInteractor;
import com.letgo.populartvshows.domain.interactors.impl.ConfigurationInteractorImpl;
import com.letgo.populartvshows.domain.model.rest.RestTvShows;

import dagger.Module;
import dagger.Provides;

/**
 * @author diego.galico
 */
@Module
public class ConfigurationModule {

    @Provides
    ConfigurationInteractor provideConfigurationInteractor(RestTvShows tvShows) {
        return new ConfigurationInteractorImpl(tvShows);
    }
}
