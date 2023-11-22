package com.example.skycast.WeatherCurrent;

import com.example.skycast.Model.WeatherModel.ApiResultCurrent;

import java.util.List;

public interface OnFetchCurrentWeatherListener {

    void onFetchData(ApiResultCurrent resultCurrentList,String message);

    void onError(String message);

    void onCityNotFound(String message);



}
