package com.letgo.populartvshows.domain.model.rest;

import com.letgo.populartvshows.domain.model.entities.Configuration;
import com.letgo.populartvshows.domain.model.entities.TvShowsWrapper;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author diego.galico
 *
 * Interface that define API call methods
 */
public interface TheMovieApiInterface {

    @GET("/3/configuration")
    Observable<Configuration> getConfiguration(@Query("api_key") String apiKey);

    @GET("/3/tv/top_rated")
    Observable<TvShowsWrapper> getPopularTvShowsByPage(@Query("api_key") String apiKey, @Query("page") int page, @Query("language") String language);

    @GET("/3/tv/{id}/similar")
    Observable<TvShowsWrapper> getSimilarTvShows(@Path("id") int tvShowId, @Query("api_key") String apiKey, @Query("language") String language);

}
