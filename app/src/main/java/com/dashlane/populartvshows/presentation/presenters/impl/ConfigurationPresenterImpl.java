package com.dashlane.populartvshows.presentation.presenters.impl;

import com.dashlane.populartvshows.domain.interactors.ConfigurationInteractor;
import com.dashlane.populartvshows.data.entities.Configuration;
import com.dashlane.populartvshows.presentation.presenters.ConfigurationPresenter;

import javax.inject.Inject;

/**
 * @author diego.galico
 *
 * ConfigurationPresenterImpl class is in charge of calling {@link ConfigurationInteractor} to obtain configuration response
 *
 */
public class ConfigurationPresenterImpl implements ConfigurationPresenter, ConfigurationInteractor.ConfigurationResponse {

    private ConfigurationInteractor mGetConfigurationInteractor;
    private ConfigurationView mConfigurationView;

    @Inject
    public ConfigurationPresenterImpl(ConfigurationInteractor getConfigurationInteractor) {
        mGetConfigurationInteractor = getConfigurationInteractor;
    }

    /**
     * Attach view to presenter
     * @param configurationView
     */
    public void attachView(ConfigurationView configurationView) {
        mConfigurationView = configurationView;
    }

    @Override
    public void start() {
        mConfigurationView.showProgress();
        mGetConfigurationInteractor.setPresenter(this);
        mGetConfigurationInteractor.execute();
    }

    /**
     * Configuration response
     * @param configuration
     */
    @Override
    public void onConfigurationResponse(Configuration configuration) {
        mGetConfigurationInteractor.setImageUrl(configuration);
        mConfigurationView.hideProgress();
        mConfigurationView.startPopularTvShowsActivity();
    }

    /**
     * Error response
     * @param error
     */
    @Override
    public void onErrorResponse(String error) {
        mConfigurationView.hideProgress();
        mConfigurationView.showError(error);
    }

    @Override
    public void stop() {
    }
}
