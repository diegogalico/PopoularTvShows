package com.android.populartvshows.presentation.app.dependencyinjection.components;

import com.android.populartvshows.presentation.app.dependencyinjection.modules.ConfigurationModule;
import com.android.populartvshows.presentation.app.dependencyinjection.scopes.PerActivity;
import com.android.populartvshows.presentation.ui.fragments.SplashFragment;

import dagger.Component;

/**
 * @author diego.galico
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ConfigurationModule.class)
public interface ConfigurationComponent {
    void inject (SplashFragment splashFragment);
}
