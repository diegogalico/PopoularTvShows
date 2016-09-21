package com.letgo.populartvshows.domain.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author diego.galico
 */

public class TvShowsWrapper {

    @SerializedName("page")
    @Expose
    private String page;

    @SerializedName("results")
    @Expose
    private List<TvShow> tvShowInfo;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<TvShow> getTvShowInfo() {
        return tvShowInfo;
    }

    public void setTvShowInfo(List<TvShow> tvShowInfo) {
        this.tvShowInfo = tvShowInfo;
    }
}
