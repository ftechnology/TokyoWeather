package com.farukhossain.tokyoweather.webservice;

import com.farukhossain.tokyoweather.model.WeatherDataList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetWeatherDataService {
    String CITY_ID = "1850147"; // TOKYO CITY ID
    String API_KEY = "ce10471789a42da7c7fe792fef884ad6";
    int CNT_DAYS = 16; // number of days returned (from 1 to 16)

    //For temperature in Fahrenheit use units=imperial
    //For temperature in Celsius use units=metric
    //Temperature in Kelvin is used by default,
    String TEMP_UNIT = "metric";

    String REQUEST_PARAM = "daily?id=" + CITY_ID + "&cnt=" + CNT_DAYS + "&APPID=" + API_KEY + "&units=" + TEMP_UNIT;

    @GET(REQUEST_PARAM)
    Call<WeatherDataList> getAllData();
}
