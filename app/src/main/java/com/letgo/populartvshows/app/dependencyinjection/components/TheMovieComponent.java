package com.letgo.populartvshows.app.dependencyinjection.components;

import com.letgo.populartvshows.app.dependencyinjection.modules.TheMovieModule;
import com.letgo.populartvshows.app.dependencyinjection.scopes.PerActivity;
import com.letgo.populartvshows.domain.model.rest.RestTvShows;

import dagger.Component;

/**
 * @author diego.galico
 */
@PerActivity
@Component(dependencies = NetworkComponent.class, modules = TheMovieModule.class)
public interface TheMovieComponent {
    void inject(RestTvShows restTvShows);
}
