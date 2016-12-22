package com.dashlane.populartvshows.presentation.presenters.impl;

import com.dashlane.populartvshows.domain.ConfigurationData;
import com.dashlane.populartvshows.domain.exception.DefaultErrorBundle;
import com.dashlane.populartvshows.domain.exception.ErrorBundle;
import com.dashlane.populartvshows.domain.interactors.ConfigurationInteractor;
import com.dashlane.populartvshows.domain.interactors.DefaultSubscriber;
import com.dashlane.populartvshows.presentation.app.Constants;
import com.dashlane.populartvshows.presentation.exception.ErrorMessageFactory;
import com.dashlane.populartvshows.presentation.presenters.ConfigurationPresenter;

import javax.inject.Inject;

/**
 * @author diego.galico
 *
 * ConfigurationPresenterImpl class is in charge of calling {@link ConfigurationInteractor} to obtain configuration response
 *
 */
public class ConfigurationPresenterImpl implements ConfigurationPresenter {

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
        mGetConfigurationInteractor.execute(new ConfigurationSubscriber());
    }

    @Override
    public void stop() {
    }

    @Override public void destroy() {
        mGetConfigurationInteractor.unsubscribe();
        mConfigurationView = null;
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(mConfigurationView.getContext(),
                errorBundle.getException());
        mConfigurationView.showError(errorMessage);
    }

    private final class ConfigurationSubscriber extends DefaultSubscriber<ConfigurationData> {

        @Override public void onCompleted() {
            mConfigurationView.hideProgress();
        }

        /**
         * Error response
         * @param e
         */
        @Override public void onError(Throwable e) {
            mConfigurationView.hideProgress();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
        }

        /**
         * ConfigurationData response
         * @param configuration
         */
        @Override public void onNext(ConfigurationData configuration) {
            mGetConfigurationInteractor.setImageUrl(configuration);
            Constants.IMAGE_URL_POSTER = configuration.getImageUrlPoster();
            Constants.IMAGE_URL_BACKDROP = configuration.getImageUrlBackDrop();
            mConfigurationView.hideProgress();
            mConfigurationView.startPopularTvShowsActivity();
        }
    }

}
