package com.android.populartvshows.data.rest;

import com.android.populartvshows.data.entities.ConfigurationEntity;
import com.android.populartvshows.data.entities.TvShowsWrapperEntity;
import com.android.populartvshows.data.exceptions.NetworkConnectionException;
import com.android.populartvshows.presentation.app.Constants;
import com.android.populartvshows.presentation.app.PopularTvShowsApplication;

import javax.inject.Inject;

import rx.Observable;
import timber.log.Timber;

/**
 * @author diego.galico
 *
 * RestTvShows class is in charge of calling {@link TheMovieApiInterface} in order to get API responses
 *
 */
public class RestTvShows implements RestData {

    private PopularTvShowsApplication mApplication;

    @Inject
    TheMovieApiInterface mTheMovieApiInterface;

    @Inject
    public RestTvShows(PopularTvShowsApplication app) {
        app.getTheMovieComponent().inject(this);
        mApplication = app;
    }

    @Override public Observable<ConfigurationEntity> getConfiguration() {
        Observable<ConfigurationEntity> configuration = mTheMovieApiInterface.getConfiguration(Constants.API_KEY);
        if (!mApplication.hasNetwork()) {
            configuration.error(new NetworkConnectionException());
        }
        Timber.d("Downloading configuration: %s", "http://api.themoviedb.org/3/configuration");
        return configuration;
    }

    @Override
    public Observable<TvShowsWrapperEntity> getTvShowsByPage(int page) {
        Observable<TvShowsWrapperEntity> tvShow = mTheMovieApiInterface.getPopularTvShowsByPage(Constants.API_KEY, page, Constants.DEVICE_LANGUAGE);
        if (!mApplication.hasNetwork()) {
            tvShow.error(new NetworkConnectionException());
        }
        Timber.d("Downloading tv shows by page: %s", "http://api.themoviedb.org/3/tv/top_rated/" + page);
        return tvShow;
    }

    @Override
    public Observable<TvShowsWrapperEntity> getSimilarTvShows(int tvShowId) {
        Observable<TvShowsWrapperEntity> tvShow = mTheMovieApiInterface.getSimilarTvShows(tvShowId, Constants.API_KEY, Constants.DEVICE_LANGUAGE);
        if (!mApplication.hasNetwork()) {
            tvShow.error(new NetworkConnectionException());
        }
        Timber.d("Downloading similar tv shows: %s", "http://api.themoviedb.org/3/similar/" + tvShowId);
        return tvShow;
    }
}
