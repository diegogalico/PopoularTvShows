package com.android.populartvshows.presentation.app;

import android.app.Application;
import android.content.Context;

import com.crittercism.app.Crittercism;
import com.android.populartvshows.BuildConfig;
import com.android.populartvshows.presentation.app.dependencyinjection.components.AppComponent;
import com.android.populartvshows.presentation.app.dependencyinjection.components.DaggerAppComponent;
import com.android.populartvshows.presentation.app.dependencyinjection.components.DaggerNetworkComponent;
import com.android.populartvshows.presentation.app.dependencyinjection.components.DaggerTheMovieComponent;
import com.android.populartvshows.presentation.app.dependencyinjection.components.NetworkComponent;
import com.android.populartvshows.presentation.app.dependencyinjection.components.TheMovieComponent;
import com.android.populartvshows.presentation.app.dependencyinjection.modules.AppModule;
import com.android.populartvshows.presentation.app.dependencyinjection.modules.DomainModule;
import com.android.populartvshows.presentation.app.dependencyinjection.modules.NetworkModule;
import com.android.populartvshows.presentation.app.dependencyinjection.modules.TheMovieModule;
import com.android.populartvshows.presentation.utils.NetworkUtils;
import com.squareup.leakcanary.LeakCanary;

import java.util.Locale;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import timber.log.Timber;

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

        // Initiate LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        // Initiate Crittercism
        Crittercism.initialize(this, Constants.CRITTERCISM_API_KEY);

        // Initiate Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        // Install CustomActivityOnCrash
        CustomActivityOnCrash.install(this);

        // Set device language
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

    public NetworkComponent getNetworkComponent() {

        return mNetworkComponent;
    }


    public boolean hasNetwork() {
        return NetworkUtils.hasNetwork(this);
    }

}
