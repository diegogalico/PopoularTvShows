package com.letgo.populartvshows.domain.interactors;


import com.letgo.populartvshows.domain.interactors.base.Interactor;
import com.letgo.populartvshows.domain.model.entities.Configuration;
import com.letgo.populartvshows.presentation.presenters.impl.ConfigurationPresenterImpl;

/**
 * @author diego.galico
 */
public interface ConfigurationInteractor extends Interactor {

    void requestConfiguration();

    void setPresenter(ConfigurationPresenterImpl presenter);

    void setImageUrl(Configuration configuration);

}