package com.letgo.populartvshows.app.dependencyinjection.components;

import com.letgo.populartvshows.app.dependencyinjection.modules.ConfigurationModule;
import com.letgo.populartvshows.app.dependencyinjection.scopes.PerActivity;
import com.letgo.populartvshows.presentation.ui.fragments.SplashFragment;

import dagger.Component;

/**
 * @author diego.galico
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ConfigurationModule.class)
public interface ConfigurationComponent {

    void inject (SplashFragment splashFragment);
}
