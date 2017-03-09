package com.android.populartvshows.domain.interactors;


import com.android.populartvshows.domain.ConfigurationData;
import com.android.populartvshows.domain.executors.PostExecutionThread;
import com.android.populartvshows.domain.executors.ThreadExecutor;
import com.android.populartvshows.domain.interactors.base.Interactor;
import com.android.populartvshows.presentation.app.Constants;

/**
 * @author diego.galico
 *
 * ConfigurationInteractor is an interface that define methods to obtain configuration information
 *
 */
public abstract class ConfigurationInteractor extends Interactor {

    protected ConfigurationInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    /**
     * Set image url into {@link Constants}
     *
     * @param configurationData
     */
    public abstract void setImageUrl(ConfigurationData configurationData);

}
