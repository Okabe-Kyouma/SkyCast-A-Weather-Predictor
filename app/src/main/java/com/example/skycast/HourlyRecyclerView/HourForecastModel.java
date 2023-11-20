package com.example.skycast.HourlyRecyclerView;

import com.example.skycast.Model.WeatherModel.condition;

public class HourForecastModel {

    private String time;
    private float temp_c;
    private condition condition;

    public HourForecastModel(String time,float temp_c,condition condition){
        this.time = time;
        this.temp_c = temp_c;
        this.condition = condition;
    }

    public HourForecastModel(){};


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
