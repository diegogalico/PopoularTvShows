package com.dashlane.populartvshows.domain.interactors;


import com.dashlane.populartvshows.domain.ConfigurationData;
import com.dashlane.populartvshows.domain.executors.PostExecutionThread;
import com.dashlane.populartvshows.domain.executors.ThreadExecutor;
import com.dashlane.populartvshows.domain.interactors.base.Interactor;
import com.dashlane.populartvshows.presentation.app.Constants;

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
