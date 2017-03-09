package com.android.populartvshows.presentation.app.dependencyinjection.components;

import com.android.populartvshows.presentation.app.dependencyinjection.modules.TheMovieModule;
import com.android.populartvshows.presentation.app.dependencyinjection.scopes.PerActivity;
import com.android.populartvshows.data.rest.RestTvShows;

import dagger.Component;

/**
 * @author diego.galico
 */
@PerActivity
@Component(dependencies = NetworkComponent.class, modules = TheMovieModule.class)
public interface TheMovieComponent {
    void inject(RestTvShows restTvShows);
}
