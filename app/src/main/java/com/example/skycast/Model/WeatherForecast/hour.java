package com.example.skycast.Model.WeatherForecast;

import com.example.skycast.Model.WeatherModel.condition;

public class hour {

    private long time_epoch;
    private String time;
    private double temp_c;
    private double temp_f;
    private int is_day;
    private condition condition;
    private float wind_mph;
    private float wind_kph;
    private int wind_degree;
    private String wind_dir;
    private float pressure_mb;
    private float pressure_in;
    private float precip_mm;
    private float precip_in;
    private int humidity;
    private int cloud;
    private float feelslike_c;
    private float feelslike_f;
    private float windchill_c;
    private float windchill_f;
    private float heatindex_c;
    private float heatindex_f;
    private float dewpoint_c;
    private float dewpoint_f;
    private byte will_it_rain;
    private byte chance_of_rain;
    private byte will_it_snow;
    private byte chance_of_snow;
    private float vis_km;
    private float vis_miles;
    private float gust_mph;
    private float gust_kph;
    private float uv;

    public long getTime_epoch() {
        return time_epoch;
    }

    public void setTime_epoch(long time_epoch) {
        this.time_epoch = time_epoch;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(double temp_c) {
        this.temp_c = temp_c;
    }

    public double getTemp_f() {
        return temp_f;
    }

    public void setTemp_f(double temp_f) {
        this.temp_f = temp_f;
    }

    public int getIs_day() {
        return is_day;
    }

    public void setIs_day(int is_day) {
        this.is_day = is_day;
    }

    public com.example.skycast.Model.WeatherModel.condition getCondition() {
        return condition;
    }

    public void setCondition(com.example.skycast.Model.WeatherModel.condition condition) {
        this.condition = condition;
    }

    public float getWind_mph() {
        return wind_mph;
    }

    public void setWind_mph(float wind_mph) {
        this.wind_mph = wind_mph;
    }

    public float getWind_kph() {
        return wind_kph;
    }

    public void setWind_kph(float wind_kph) {
        this.wind_kph = wind_kph;
    }

    public int getWind_degree() {
        return wind_degree;
    }

    public void setWind_degree(int wind_degree) {
        this.wind_degree = wind_degree;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public float getPressure_mb() {
        return pressure_mb;
    }

    public void setPressure_mb(float pressure_mb) {
        this.pressure_mb = pressure_mb;
    }

    public float getPressure_in() {
        return pressure_in;
    }

    public void setPressure_in(float pressure_in) {
        this.pressure_in = pressure_in;
    }

    public float getPrecip_mm() {
        return precip_mm;
    }

    public void setPrecip_mm(float precip_mm) {
        this.precip_mm = precip_mm;
    }

    public float getPrecip_in() {
        return precip_in;
    }

    public void setPrecip_in(float precip_in) {
        this.precip_in = precip_in;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getCloud() {
        return cloud;
    }

    public void setCloud(int cloud) {
        this.cloud = cloud;
    }

    public float getFeelslike_c() {
        return feelslike_c;
    }

    public void setFeelslike_c(float feelslike_c) {
        this.feelslike_c = feelslike_c;
    }

    public float getFeelslike_f() {
        return feelslike_f;
    }

    public void setFeelslike_f(float feelslike_f) {
        this.feelslike_f = feelslike_f;
    }

    public float getWindchill_c() {
        return windchill_c;
    }

    public void setWindchill_c(float windchill_c) {
        this.windchill_c = windchill_c;
    }

    public float getWindchill_f() {
        return windchill_f;
    }

    public void setWindchill_f(float windchill_f) {
        this.windchill_f = windchill_f;
    }

    public float getHeatindex_c() {
        return heatindex_c;
    }

    public void setHeatindex_c(float heatindex_c) {
        this.heatindex_c = heatindex_c;
    }

    public float getHeatindex_f() {
        return heatindex_f;
    }

    public void setHeatindex_f(float heatindex_f) {
        this.heatindex_f = heatindex_f;
    }

    public float getDewpoint_c() {
        return dewpoint_c;
    }

    public void setDewpoint_c(float dewpoint_c) {
        this.dewpoint_c = dewpoint_c;
    }

    public float getDewpoint_f() {
        return dewpoint_f;
    }

    public void setDewpoint_f(float dewpoint_f) {
        this.dewpoint_f = dewpoint_f;
    }

    public byte getWill_it_rain() {
        return will_it_rain;
    }

    public void setWill_it_rain(byte will_it_rain) {
        this.will_it_rain = will_it_rain;
    }

    public byte getChance_of_rain() {
        return chance_of_rain;
    }

    public void setChance_of_rain(byte chance_of_rain) {
        this.chance_of_rain = chance_of_rain;
    }

    public byte getWill_it_snow() {
        return will_it_snow;
    }

    public void setWill_it_snow(byte will_it_snow) {
        this.will_it_snow = will_it_snow;
    }

    public byte getChance_of_snow() {
        return chance_of_snow;
    }

    public void setChance_of_snow(byte chance_of_snow) {
        this.chance_of_snow = chance_of_snow;
    }

    public float getVis_km() {
        return vis_km;
    }

    public void setVis_km(float vis_km) {
        this.vis_km = vis_km;
    }

    public float getVis_miles() {
        return vis_miles;
    }

    public void setVis_miles(float vis_miles) {
        this.vis_miles = vis_miles;
    }

    public float getGust_mph() {
        return gust_mph;
    }

    public void setGust_mph(float gust_mph) {
        this.gust_mph = gust_mph;
    }

    public float getGust_kph() {
        return gust_kph;
    }

    public void setGust_kph(float gust_kph) {
        this.gust_kph = gust_kph;
    }

    public float getUv() {
        return uv;
    }

    public void setUv(float uv) {
        this.uv = uv;
    }
}
