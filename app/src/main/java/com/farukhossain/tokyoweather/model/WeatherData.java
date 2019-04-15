package com.farukhossain.tokyoweather.model;

import com.google.gson.annotations.SerializedName;

public class WeatherData {

    @SerializedName("main")
    private String main;
    @SerializedName("description")
    private String description;

    public WeatherData(String main, String description) {
        this.main = main;
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
