package com.dashlane.populartvshows.presentation.app;

import com.dashlane.populartvshows.presentation.utils.StringUtils;

/**
 * @author diego.galico
 *
 * Application constants
 */
public class Constants {
    public static final String CRITTERCISM_API_KEY = "72d68d0e0f494f5e9f20e07ced05017500555300";

    public static final int MAX_AGE_CACHE_RESPONSE_MINUTES = 2;
    public static final int MAX_STALE_CACHE_DAYS= 7;

    public static final String API_KEY = "90d95adef3feab2f5beb67d245110c28";
    public static final String THE_MOVIE_ENDPOINT = "http://api.themoviedb.org/";

    public static String DEVICE_LANGUAGE = "en";

    public static String IMAGE_URL_POSTER = StringUtils.EMPTY_STRING;
    public static String IMAGE_URL_BACKDROP = StringUtils.EMPTY_STRING;
    public static final String POSTER_IMAGE_SIZE = "w185";
    public static final String BACKDROP_IMAGE_SIZE = "w780";
    public static final String DEFAULT_IMAGE_SIZE = "original";

    public static boolean START_PAGINATION = false;
}
