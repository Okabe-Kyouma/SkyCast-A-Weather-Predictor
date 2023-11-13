package com.example.skycast.Recyler_View;

public class CityList {

    public String cityName;
    public String stateName;
    public String countryName;

    public CityList(){}

    public CityList(String cityName, String stateName, String countryName) {
        this.cityName = cityName;
        this.stateName = stateName;
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
