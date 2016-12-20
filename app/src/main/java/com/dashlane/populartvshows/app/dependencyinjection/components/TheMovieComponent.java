package com.dashlane.populartvshows.app.dependencyinjection.components;

import com.dashlane.populartvshows.app.dependencyinjection.modules.TheMovieModule;
import com.dashlane.populartvshows.app.dependencyinjection.scopes.PerActivity;
import com.dashlane.populartvshows.domain.model.rest.RestTvShows;

import dagger.Component;

/**
 * @author diego.galico
 */
@PerActivity
@Component(dependencies = NetworkComponent.class, modules = TheMovieModule.class)
public interface TheMovieComponent {
    void inject(RestTvShows restTvShows);
}
