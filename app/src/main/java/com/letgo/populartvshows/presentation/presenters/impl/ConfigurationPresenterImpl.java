package com.letgo.populartvshows.presentation.presenters.impl;

import android.util.Log;

import com.letgo.populartvshows.domain.interactors.ConfigurationInteractor;
import com.letgo.populartvshows.domain.model.entities.Configuration;
import com.letgo.populartvshows.presentation.presenters.ConfigurationPresenter;
import com.letgo.populartvshows.presentation.ui.BaseView;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by diego.galico on 21/09/2016.
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
        Log.e("Splash", e.getMessage());
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
