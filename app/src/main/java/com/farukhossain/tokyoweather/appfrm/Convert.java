/**
 * @author Faruk Hossain
 * <p>
 * Useful for projects, when need some conversion. Update the file as required
 */
package com.farukhossain.tokyoweather.appfrm;


import com.farukhossain.tokyoweather.adapter.WeatherListAdapter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Convert {

    /**
     * Return the integer from string.
     *
     * @param value
     * @return
     */
    public static int toInt(String value) {
        try {
            return NumberFormat.getInstance().parse(value).intValue();
        } catch (ParseException e) {

        }
        return 0;
    }

    /**
     * Return the long from string.
     *
     * @param value
     * @return
     */
    public static long toLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {

        }
        return 0;
    }

    public static String getYearDay(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, EEE", Locale.US);
        return format.format(new Date(timestamp * 1000L));
    }

    public static String getWeekDayName(long timestamp) {
        TimeZone.setDefault(TimeZone.getTimeZone("Japan/Tokyo"));
        SimpleDateFormat format = new SimpleDateFormat("EEEE", Locale.US);
        return format.format(new Date(timestamp * 1000L));
    }

    public static String getTodayDate(long timestamp) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTimeInMillis(timestamp * 1000L);
        Date now = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("MMMM dd", Locale.US);
        String today = simpleDateformat.format(now);
        String date = WeatherListAdapter.STR_TODAY + ", " + today;
        return date;
    }

}
