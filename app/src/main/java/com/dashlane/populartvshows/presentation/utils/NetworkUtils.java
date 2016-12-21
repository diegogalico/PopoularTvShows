package com.dashlane.populartvshows.presentation.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author diego.galico
 *
 * Network utils class
 *
 */
public class NetworkUtils {

    /**
     * Return true if device has network
     * @param context
     * @return
     */
    public static boolean hasNetwork(Context context) {
        return checkIfHasNetwork(context);
    }

    /**
     * Check if device has network and return true in that case
     * @param context
     * @return
     */
    private static boolean checkIfHasNetwork(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
