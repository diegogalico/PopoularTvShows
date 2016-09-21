package com.letgo.populartvshows.domain.interactors;


import com.letgo.populartvshows.domain.interactors.base.Interactor;
import com.letgo.populartvshows.presentation.presenters.impl.PopularTvShowsPresenterImpl;

/**
 * @author diego.galico
 */
public interface TvShowsInteractor extends Interactor {

    void requestPopularTvShows();

    void setPresenter(PopularTvShowsPresenterImpl presenter);

}
