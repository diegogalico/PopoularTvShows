package com.letgo.populartvshows.domain.model.rest;

import com.letgo.populartvshows.presentation.presenters.impl.PopularTvShowsPresenterImpl;

/**
 * @author diego.galico
 */
public interface RestData {

    void getTvShowsByPage(int page, PopularTvShowsPresenterImpl presenter);

    void getTvShowDetail(String id);
}
