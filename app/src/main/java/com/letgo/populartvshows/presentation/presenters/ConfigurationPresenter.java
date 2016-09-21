package com.letgo.populartvshows.presentation.presenters;

import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.presentation.presenters.base.BasePresenter;
import com.letgo.populartvshows.presentation.ui.BaseView;

import java.util.List;

/**
 * @author diego.galico
 */
public interface ConfigurationPresenter extends BasePresenter {

    interface ConfigurationView extends BaseView {

        void startPopularTvShowsFragment();
    }
}
