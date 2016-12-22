package com.dashlane.populartvshows.presentation.app.dependencyinjection.modules;

import com.dashlane.populartvshows.domain.executors.PostExecutionThread;
import com.dashlane.populartvshows.domain.executors.ThreadExecutor;
import com.dashlane.populartvshows.domain.interactors.SimilarTvShowsInteractor;
import com.dashlane.populartvshows.domain.interactors.impl.SimilarTvShowsInteractorImpl;
import com.dashlane.populartvshows.data.rest.RestTvShows;
import com.dashlane.populartvshows.domain.mapper.TvShowEntityDataMapper;

import dagger.Module;
import dagger.Provides;

/**
 * @author diego.galico
 */
@Module
public class SimilarTvShowsModule {

    private final int tvShowId;

    public SimilarTvShowsModule(int tvShowId) {
        this.tvShowId = tvShowId;
    }

    @Provides
    SimilarTvShowsInteractor provideTvShowDetailInteractor(RestTvShows tvShows, ThreadExecutor threadExecutor,
                                                           PostExecutionThread postExecutionThread, TvShowEntityDataMapper tvShowEntityDataMapper) {
        return new SimilarTvShowsInteractorImpl(tvShows, tvShowId, threadExecutor, postExecutionThread, tvShowEntityDataMapper);
    }
}
