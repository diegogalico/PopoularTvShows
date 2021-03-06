package com.android.populartvshows.presentation.app.dependencyinjection.components;

import com.android.populartvshows.presentation.app.dependencyinjection.modules.AppModule;
import com.android.populartvshows.presentation.app.dependencyinjection.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * @author diego.galico
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface NetworkComponent {
    Retrofit retrofit();
}
