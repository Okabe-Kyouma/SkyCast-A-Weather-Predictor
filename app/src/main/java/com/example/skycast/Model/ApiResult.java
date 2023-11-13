package com.example.skycast.Model;

import java.util.List;

public class ApiResult {

    public String place_id;
    public String osm_id;
    public String osm_type;
    public String licence;
    public String lat;
    public String lon;
    public List<String> boundingbox;
    public String classs;
    public String type;
    public String display_name;
    public String display_place;
    public String display_address;
    public address address;

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getOsm_id() {
        return osm_id;
    }

    public void setOsm_id(String osm_id) {
        this.osm_id = osm_id;
    }

    public String getOsm_type() {
        return osm_type;
    }

    public void setOsm_type(String osm_type) {
        this.osm_type = osm_type;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public List<String> getBoundingbox() {
        return boundingbox;
    }

    public void setBoundingbox(List<String> boundingbox) {
        this.boundingbox = boundingbox;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getDisplay_place() {
        return display_place;
    }

    public void setDisplay_place(String display_place) {
        this.display_place = display_place;
    }

    public String getDisplay_address() {
        return display_address;
    }

    public void setDisplay_address(String display_address) {
        this.display_address = display_address;
    }

    public com.example.skycast.Model.address getAddress() {
        return address;
    }

    public void setAddress(com.example.skycast.Model.address address) {
        this.address = address;
    }
}
