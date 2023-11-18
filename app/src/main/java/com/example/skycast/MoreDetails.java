package com.example.skycast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.skycast.Model.WeatherForecast.ApiResultForecast;
import com.example.skycast.WeatherForecast.OnFetchWeatherForecastListener;
import com.example.skycast.WeatherForecast.RequestManagerWeatherForecast;

public class MoreDetails extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_more_details);

        OnFetchWeatherForecastListener listener = new OnFetchWeatherForecastListener() {
            @Override
            public void onFetchDate(ApiResultForecast apiResultForecast, String message) {

                Log.d("huehue","current air quality o3 level: " +  apiResultForecast.getForecast().getForecastday().get(0).getAstro().getSunset());

            }

            @Override
            public void onError(String message) {

                Log.d("huehue","message error");

            }
        };


        RequestManagerWeatherForecast requestManagerWeatherForecast = new RequestManagerWeatherForecast(this);

        requestManagerWeatherForecast.fetchWeatherForecastDetails(listener,"Agra");




    }
}