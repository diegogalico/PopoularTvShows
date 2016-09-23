package com.letgo.populartvshows.presentation.presenters.impl;

import com.letgo.populartvshows.domain.interactors.ConfigurationInteractor;
import com.letgo.populartvshows.domain.model.entities.Configuration;
import com.letgo.populartvshows.presentation.presenters.ConfigurationPresenter;
import com.letgo.populartvshows.utils.ApiStatusCode;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;

/**
 * @author diego.galico
 */

public class ConfigurationPresenterImpl implements ConfigurationPresenter, Observer<Configuration> {

    private ConfigurationInteractor mGetConfiguration;
    private ConfigurationView mConfigurationView;

    @Inject
    public ConfigurationPresenterImpl(ConfigurationInteractor getConfiguration) {
        mGetConfiguration = getConfiguration;
    }

    public void attachView(ConfigurationView configurationView) {
        mConfigurationView = configurationView;
    }

    @Override
    public final void onCompleted() {
        // do nothing
    }

    @Override
    public final void onError(Throwable e) {
        try {
            mConfigurationView.showError(ApiStatusCode.getApiStatusByCode(((HttpException) e).code()));
        } catch (Exception exc) {
            mConfigurationView.showError(e.getMessage());
        }

    }

    @Override
    public void onNext(Configuration configuration) {
        mGetConfiguration.setImageUrl(configuration);
        mConfigurationView.hideProgress();
        mConfigurationView.startPopularTvShowsFragment();
    }

    @Override
    public void start() {
        mConfigurationView.showProgress();
        mGetConfiguration.setPresenter(this);
        mGetConfiguration.execute();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }
}
