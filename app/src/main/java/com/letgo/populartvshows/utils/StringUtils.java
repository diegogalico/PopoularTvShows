package com.letgo.populartvshows.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author diego.galico
 */
public class StringUtils {
    public static final String EMPTY_STRING = "";

    public static String convertStringDate(String dateString) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fmtOut = new SimpleDateFormat("MMM dd, yyyy");

        Date date = null;
        try {
            date = fmt.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fmtOut.format(date);
    }
}
