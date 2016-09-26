package com.letgo.populartvshows.app.dependencyinjection.modules;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.letgo.populartvshows.app.PopularTvShowsApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author diego.galico
 */
@Module
public class AppModule {

    PopularTvShowsApplication mApplication;

    public AppModule(PopularTvShowsApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    PopularTvShowsApplication providesApplication() {
        return mApplication;
    }


    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(PopularTvShowsApplication popularTvShowsApplication) {
        return PreferenceManager.getDefaultSharedPreferences(popularTvShowsApplication);
    }

}

