package com.dashlane.populartvshows.domain.interactors.impl;

import com.dashlane.populartvshows.data.entities.ConfigurationEntity;
import com.dashlane.populartvshows.data.rest.RestData;
import com.dashlane.populartvshows.domain.ConfigurationData;
import com.dashlane.populartvshows.domain.executors.PostExecutionThread;
import com.dashlane.populartvshows.domain.executors.ThreadExecutor;
import com.dashlane.populartvshows.domain.interactors.ConfigurationInteractor;
import com.dashlane.populartvshows.domain.mapper.ConfigurationEntityDataMapper;
import com.dashlane.populartvshows.presentation.app.Constants;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author diego.galico
 *
 * ConfigurationInteractorImpl class is in charge of calling {@link RestData} to obtain configuration response
 *
 */
public class ConfigurationInteractorImpl extends ConfigurationInteractor {

    private final RestData mRestData;
    private final ConfigurationEntityDataMapper mConfigurationEntityDataMapper;

    @Inject
    public ConfigurationInteractorImpl(RestData mRestData, ThreadExecutor threadExecutor,
                                       PostExecutionThread postExecutionThread,
                                       ConfigurationEntityDataMapper configurationEntityDataMapper) {
        super(threadExecutor, postExecutionThread);
        this.mRestData = mRestData;
        mConfigurationEntityDataMapper = configurationEntityDataMapper;
    }

    @Override
    public void setImageUrl(ConfigurationData configurationData) {
        String posterUrl = configurationData.getBaseUrl();
        String backdropUrl = configurationData.getBaseUrl();
        String posterImageSize = Constants.DEFAULT_IMAGE_SIZE;
        String backdropImageSize = Constants.DEFAULT_IMAGE_SIZE;
        for (String posterSize : configurationData.getPosterSizes()) {
            if(posterSize.equals(Constants.POSTER_IMAGE_SIZE)){
                posterImageSize = posterSize;
            }
        }
        for (String backdropSize : configurationData.getBackdropSizes()) {
            if(backdropSize.equals(Constants.BACKDROP_IMAGE_SIZE)){
                backdropImageSize = backdropSize;
            }
        }
        configurationData.setImageUrlPoster(posterUrl + posterImageSize);
        configurationData.setImageUrlBackDrop(backdropUrl + backdropImageSize);
    }

    @Override
    protected Observable<ConfigurationData> buildInteractorObservable() {
        return mRestData.getConfiguration().map(new Func1<ConfigurationEntity, ConfigurationData>() {
            @Override public ConfigurationData call(ConfigurationEntity configurationEntity) {
                return mConfigurationEntityDataMapper.transform(configurationEntity);
            }
        });
    }
}
