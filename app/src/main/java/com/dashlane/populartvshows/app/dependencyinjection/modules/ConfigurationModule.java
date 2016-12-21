package com.dashlane.populartvshows.app.dependencyinjection.modules;

import com.dashlane.populartvshows.domain.interactors.ConfigurationInteractor;
import com.dashlane.populartvshows.domain.interactors.impl.ConfigurationInteractorImpl;
import com.dashlane.populartvshows.data.rest.RestTvShows;

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
