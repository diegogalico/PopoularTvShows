package com.dashlane.populartvshows.data.rest;

import com.dashlane.populartvshows.presentation.app.Constants;
import com.dashlane.populartvshows.presentation.app.PopularTvShowsApplication;
import com.dashlane.populartvshows.domain.interactors.impl.ConfigurationInteractorImpl;
import com.dashlane.populartvshows.domain.interactors.impl.SimilarTvShowsInteractorImpl;
import com.dashlane.populartvshows.domain.interactors.impl.TvShowsInteractorImpl;
import com.dashlane.populartvshows.data.entities.Configuration;
import com.dashlane.populartvshows.data.entities.TvShowsWrapper;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * @author diego.galico
 *
 * RestTvShows class is in charge of calling {@link TheMovieApiInterface} in order to get API responses
 *
 */
public class RestTvShows implements RestData {

    @Inject
    TheMovieApiInterface mTheMovieApiInterface;

    public RestTvShows(PopularTvShowsApplication app) {
        app.getTheMovieComponent().inject(this);
    }

    @Override
    public void getConfiguration(ConfigurationInteractorImpl interactor) {
        Observable<Configuration> configuration = mTheMovieApiInterface.getConfiguration(Constants.API_KEY);
        configuration.subscribeOn(Schedulers.newThread())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(interactor);
        Timber.d("Downloading configuration: %s", "http://api.themoviedb.org/3/configuration");
    }

    @Override
    public void getTvShowsByPage(int page, TvShowsInteractorImpl interactor) {
        Observable<TvShowsWrapper> tvShow = mTheMovieApiInterface.getPopularTvShowsByPage(Constants.API_KEY, page, Constants.DEVICE_LANGUAGE);
        tvShow.subscribeOn(Schedulers.newThread())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(interactor);
        Timber.d("Downloading tv shows by page: %s", "http://api.themoviedb.org/3/tv/top_rated/" + page);
    }

    @Override
    public void getSimilarTvShows(SimilarTvShowsInteractorImpl interactor, int tvShowId) {
        Observable<TvShowsWrapper> configuration = mTheMovieApiInterface.getSimilarTvShows(tvShowId, Constants.API_KEY, Constants.DEVICE_LANGUAGE);
        configuration.subscribeOn(Schedulers.newThread())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(interactor);
        Timber.d("Downloading similar tv shows: %s", "http://api.themoviedb.org/3/similar/" + tvShowId);
    }
}
