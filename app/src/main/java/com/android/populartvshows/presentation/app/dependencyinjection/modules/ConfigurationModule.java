package com.android.populartvshows.presentation.app.dependencyinjection.modules;

import com.android.populartvshows.data.rest.RestTvShows;
import com.android.populartvshows.domain.executors.PostExecutionThread;
import com.android.populartvshows.domain.executors.ThreadExecutor;
import com.android.populartvshows.domain.interactors.ConfigurationInteractor;
import com.android.populartvshows.domain.interactors.impl.ConfigurationInteractorImpl;
import com.android.populartvshows.domain.mapper.ConfigurationEntityDataMapper;

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
