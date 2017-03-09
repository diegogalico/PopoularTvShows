package com.android.populartvshows.domain.interactors.impl;

import com.android.populartvshows.data.entities.ConfigurationEntity;
import com.android.populartvshows.data.rest.RestData;
import com.android.populartvshows.domain.ConfigurationData;
import com.android.populartvshows.domain.executors.PostExecutionThread;
import com.android.populartvshows.domain.executors.ThreadExecutor;
import com.android.populartvshows.domain.interactors.ConfigurationInteractor;
import com.android.populartvshows.domain.mapper.ConfigurationEntityDataMapper;
import com.android.populartvshows.presentation.app.Constants;

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
