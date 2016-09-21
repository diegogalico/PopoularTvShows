package com.letgo.populartvshows.domain.model.rest;

import com.letgo.populartvshows.app.Constants;
import com.letgo.populartvshows.app.PopularTvShowsApplication;
import com.letgo.populartvshows.domain.model.entities.TvShowsWrapper;
import com.letgo.populartvshows.presentation.presenters.impl.PopularTvShowsPresenterImpl;

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
    public void getTvShowsByPage(int page, PopularTvShowsPresenterImpl presenter) {
        Observable<TvShowsWrapper> tvShow = mTheMovieApiInterface.getPopularTvShowsByPage(Constants.API_KEY);
        tvShow.subscribeOn(Schedulers.newThread())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(presenter);
    }

    @Override
    public void getTvShowDetail(String id) {

    }
}
