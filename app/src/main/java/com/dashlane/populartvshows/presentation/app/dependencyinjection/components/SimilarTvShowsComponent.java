package com.dashlane.populartvshows.presentation.app.dependencyinjection.components;

import com.dashlane.populartvshows.presentation.app.dependencyinjection.modules.SimilarTvShowsModule;
import com.dashlane.populartvshows.presentation.app.dependencyinjection.scopes.PerActivity;
import com.dashlane.populartvshows.presentation.ui.activities.TvShowDetailActivity;

import dagger.Component;

/**
 * @author diego.galico
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = SimilarTvShowsModule.class)
public interface SimilarTvShowsComponent {
    void inject(TvShowDetailActivity tvShowDetailActivity);
}