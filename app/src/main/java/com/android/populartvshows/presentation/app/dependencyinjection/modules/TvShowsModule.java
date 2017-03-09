package com.android.populartvshows.presentation.app.dependencyinjection.modules;

import com.android.populartvshows.domain.executors.PostExecutionThread;
import com.android.populartvshows.domain.executors.ThreadExecutor;
import com.android.populartvshows.domain.interactors.TvShowsInteractor;
import com.android.populartvshows.domain.interactors.impl.TvShowsInteractorImpl;
import com.android.populartvshows.data.rest.RestTvShows;
import com.android.populartvshows.domain.mapper.TvShowEntityDataMapper;

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
