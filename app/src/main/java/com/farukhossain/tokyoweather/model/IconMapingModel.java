package com.farukhossain.tokyoweather.model;

import com.farukhossain.tokyoweather.R;

public class IconMapingModel {

    static final String STR_CLEAR = "sky is clear";
    static final String STR_CLOUDY = "overcast clouds";
    static final String STR_FOG = "fog";
    static final String STR_LIGHT_CLOUDS = "few clouds";
    static final String STR_RAIN = "moderate rain";
    static final String STR_LIGHT_RAIN = "light rain";
    static final String STR_BROKEN_CLOUDS = "broken clouds";
    static final String STR_SCATTERED = "scattered clouds";
    static final String SRT_HEAVY_RAIN = "heavy intensity rain";
    static final String SRT_THUNDER_RAIN = "thunder storm with light rain";
    static final String SRT_THUNDER_STORM = "thunder storm";
    static final String SRT_SNOW = "snow";
    static final String SRT_LIGHT_SNOW = "light snow";

    private static IconMapingModel iconMapingModel;

    public static IconMapingModel getInstance() {
        if (iconMapingModel == null) {
            iconMapingModel = new IconMapingModel();
        }
        return iconMapingModel;
    }

    public int getWeatherIcon(String status) {
        int iconId = R.drawable.ic_clear;

        switch (status) {
            case STR_CLEAR:
                iconId = R.drawable.ic_clear;
                break;
            case STR_CLOUDY:
                iconId = R.drawable.ic_cloudy;
                break;
            case STR_FOG:
                iconId = R.drawable.ic_fog;
                break;
            case STR_LIGHT_CLOUDS:
                iconId = R.drawable.ic_light_clouds;

            case STR_BROKEN_CLOUDS:
                iconId = R.drawable.ic_light_clouds;

            case STR_SCATTERED:
                iconId = R.drawable.ic_light_clouds;
                break;

            case STR_RAIN:
                iconId = R.drawable.ic_rain;
                break;

            case STR_LIGHT_RAIN:
                iconId = R.drawable.ic_light_rain;
                break;

            case SRT_HEAVY_RAIN:
                iconId = R.drawable.ic_storm;
            case SRT_THUNDER_RAIN:
                iconId = R.drawable.ic_storm;
            case SRT_THUNDER_STORM:
                iconId = R.drawable.ic_storm;
                break;

            case SRT_SNOW:
                iconId = R.drawable.ic_snow;
            case SRT_LIGHT_SNOW:
                iconId = R.drawable.ic_storm;
                break;
        }

        return iconId;
    }

    public int getArtWeatherIcon(String status) {
        int iconId = R.drawable.art_clear;

        switch (status) {
            case STR_CLEAR:
                iconId = R.drawable.art_clear;
                break;
            case STR_CLOUDY:
                iconId = R.drawable.art_clouds;
                break;
            case STR_FOG:
                iconId = R.drawable.art_fog;
                break;
            case STR_LIGHT_CLOUDS:
                iconId = R.drawable.art_light_clouds;

            case STR_BROKEN_CLOUDS:
                iconId = R.drawable.art_light_clouds;

            case STR_SCATTERED:
                iconId = R.drawable.art_light_clouds;
                break;
            case STR_RAIN:
                iconId = R.drawable.art_rain;
                break;
            case STR_LIGHT_RAIN:
                iconId = R.drawable.art_light_rain;
                break;
            case SRT_HEAVY_RAIN:
                iconId = R.drawable.art_storm;
            case SRT_THUNDER_RAIN:
                iconId = R.drawable.art_storm;
            case SRT_THUNDER_STORM:
                iconId = R.drawable.art_storm;
                break;

            case SRT_SNOW:
                iconId = R.drawable.art_snow;
            case SRT_LIGHT_SNOW:
                iconId = R.drawable.art_snow;
                break;
        }
        return iconId;
    }
}
