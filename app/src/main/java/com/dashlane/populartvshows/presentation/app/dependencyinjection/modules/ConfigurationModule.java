package com.dashlane.populartvshows.presentation.app.dependencyinjection.modules;

import com.dashlane.populartvshows.data.rest.RestTvShows;
import com.dashlane.populartvshows.domain.executors.PostExecutionThread;
import com.dashlane.populartvshows.domain.executors.ThreadExecutor;
import com.dashlane.populartvshows.domain.interactors.ConfigurationInteractor;
import com.dashlane.populartvshows.domain.interactors.impl.ConfigurationInteractorImpl;
import com.dashlane.populartvshows.domain.mapper.ConfigurationEntityDataMapper;

import dagger.Module;
import dagger.Provides;

/**
 * @author diego.galico
 */
@Module
public class ConfigurationModule {

    @Provides
    ConfigurationInteractor provideConfigurationInteractor(RestTvShows tvShows, ThreadExecutor threadExecutor,
                                                           PostExecutionThread postExecutionThread, ConfigurationEntityDataMapper configurationEntityDataMapper) {
        return new ConfigurationInteractorImpl(tvShows, threadExecutor, postExecutionThread, configurationEntityDataMapper);
    }
}
