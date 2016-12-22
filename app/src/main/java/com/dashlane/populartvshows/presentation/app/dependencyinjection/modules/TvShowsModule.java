package com.dashlane.populartvshows.presentation.app.dependencyinjection.modules;

import com.dashlane.populartvshows.domain.executors.PostExecutionThread;
import com.dashlane.populartvshows.domain.executors.ThreadExecutor;
import com.dashlane.populartvshows.domain.interactors.TvShowsInteractor;
import com.dashlane.populartvshows.domain.interactors.impl.TvShowsInteractorImpl;
import com.dashlane.populartvshows.data.rest.RestTvShows;
import com.dashlane.populartvshows.domain.mapper.TvShowEntityDataMapper;

import dagger.Module;
import dagger.Provides;

/**
 * @author diego.galico
 */
@Module
public class TvShowsModule {

    @Provides
    TvShowsInteractor provideTvShowsInteractor(RestTvShows tvShows, ThreadExecutor threadExecutor,
                                               PostExecutionThread postExecutionThread, TvShowEntityDataMapper tvShowEntityDataMapper) {
        return new TvShowsInteractorImpl(tvShows, threadExecutor, postExecutionThread, tvShowEntityDataMapper);
    }
}
