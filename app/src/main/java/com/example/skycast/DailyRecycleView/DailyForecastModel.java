package com.example.skycast.DailyRecycleView;

import com.example.skycast.Model.WeatherModel.condition;

public class DailyForecastModel {

    private String time;
    private float temp_c;
    private com.example.skycast.Model.WeatherModel.condition condition;

    public DailyForecastModel(String time,float temp_c,condition condition){
        this.time = time;
        this.temp_c = temp_c;
        this.condition = condition;
    }

    public DailyForecastModel(){};

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(float temp_c) {
        this.temp_c = temp_c;
    }

    public com.example.skycast.Model.WeatherModel.condition getCondition() {
        return condition;
    }

    public void setCondition(com.example.skycast.Model.WeatherModel.condition condition) {
        this.condition = condition;
    }
}
