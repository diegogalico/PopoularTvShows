package com.dashlane.populartvshows.data.entities;

import java.io.Serializable;
import java.util.List;

/**
 * @author diego.galico
 *
 * Class that model TvShowEntity information
 *
 */
public class TvShowEntity implements Serializable{

    String poster_path;
    double popularity;
    int id;
    String backdrop_path;
    double vote_average;
    String overview;
    String first_air_date;
    List<String> origin_country;
    List<Integer> genre_ids;
    String original_language;
    int vote_count;
    String name;
    String original_name;
    boolean tv_show_ready;

    public String getPosterPath() {
        return poster_path;
    }

    public void setPosterPath(String posterPath) {
        this.poster_path = posterPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
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

    public List<String> getOriginCountry() {
        return origin_country;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.origin_country = originCountry;
    }

    public List<Integer> getGenreIds() {
        return genre_ids;
    }

    public void setgetGenreIds(List<Integer> genreIds) {
        this.genre_ids = genreIds;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.original_language = originalLanguage;
    }

    public int getVoteCount() {
        return vote_count;
    }

    public void setVoteCount(int voteCount) {
        this.vote_count = voteCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return original_name;
    }

    public void setOriginalName(String originalName) {
        this.original_name = originalName;
    }

    public void setTvShowReady(boolean tvShowReady) {

        this.tv_show_ready = tvShowReady;
    }

    public boolean isTvShowReady() {

        return tv_show_ready;
    }
}
