package com.letgo.populartvshows.presentation.presenters.impl;

import com.letgo.populartvshows.domain.interactors.ConfigurationInteractor;
import com.letgo.populartvshows.domain.model.entities.Configuration;
import com.letgo.populartvshows.presentation.presenters.ConfigurationPresenter;

import javax.inject.Inject;

/**
 * @author diego.galico
 */

public class ConfigurationPresenterImpl implements ConfigurationPresenter, ConfigurationInteractor.ConfigurationResponse {

    private ConfigurationInteractor mGetConfigurationInteractor;
    private ConfigurationView mConfigurationView;

    @Inject
    public ConfigurationPresenterImpl(ConfigurationInteractor getConfigurationInteractor) {
        mGetConfigurationInteractor = getConfigurationInteractor;
    }

    public void attachView(ConfigurationView configurationView) {
        mConfigurationView = configurationView;
    }

    @Override
    public void start() {
        mConfigurationView.showProgress();
        mGetConfigurationInteractor.setPresenter(this);
        mGetConfigurationInteractor.execute();
    }

    @Override
    public void onConfigurationResponse(Configuration configuration) {
        mGetConfigurationInteractor.setImageUrl(configuration);
        mConfigurationView.hideProgress();
        mConfigurationView.startPopularTvShowsFragment();
    }

    @Override
    public void onErrorResponse(String error) {
        mConfigurationView.hideProgress();
        mConfigurationView.showError(error);
    }

    @Override
    public void stop() {
    }
}
