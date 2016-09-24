package com.letgo.populartvshows.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author diego.galico
 *
 * String utils class
 *
 */
public class StringUtils {

    public static final String EMPTY_STRING = "";
    public static final String DATE_FORMAT_ENTRY = "yyyy-MM-dd";
    public static final String DATE_FORMAT_OUTPUT = "MMM dd, yyyy";

    /**
     * Convert date string from DATE_FORMAT_ENTRY to DATE_FORMAT_OUTPUT
     * @param dateString
     * @return converted date string
     */
    public static String convertStringDate(String dateString) {
        SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT_ENTRY);
        SimpleDateFormat fmtOut = new SimpleDateFormat(DATE_FORMAT_OUTPUT);

        Date date = null;
        try {
            date = fmt.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fmtOut.format(date);
    }
}
