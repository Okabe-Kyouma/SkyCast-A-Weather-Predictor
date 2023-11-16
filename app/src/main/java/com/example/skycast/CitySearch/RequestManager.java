package com.example.skycast.CitySearch;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.skycast.Model.ApiResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {

    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.locationiq.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context){
        this.context = context;
    }

    public void fetchCityNames(OnFetchDataListener listener,String cityName){

        CallNames callNames = retrofit.create(CallNames.class);
        Call<List<ApiResult>> call = callNames.callApi(cityName,"pk.345416150c4b68a41c1bcfa754a14b76");

        try{

            call.enqueue(new Callback<List<ApiResult>>() {
                @Override
                public void onResponse(Call<List<ApiResult>> call, Response<List<ApiResult>> response) {

//                    Log.d("huehue",response.message().toString() + " and " + response.code() + " and " + response.errorBody().toString() + " and " + response.body() + " and " + response.toString());

                    Log.d("huehue", String.valueOf(response.code()));

                    if(!response.isSuccessful()){
//                        Toast.makeText(context, "Error Occurred!-NOt successfull", Toast.LENGTH_SHORT).show();
                    }
                    else{
//                        Toast.makeText(context, "Response SuccessFull", Toast.LENGTH_SHORT).show();
                        listener.OnFetchData(response.body(),response.message());

                    }

                }

                @Override
                public void onFailure(Call<List<ApiResult>> call, Throwable t) {

//                    Log.d("huehue","tomtal failure " + t.toString());

                    listener.onError("Error Occurred!-FAiled");

                }
            });

        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("huehue","caught Exception: " + e.toString());

        }



    }




}

interface CallNames{

    @GET("autocomplete")

    Call<List<ApiResult>> callApi(

            @Query("q") String q,
            @Query("key") String key


    );

}
