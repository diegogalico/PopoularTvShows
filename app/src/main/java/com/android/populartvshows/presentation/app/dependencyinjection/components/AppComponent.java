package com.android.populartvshows.presentation.app.dependencyinjection.components;

import com.android.populartvshows.data.rest.RestTvShows;
import com.android.populartvshows.domain.executors.PostExecutionThread;
import com.android.populartvshows.domain.executors.ThreadExecutor;
import com.android.populartvshows.domain.mapper.ConfigurationEntityDataMapper;
import com.android.populartvshows.domain.mapper.TvShowEntityDataMapper;
import com.android.populartvshows.presentation.app.dependencyinjection.modules.AppModule;
import com.android.populartvshows.presentation.app.dependencyinjection.modules.DomainModule;
import com.android.populartvshows.presentation.mapper.TvShowModelDataMapper;

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

