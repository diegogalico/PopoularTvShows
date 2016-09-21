package com.letgo.populartvshows.app.dependencyinjection.components;

import com.letgo.populartvshows.app.dependencyinjection.modules.AppModule;
import com.letgo.populartvshows.app.dependencyinjection.modules.DomainModule;
import com.letgo.populartvshows.domain.model.rest.RestTvShows;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author diego.galico
 */
@Singleton
@Component(modules = {
        AppModule.class,
        DomainModule.class,
})

public interface AppComponent {
    RestTvShows restTvShows();
}

