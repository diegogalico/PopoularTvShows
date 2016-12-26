package com.dashlane.populartvshows.presentation.models;

/**
 * @author diego.galico
 *
 * Class that represents ConfigurationEntity information in the presentation layer
 *
 */
public class TvShowModel {
    String poster_path;
    int id;
    String backdrop_path;
    double vote_average;
    String overview;
    String first_air_date;
    String name;
    boolean tv_show_ready;

    public String getPosterPath() {
        return poster_path;
    }

    public void setPosterPath(String posterPath) {
        this.poster_path = posterPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdrop_path = backdropPath;
    }

    public double getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(double voteAverage) {
        this.vote_average = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirstAirDate() {
        return first_air_date;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.first_air_date = firstAirDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTvShowReady(boolean tvShowReady) {
        this.tv_show_ready = tvShowReady;
    }

    public boolean isTvShowReady() {
        return tv_show_ready;
    }
}
