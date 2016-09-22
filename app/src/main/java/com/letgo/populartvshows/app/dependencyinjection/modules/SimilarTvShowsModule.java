package com.letgo.populartvshows.app.dependencyinjection.modules;

import com.letgo.populartvshows.domain.interactors.SimilarTvShowsInteractor;
import com.letgo.populartvshows.domain.interactors.impl.SimilarTvShowsInteractorImpl;
import com.letgo.populartvshows.domain.model.rest.RestTvShows;

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
    SimilarTvShowsInteractor provideTvShowDetailInteractor(RestTvShows tvShows) {
        return new SimilarTvShowsInteractorImpl(tvShows, tvShowId);
    }
}
