package com.example.skycast.Model.WeatherForecast;

import java.util.List;

public class forecastDay {

    private String date;
    private long date_epoch;
    private day day;
    private astro astro;
    private List<hour> hour;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getDate_epoch() {
        return date_epoch;
    }

    public void setDate_epoch(long date_epoch) {
        this.date_epoch = date_epoch;
    }

    public com.example.skycast.Model.WeatherForecast.day getDay() {
        return day;
    }

    public void setDay(com.example.skycast.Model.WeatherForecast.day day) {
        this.day = day;
    }

    public com.example.skycast.Model.WeatherForecast.astro getAstro() {
        return astro;
    }

    public void setAstro(com.example.skycast.Model.WeatherForecast.astro astro) {
        this.astro = astro;
    }

    public List<com.example.skycast.Model.WeatherForecast.hour> getHour() {
        return hour;
    }

    public void setHour(List<com.example.skycast.Model.WeatherForecast.hour> hour) {
        this.hour = hour;
    }
}
