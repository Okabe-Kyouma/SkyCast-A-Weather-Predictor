package com.example.skycast.Model.WeatherForecast;

import com.example.skycast.Model.WeatherModel.location;

public class ApiResultForecast {

    private location location;
    private current current;
    private forecast forecast;

    public com.example.skycast.Model.WeatherModel.location getLocation() {
        return location;
    }

    public void setLocation(com.example.skycast.Model.WeatherModel.location location) {
        this.location = location;
    }

    public com.example.skycast.Model.WeatherForecast.current getCurrent() {
        return current;
    }

    public void setCurrent(com.example.skycast.Model.WeatherForecast.current current) {
        this.current = current;
    }

    public com.example.skycast.Model.WeatherForecast.forecast getForecast() {
        return forecast;
    }

    public void setForecast(com.example.skycast.Model.WeatherForecast.forecast forecast) {
        this.forecast = forecast;
    }
}
