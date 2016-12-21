package com.dashlane.populartvshows.domain.interactors;


import com.dashlane.populartvshows.app.Constants;
import com.dashlane.populartvshows.domain.interactors.base.Interactor;
import com.dashlane.populartvshows.data.entities.Configuration;
import com.dashlane.populartvshows.data.rest.RestData;
import com.dashlane.populartvshows.presentation.presenters.impl.ConfigurationPresenterImpl;

/**
 * @author diego.galico
 *
 * ConfigurationInteractor is an interface that define methods to obtain configuration information
 *
 */
public interface ConfigurationInteractor extends Interactor {

    /**
     * Request configuration to {@link RestData} class
     */
    void requestConfiguration();

    /**
     * Set presenter to class variable in order to return information to {@link ConfigurationPresenterImpl} class
     * @param presenter
     */
    void setPresenter(ConfigurationPresenterImpl presenter);

    /**
     * Set image url into {@link Constants}
     * @param configuration
     */
    void setImageUrl(Configuration configuration);

    /**
     * Interface implemented by {@link ConfigurationPresenterImpl} class
     */
    interface ConfigurationResponse {

        /**
         * Retrieve Configuration response
         * @param configuration
         */
        void onConfigurationResponse(Configuration configuration);

        /**
         * Retrieve error response
         * @param error
         */
        void onErrorResponse(String error);
    }

}
