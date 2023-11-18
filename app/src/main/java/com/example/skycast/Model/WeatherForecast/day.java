package com.example.skycast.Model.WeatherForecast;

import com.example.skycast.Model.WeatherModel.condition;

public class day {

    private float maxtemp_c;
    private float maxtemp_f;
    private float mintemp_c;
    private float mintemp_f;
    private float avgtemp_c;
    private float avgtemp_f;
    private float maxwind_mph;
    private float maxwind_kph;
    private float totalprecip_mm;
    private float totalprecip_in;
    private float totalsnow_cm;
    private float avgis_km;
    private float avgvis_miles;
    private float avghumidity;
    private byte daily_will_it_rain;
    private byte daily_chance_of_rain;
    private byte daily_will_it_snow;
    private byte daily_chance_of_snow;
    private condition condition;
    private float uv;

    public float getMaxtemp_c() {
        return maxtemp_c;
    }

    public void setMaxtemp_c(float maxtemp_c) {
        this.maxtemp_c = maxtemp_c;
    }

    public float getMaxtemp_f() {
        return maxtemp_f;
    }

    public void setMaxtemp_f(float maxtemp_f) {
        this.maxtemp_f = maxtemp_f;
    }

    public float getMintemp_c() {
        return mintemp_c;
    }

    public void setMintemp_c(float mintemp_c) {
        this.mintemp_c = mintemp_c;
    }

    public float getMintemp_f() {
        return mintemp_f;
    }

    public void setMintemp_f(float mintemp_f) {
        this.mintemp_f = mintemp_f;
    }

    public float getAvgtemp_c() {
        return avgtemp_c;
    }

    public void setAvgtemp_c(float avgtemp_c) {
        this.avgtemp_c = avgtemp_c;
    }

    public float getAvgtemp_f() {
        return avgtemp_f;
    }

    public void setAvgtemp_f(float avgtemp_f) {
        this.avgtemp_f = avgtemp_f;
    }

    public float getMaxwind_mph() {
        return maxwind_mph;
    }

    public void setMaxwind_mph(float maxwind_mph) {
        this.maxwind_mph = maxwind_mph;
    }

    public float getMaxwind_kph() {
        return maxwind_kph;
    }

    public void setMaxwind_kph(float maxwind_kph) {
        this.maxwind_kph = maxwind_kph;
    }

    public float getTotalprecip_mm() {
        return totalprecip_mm;
    }

    public void setTotalprecip_mm(float totalprecip_mm) {
        this.totalprecip_mm = totalprecip_mm;
    }

    public float getTotalprecip_in() {
        return totalprecip_in;
    }

    public void setTotalprecip_in(float totalprecip_in) {
        this.totalprecip_in = totalprecip_in;
    }

    public float getTotalsnow_cm() {
        return totalsnow_cm;
    }

    public void setTotalsnow_cm(float totalsnow_cm) {
        this.totalsnow_cm = totalsnow_cm;
    }

    public float getAvgis_km() {
        return avgis_km;
    }

    public void setAvgis_km(float avgis_km) {
        this.avgis_km = avgis_km;
    }

    public float getAvgvis_miles() {
        return avgvis_miles;
    }

    public void setAvgvis_miles(float avgvis_miles) {
        this.avgvis_miles = avgvis_miles;
    }

    public float getAvghumidity() {
        return avghumidity;
    }

    public void setAvghumidity(float avghumidity) {
        this.avghumidity = avghumidity;
    }

    public byte getDaily_will_it_rain() {
        return daily_will_it_rain;
    }

    public void setDaily_will_it_rain(byte daily_will_it_rain) {
        this.daily_will_it_rain = daily_will_it_rain;
    }

    public byte getDaily_chance_of_rain() {
        return daily_chance_of_rain;
    }

    public void setDaily_chance_of_rain(byte daily_chance_of_rain) {
        this.daily_chance_of_rain = daily_chance_of_rain;
    }

    public byte getDaily_will_it_snow() {
        return daily_will_it_snow;
    }

    public void setDaily_will_it_snow(byte daily_will_it_snow) {
        this.daily_will_it_snow = daily_will_it_snow;
    }

    public byte getDaily_chance_of_snow() {
        return daily_chance_of_snow;
    }

    public void setDaily_chance_of_snow(byte daily_chance_of_snow) {
        this.daily_chance_of_snow = daily_chance_of_snow;
    }

    public com.example.skycast.Model.WeatherModel.condition getCondition() {
        return condition;
    }

    public void setCondition(com.example.skycast.Model.WeatherModel.condition condition) {
        this.condition = condition;
    }

    public float getUv() {
        return uv;
    }

    public void setUv(float uv) {
        this.uv = uv;
    }
}
