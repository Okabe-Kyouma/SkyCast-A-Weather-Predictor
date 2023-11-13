package com.example.skycast.CitySearch;

import com.example.skycast.Model.ApiResult;

import java.util.List;

public interface OnFetchDataListener {

    void OnFetchData(List<ApiResult> results, String message);

    void onError(String message);

}
