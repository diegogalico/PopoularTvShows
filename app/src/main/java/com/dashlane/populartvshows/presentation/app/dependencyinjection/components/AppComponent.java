package com.dashlane.populartvshows.presentation.app.dependencyinjection.components;

import com.dashlane.populartvshows.data.rest.RestTvShows;
import com.dashlane.populartvshows.domain.executors.PostExecutionThread;
import com.dashlane.populartvshows.domain.executors.ThreadExecutor;
import com.dashlane.populartvshows.domain.mapper.ConfigurationEntityDataMapper;
import com.dashlane.populartvshows.domain.mapper.TvShowEntityDataMapper;
import com.dashlane.populartvshows.presentation.app.dependencyinjection.modules.AppModule;
import com.dashlane.populartvshows.presentation.app.dependencyinjection.modules.DomainModule;
import com.dashlane.populartvshows.presentation.mapper.TvShowModelDataMapper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author diego.galico
 */
@Singleton
@Component(modules = {
        AppModule.class,
        DomainModule.class,
})

public interface AppComponent {
    RestTvShows restTvShows();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    ConfigurationEntityDataMapper configurationEntityDataMapper();
    TvShowEntityDataMapper tvShowEntityDataMapper();
    TvShowModelDataMapper tvShowModelDataMapper();
}

