package com.example.skycast.WeatherForecast;

import com.example.skycast.Model.WeatherForecast.ApiResultForecast;

public interface OnFetchWeatherForecastListener {

    void onFetchDate(ApiResultForecast apiResultForecast,String message);

    void onError(String message);

}
