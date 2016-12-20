package com.dashlane.populartvshows.domain.interactors.impl;

import com.dashlane.populartvshows.app.Constants;
import com.dashlane.populartvshows.domain.interactors.ConfigurationInteractor;
import com.dashlane.populartvshows.domain.model.entities.Configuration;
import com.dashlane.populartvshows.domain.model.rest.RestData;
import com.dashlane.populartvshows.presentation.presenters.impl.ConfigurationPresenterImpl;

import javax.inject.Inject;

import rx.Observer;
import timber.log.Timber;

/**
 * @author diego.galico
 *
 * ConfigurationInteractorImpl class is in charge of calling {@link RestData} to obtain configuration response
 *
 */
public class ConfigurationInteractorImpl implements ConfigurationInteractor, Observer<Configuration> {

    private final RestData mRestData;
    private ConfigurationPresenterImpl mConfigurationPresenter;

    @Inject
    public ConfigurationInteractorImpl(RestData mRestData) {
        this.mRestData = mRestData;
    }

    @Override
    public void requestConfiguration() {
        mRestData.getConfiguration(this);
    }

    @Override
    public void setPresenter(ConfigurationPresenterImpl presenter) {
        mConfigurationPresenter = presenter;
    }

    @Override
    public void setImageUrl(Configuration configuration) {
        String posterUrl = configuration.getImages().getBaseUrl();
        String backdropUrl = configuration.getImages().getBaseUrl();
        String posterImageSize = Constants.DEFAULT_IMAGE_SIZE;
        String backdropImageSize = Constants.DEFAULT_IMAGE_SIZE;
        for (String posterSize : configuration.getImages().getPosterSizes()) {
            if(posterSize.equals(Constants.POSTER_IMAGE_SIZE)){
                posterImageSize = posterSize;
            }
        }
        for (String backdropSize : configuration.getImages().getBackdropSizes()) {
            if(backdropSize.equals(Constants.BACKDROP_IMAGE_SIZE)){
                backdropImageSize = backdropSize;
            }
        }
        Constants.IMAGE_URL_POSTER = posterUrl + posterImageSize;
        Constants.IMAGE_URL_BACKDROP = backdropUrl + backdropImageSize;
    }

    @Override
    public void execute() {
        requestConfiguration();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        mConfigurationPresenter.onErrorResponse(e.getMessage());
        Timber.e(e, "onError");
    }

    @Override
    public void onNext(Configuration configuration) {
        mConfigurationPresenter.onConfigurationResponse(configuration);
    }

}
