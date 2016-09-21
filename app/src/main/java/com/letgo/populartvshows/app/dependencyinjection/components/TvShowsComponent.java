package com.letgo.populartvshows.app.dependencyinjection.components;

import com.letgo.populartvshows.app.dependencyinjection.modules.TvShowsModule;
import com.letgo.populartvshows.app.dependencyinjection.scopes.PerActivity;
import com.letgo.populartvshows.presentation.ui.fragments.PopularTvShowsFragment;

import dagger.Component;

/**
 * @author diego.galico
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = TvShowsModule.class)
public interface TvShowsComponent {

    void inject (PopularTvShowsFragment popularTvShowsFragment);
}
