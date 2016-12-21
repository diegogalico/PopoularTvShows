package com.dashlane.populartvshows.presentation.app.dependencyinjection.components;

import com.dashlane.populartvshows.presentation.app.dependencyinjection.modules.TheMovieModule;
import com.dashlane.populartvshows.presentation.app.dependencyinjection.scopes.PerActivity;
import com.dashlane.populartvshows.data.rest.RestTvShows;

import dagger.Component;

/**
 * @author diego.galico
 */
@PerActivity
@Component(dependencies = NetworkComponent.class, modules = TheMovieModule.class)
public interface TheMovieComponent {
    void inject(RestTvShows restTvShows);
}
