package com.letgo.populartvshows.domain.interactors.impl;

import com.letgo.populartvshows.app.Constants;
import com.letgo.populartvshows.domain.interactors.ConfigurationInteractor;
import com.letgo.populartvshows.domain.model.entities.Configuration;
import com.letgo.populartvshows.domain.model.rest.RestData;
import com.letgo.populartvshows.presentation.presenters.impl.ConfigurationPresenterImpl;
import com.letgo.populartvshows.presentation.presenters.impl.PopularTvShowsPresenterImpl;
import com.letgo.populartvshows.utils.StringUtils;

import javax.inject.Inject;

/**
 * @author diego.galico
 */
public class ConfigurationInteractorImpl implements ConfigurationInteractor {

    private final RestData mRestData;
    private ConfigurationPresenterImpl mConfigurationPresenter;

    @Inject
    public ConfigurationInteractorImpl(RestData mRestData) {
        this.mRestData = mRestData;
    }

    @Override
    public void requestConfiguration() {
        mRestData.getConfiguration(mConfigurationPresenter);
    }

    public void setPresenter(ConfigurationPresenterImpl configurationPresenter) {
        this.mConfigurationPresenter = configurationPresenter;
    }

    @Override
    public void setImageUrl(Configuration configuration) {
        String posterUrl = configuration.getImages().getBase_url();
        String backdropUrl = configuration.getImages().getBase_url();
        String posterImageSize = Constants.DEFAULT_IMAGE_SIZE;
        String backdropImageSize = Constants.DEFAULT_IMAGE_SIZE;
        for (String posterSize : configuration.getImages().getPoster_sizes()) {
             if(posterSize.equals(Constants.POSTER_IMAGE_SIZE)){
                 posterImageSize = posterSize;
             }
        }
        for (String backdropSize : configuration.getImages().getBackdrop_sizes()) {
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
}
