package com.letgo.populartvshows.app;

import android.app.Application;
import android.content.Context;

import com.crittercism.app.Crittercism;
import com.letgo.populartvshows.app.dependencyinjection.components.AppComponent;
import com.letgo.populartvshows.app.dependencyinjection.components.DaggerAppComponent;
import com.letgo.populartvshows.app.dependencyinjection.components.DaggerNetworkComponent;
import com.letgo.populartvshows.app.dependencyinjection.components.DaggerTheMovieComponent;
import com.letgo.populartvshows.app.dependencyinjection.components.NetworkComponent;
import com.letgo.populartvshows.app.dependencyinjection.components.TheMovieComponent;
import com.letgo.populartvshows.app.dependencyinjection.modules.AppModule;
import com.letgo.populartvshows.app.dependencyinjection.modules.DomainModule;
import com.letgo.populartvshows.app.dependencyinjection.modules.NetworkModule;
import com.letgo.populartvshows.app.dependencyinjection.modules.TheMovieModule;
import com.letgo.populartvshows.utils.NetworkUtils;

import java.util.Locale;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import timber.log.Timber;
import timber.log.Timber.DebugTree;

/**
 * @author diego.galico
 *
 * Application class
 */
public class PopularTvShowsApplication extends Application {

    private NetworkComponent mNetworkComponent;
    private TheMovieComponent mTheMovieComponent;
    private AppComponent mAppComponent;

    /**
     * Static method for get application context
     *
     * @param context
     * @return
     */
    public static PopularTvShowsApplication get(Context context) {
        return (PopularTvShowsApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // initiate Crittercism
        Crittercism.initialize(this, Constants.CRITTERCISM_API_KEY);
        // initiate Timber
        Timber.plant(new DebugTree());
        // install CustomActivityOnCrash
        CustomActivityOnCrash.install(this);
        // set device language
        Constants.DEVICE_LANGUAGE = Locale.getDefault().getLanguage();

        mNetworkComponent = DaggerNetworkComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(Constants.THE_MOVIE_ENDPOINT))
                .build();

        mTheMovieComponent = DaggerTheMovieComponent.builder()
                .networkComponent(mNetworkComponent)
                .theMovieModule(new TheMovieModule())
                .build();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .domainModule(new DomainModule())
                .build();
    }

    public TheMovieComponent getTheMovieComponent() {
        return mTheMovieComponent;
    }

    public AppComponent getAppComponent() {

        return mAppComponent;
    }


    public boolean hasNetwork() {
        return NetworkUtils.hasNetwork(this);
    }

}
