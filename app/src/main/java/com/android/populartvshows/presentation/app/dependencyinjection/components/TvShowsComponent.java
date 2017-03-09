package com.android.populartvshows.presentation.app.dependencyinjection.components;

import com.android.populartvshows.presentation.app.dependencyinjection.modules.TvShowsModule;
import com.android.populartvshows.presentation.app.dependencyinjection.scopes.PerActivity;
import com.android.populartvshows.presentation.ui.fragments.PopularTvShowsFragment;

import dagger.Component;

/**
 * @author diego.galico
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = TvShowsModule.class)
public interface TvShowsComponent {
    void inject (PopularTvShowsFragment popularTvShowsFragment);
}
