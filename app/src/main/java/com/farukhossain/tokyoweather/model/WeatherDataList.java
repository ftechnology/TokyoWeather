package com.farukhossain.tokyoweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherDataList {

    @SerializedName("list")
    private ArrayList<ResponseData> weatherList;

    public ArrayList<ResponseData> getWeatherArrayList() {
        return weatherList;
    }

    public void setEmployeeArrayList(ArrayList<ResponseData> weatherArrayList) {
        this.weatherList = weatherArrayList;
    }
}
