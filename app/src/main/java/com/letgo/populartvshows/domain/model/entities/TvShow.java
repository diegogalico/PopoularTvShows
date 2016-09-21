package com.letgo.populartvshows.domain.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author diego.galico
 */
public class TvShow {

    @SerializedName("poster_path")
    @Expose
    String poster_path;

    @SerializedName("popularity")
    @Expose
    double popularity;

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("backdrop_path")
    @Expose
    String backdrop_path;

    @SerializedName("vote_average")
    @Expose
    double vote_average;

    @SerializedName("overview")
    @Expose
    String overview;

    @SerializedName("first_air_date")
    @Expose
    String first_air_date;

    @SerializedName("origin_country")
    @Expose
    List<String> origin_country;

    @SerializedName("genre_ids")
    @Expose
    List<Integer> genre_ids;

    @SerializedName("original_language")
    @Expose
    String original_language;

    @SerializedName("vote_count")
    @Expose
    int vote_count;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("original_name")
    @Expose
    String original_name;

    boolean tvShowReady;

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

        this.tvShowReady = tvShowReady;
    }

    public boolean isTvShowReady() {

        return tvShowReady;
    }
}
