package com.letgo.populartvshows.domain.model.rest;

import com.letgo.populartvshows.app.Constants;
import com.letgo.populartvshows.app.PopularTvShowsApplication;
import com.letgo.populartvshows.domain.interactors.impl.ConfigurationInteractorImpl;
import com.letgo.populartvshows.domain.interactors.impl.SimilarTvShowsInteractorImpl;
import com.letgo.populartvshows.domain.interactors.impl.TvShowsInteractorImpl;
import com.letgo.populartvshows.domain.model.entities.Configuration;
import com.letgo.populartvshows.domain.model.entities.TvShowsWrapper;
import com.letgo.populartvshows.presentation.presenters.impl.ConfigurationPresenterImpl;
import com.letgo.populartvshows.presentation.presenters.impl.PopularTvShowsPresenterImpl;
import com.letgo.populartvshows.presentation.presenters.impl.SimilarTvShowsPresenterImpl;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author diego.galico
 */
public class RestTvShows implements RestData {

    @Inject
    TheMovieApiInterface mTheMovieApiInterface;

    public RestTvShows(PopularTvShowsApplication app) {

        app.getTheMovieComponent().inject(this);
    }

    @Override
    public void getTvShowsByPage(int page, TvShowsInteractorImpl interactor) {
        Observable<TvShowsWrapper> tvShow = mTheMovieApiInterface.getPopularTvShowsByPage(Constants.API_KEY, page);
        tvShow.subscribeOn(Schedulers.newThread())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(interactor);
    }

    @Override
    public void getConfiguration(ConfigurationInteractorImpl interactor) {
        Observable<Configuration> configuration = mTheMovieApiInterface.getConfiguration(Constants.API_KEY);
        configuration.subscribeOn(Schedulers.newThread())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(interactor);
    }

    @Override
    public void getSimilarTvShows(SimilarTvShowsInteractorImpl interactor, int tvShowId) {
        Observable<TvShowsWrapper> configuration = mTheMovieApiInterface.getSimilarTvShows(tvShowId, Constants.API_KEY);
        configuration.subscribeOn(Schedulers.newThread())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(interactor);

    }
}
