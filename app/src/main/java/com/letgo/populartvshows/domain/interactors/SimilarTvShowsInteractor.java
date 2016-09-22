package com.letgo.populartvshows.domain.interactors;


import com.letgo.populartvshows.domain.interactors.base.Interactor;
import com.letgo.populartvshows.presentation.presenters.impl.SimilarTvShowsPresenterImpl;

/**
 * @author diego.galico
 */
public interface SimilarTvShowsInteractor extends Interactor {

    void requestSimilarTvShowsDetail(int tvShowId);

    void setPresenter(SimilarTvShowsPresenterImpl presenter);

}
