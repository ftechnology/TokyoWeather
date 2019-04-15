package com.farukhossain.tokyoweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseData {

    @SerializedName("dt")
    private String dt;
    @SerializedName("temp")
    private TemperatureData temp;
    @SerializedName("weather")
    private ArrayList<WeatherData> weather;

    public ResponseData(String dt, TemperatureData temp, ArrayList<WeatherData> weather) {
        this.dt = dt;
        this.temp = temp;
        this.weather = weather;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public TemperatureData getTemp() {
        return temp;
    }

    public void setTemp(TemperatureData temp) {
        this.temp = temp;
    }

    public ArrayList<WeatherData> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<WeatherData> weather) {
        this.weather = weather;
    }
}
