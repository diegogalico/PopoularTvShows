package com.letgo.populartvshows.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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

import timber.log.Timber;
import timber.log.Timber.DebugTree;

/**
 * @author diego.galico
 */
public class PopularTvShowsApplication extends Application {

    private NetworkComponent mNetworkComponent;
    private TheMovieComponent mTheMovieComponent;
    private AppComponent mAppComponent;

    /**
     * Static method for get application context
     *
     * @param context
     *
     * @return
     */
    public static PopularTvShowsApplication get(Context context) {
        return (PopularTvShowsApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //initiate Crittercism
        Crittercism.initialize(this, Constants.CRITTERCISM_API_KEY);
        // initiate Timber
        Timber.plant(new DebugTree());

        mNetworkComponent = DaggerNetworkComponent.builder()
                                                  // list of modules that are part of this component need to be created here too
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
        return checkIfHasNetwork();
    }

    private boolean checkIfHasNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
