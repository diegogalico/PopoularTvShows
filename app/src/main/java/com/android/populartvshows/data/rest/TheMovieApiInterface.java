package com.android.populartvshows.data.rest;

import com.android.populartvshows.data.entities.ConfigurationEntity;
import com.android.populartvshows.data.entities.TvShowsWrapperEntity;

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
    Observable<ConfigurationEntity> getConfiguration(@Query("api_key") String apiKey);

    @GET("/3/tv/top_rated")
    Observable<TvShowsWrapperEntity> getPopularTvShowsByPage(@Query("api_key") String apiKey, @Query("page") int page, @Query("language") String language);

    @GET("/3/tv/{id}/similar")
    Observable<TvShowsWrapperEntity> getSimilarTvShows(@Path("id") int tvShowId, @Query("api_key") String apiKey, @Query("language") String language);

}
