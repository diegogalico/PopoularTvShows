package com.dashlane.populartvshows.app.dependencyinjection.components;

import com.dashlane.populartvshows.app.dependencyinjection.modules.ConfigurationModule;
import com.dashlane.populartvshows.app.dependencyinjection.scopes.PerActivity;
import com.dashlane.populartvshows.presentation.ui.fragments.SplashFragment;

import dagger.Component;

/**
 * @author diego.galico
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ConfigurationModule.class)
public interface ConfigurationComponent {
    void inject (SplashFragment splashFragment);
}
