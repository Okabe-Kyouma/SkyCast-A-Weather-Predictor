package com.example.skycast.Model.WeatherForecast;

import java.util.List;

public class forecast {

   private List<forecastDay> forecastday;

    public List<forecastDay> getForecastday() {
        return forecastday;
    }

    public void setForecastday(List<forecastDay> forecastday) {
        this.forecastday = forecastday;
    }
}
