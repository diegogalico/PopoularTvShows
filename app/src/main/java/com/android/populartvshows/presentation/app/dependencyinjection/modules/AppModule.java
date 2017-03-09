package com.android.populartvshows.presentation.app.dependencyinjection.modules;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.populartvshows.data.executors.JobExecutor;
import com.android.populartvshows.domain.executors.PostExecutionThread;
import com.android.populartvshows.domain.executors.ThreadExecutor;
import com.android.populartvshows.presentation.app.PopularTvShowsApplication;
import com.android.populartvshows.presentation.app.UIThread;

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

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(PopularTvShowsApplication popularTvShowsApplication) {
        return PreferenceManager.getDefaultSharedPreferences(popularTvShowsApplication);
    }



}

