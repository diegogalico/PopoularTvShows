package com.letgo.populartvshows.app.dependencyinjection.modules;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.letgo.populartvshows.BuildConfig;
import com.letgo.populartvshows.app.Constants;
import com.letgo.populartvshows.app.PopularTvShowsApplication;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

/**
 * @author diego.galico
 */
@Module
public class NetworkModule {

    String mBaseUrl;
    private static final String CACHE_CONTROL = "Cache-Control";

    public NetworkModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(PopularTvShowsApplication application) {
        Cache cache = null;
        try {
            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            cache = new Cache(application.getCacheDir(), cacheSize);
        } catch (Exception e) {
            Timber.e(e, "Could not create Cache!");
        }
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setLenient();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Timber.d(message);
                    }
                });
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HEADERS : NONE);
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    Interceptor provideCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());

                // re-write response header to force use of cache
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(Constants.MAX_AGE_CACHE_RESPONSE_MINUTES, TimeUnit.MINUTES)
                        .build();

                return response.newBuilder()
                               .header(CACHE_CONTROL, cacheControl.toString())
                               .build();
            }
        };
    }

    @Provides
    @Named("provideOfflineCacheInterceptor")
    @Singleton
    Interceptor provideOfflineCacheInterceptor(final PopularTvShowsApplication application) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                if (!application.hasNetwork()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(Constants.MAX_STALE_CACHE_DAYS, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                                     .cacheControl(cacheControl)
                                     .build();
                }

                return chain.proceed(request);
            }
        };
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
