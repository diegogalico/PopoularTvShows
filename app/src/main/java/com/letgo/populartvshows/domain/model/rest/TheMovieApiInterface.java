package com.letgo.populartvshows.domain.model.rest;

import com.letgo.populartvshows.domain.model.entities.TvShowsWrapper;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author diego.galico
 */
public interface TheMovieApiInterface {

    @GET("/3/tv/top_rated")
    Observable<TvShowsWrapper> getPopularTvShowsByPage(@Query("api_key") String apiKey);

}
