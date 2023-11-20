package com.example.skycast.WeatherForecast;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.skycast.Model.WeatherForecast.ApiResultForecast;
import com.example.skycast.Model.WeatherModel.ApiResultCurrent;
import com.example.skycast.WeatherCurrent.OnFetchCurrentWeatherListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManagerWeatherForecast {

    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManagerWeatherForecast(Context context){
        this.context = context;
    }


    public void fetchWeatherForecastDetails(OnFetchWeatherForecastListener listener, String getWeatherOfCity){

        CallWeatherForecast callWeatherForecast = retrofit.create(CallWeatherForecast.class);
        Call<ApiResultForecast> call = callWeatherForecast.callApi("7913c73b31b24cdd86c190529232011",getWeatherOfCity,10,"yes","no");


        Log.d("huehue","inside fetchWeatherForeCastDetails: with city name: " + getWeatherOfCity +" " + "the call " + call.request());

        try{

            call.enqueue(new Callback<ApiResultForecast>() {
                @Override
                public void onResponse(Call<ApiResultForecast> call, Response<ApiResultForecast> response) {

                    if(!response.isSuccessful()){
                        Toast.makeText(context, "Error Occurred!-NOt successfull", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context, "Response is tomtally Successfull", Toast.LENGTH_SHORT).show();
                        listener.onFetchDate(response.body(), response.message());

                    }

                }

                @Override
                public void onFailure(Call<ApiResultForecast> call, Throwable t) {

                    listener.onError("Error");
                    Log.d("huehue","The forecast error: " + t.toString());

                }
            });


        }
        catch (Exception e){
            e.printStackTrace();
        }




    }



}

interface CallWeatherForecast{

    @GET("forecast.json")
    Call<ApiResultForecast> callApi(

            @Query("key") String key,
            @Query("q") String q,
            @Query("days") int days,
            @Query("aqi") String aqi,
            @Query("alerts") String alerts


    );


}
