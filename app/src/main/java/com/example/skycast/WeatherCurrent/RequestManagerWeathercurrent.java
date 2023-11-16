package com.example.skycast.WeatherCurrent;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.skycast.Model.ApiResult;
import com.example.skycast.Model.WeatherModel.ApiResultCurrent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManagerWeathercurrent {

    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManagerWeathercurrent(Context context){
        this.context = context;
    }


    public void fetchCurrentWeatherDetails(OnFetchCurrentWeatherListener listener,String getWeatherOfCity){

        CallCurrentWeather callCurrentWeather = retrofit.create(CallCurrentWeather.class);
        Call<ApiResultCurrent> call =callCurrentWeather.callApi("33b0c5aebdcb4a9497e124359232010",getWeatherOfCity,"yes");

        Log.d("huehue","inside fetchCurrentWeatherDetails: with city name: " + getWeatherOfCity);

        try{

            call.enqueue(new Callback<ApiResultCurrent>() {
                @Override
                public void onResponse(Call<ApiResultCurrent> call, Response<ApiResultCurrent> response) {

                    Log.d("huehue response code of currentWeather " , String.valueOf(response.code()) + "call: " + call.request().toString());

                    if(!response.isSuccessful()){
                        Toast.makeText(context, "Error Occurred!-NOt successfull", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context, "Response SuccessFull", Toast.LENGTH_SHORT).show();
                        listener.onFetchData(response.body(),response.message());

                    }

                }

                @Override
                public void onFailure(Call<ApiResultCurrent> call, Throwable t) {

                    Log.d("huehue","tomtal failure " + t.toString());
                     listener.onError("error");

                }
            });


        }
        catch (Exception e){
            e.printStackTrace();
        }




    }



}

interface CallCurrentWeather{

    @GET("current.json")
    Call<ApiResultCurrent> callApi(

            @Query("key") String key,
            @Query("q") String q,
            @Query("aqi") String aqi


    );

}

