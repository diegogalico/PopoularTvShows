package com.android.populartvshows.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author diego.galico
 *
 * Class that model TvShowsWrapperEntity information
 *
 */
public class TvShowsWrapperEntity implements Serializable {

    private String page;

    @SerializedName("results")
    @Expose
    private List<TvShowEntity> tv_show_info;

    public TvShowsWrapperEntity(List<TvShowEntity> tvShowInfo) {
        this.tv_show_info = tvShowInfo;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<TvShowEntity> getTvShowInfo() {
        return tv_show_info;
    }

    public void setTvShowInfo(List<TvShowEntity> tvShowInfo) {
        this.tv_show_info = tvShowInfo;
    }
}
