package com.example.skycast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.skycast.DailyRecycleView.DailyForecastModel;
import com.example.skycast.HourlyRecyclerView.HourForecastModel;
import com.example.skycast.HourlyRecyclerView.ProgrammingAdapter;
import com.example.skycast.Model.WeatherForecast.ApiResultForecast;
import com.example.skycast.Model.WeatherModel.ApiResultCurrent;
import com.example.skycast.Recyler_View.CityList;
import com.example.skycast.WeatherCurrent.OnFetchCurrentWeatherListener;
import com.example.skycast.WeatherCurrent.RequestManagerWeathercurrent;
import com.example.skycast.WeatherForecast.OnFetchWeatherForecastListener;
import com.example.skycast.WeatherForecast.RequestManagerWeatherForecast;

import java.util.ArrayList;
import java.util.List;

public class MoreDetails extends AppCompatActivity {

    ScrollView scrollView;
    TextView co,no2,o3,so2,pm25,pm10;
    List<HourForecastModel> hourForecastModelList;
    String currentHourString = MainActivity.CurrentHourOfCurrentCity;
    RecyclerView recyclerView,dailyRecyclerView;
    AnimationDrawable animationDrawable;
    OnFetchCurrentWeatherListener onFetchCurrentWeatherListener;
    List<DailyForecastModel> dailyForecastModelList;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_more_details);



        scrollView = findViewById(R.id.viewScroll);
        recyclerView = findViewById(R.id.recycle2);
        dailyRecyclerView = findViewById(R.id.recycleDaily);
        co = findViewById(R.id.co);
        no2 = findViewById(R.id.no2);
        o3 = findViewById(R.id.o3);
        so2 = findViewById(R.id.so2);
        pm25 = findViewById(R.id.pm25);
        pm10 = findViewById(R.id.pm10);
        hourForecastModelList = new ArrayList<>();
        dailyForecastModelList = new ArrayList<>();


        scrollView.setBackground(MainActivity.currentBackgroundDrawable);
        animationDrawable = (AnimationDrawable) scrollView.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();


        OnFetchWeatherForecastListener listener = new OnFetchWeatherForecastListener() {
            @Override
            public void onFetchDate(ApiResultForecast apiResultForecast, String message) {


                o3.setText(String.valueOf(apiResultForecast.getCurrent().getAir_quality().getO3()));
                so2.setText(String.valueOf(apiResultForecast.getCurrent().getAir_quality().getSo2()));
                co.setText(String.valueOf(apiResultForecast.getCurrent().getAir_quality().getCo()));
                no2.setText(String.valueOf(apiResultForecast.getCurrent().getAir_quality().getNo2()));
                pm10.setText(String.valueOf(apiResultForecast.getCurrent().getAir_quality().getPm10()));
                pm25.setText(String.valueOf(apiResultForecast.getCurrent().getAir_quality().getPm2_5()));






                //Hourly forecast

                for(int a = 0;a<=23;a++){


                    if(a>Integer.parseInt(currentHourString)) {

                        try {

                            HourForecastModel hr = new HourForecastModel();

                            String ss = apiResultForecast.getForecast().getForecastday().get(0).getHour().get(a).getTime().substring(10,16);

                            hr.setTime(ss);
                            hr.setTemp_c((float) apiResultForecast.getForecast().getForecastday().get(0).getHour().get(a).getTemp_c());
                            hr.setCondition(apiResultForecast.getForecast().getForecastday().get(0).getHour().get(a).getCondition());

                            hourForecastModelList.add(hr);
                        }
                        catch (Exception e){
                            Log.d("huehue","exception in hourForecastmodellist: " + e.toString());
                        }

                    }


                }


                //Daily Forecast:

                for(int a = 1;a<10;a++){

                    for(int b = 0;b<=23;b++){

                        try {

                            DailyForecastModel df = new DailyForecastModel();

                            df.setTime(apiResultForecast.getForecast().getForecastday().get(a).getHour().get(b).getTime());
                            df.setTemp_c((float) apiResultForecast.getForecast().getForecastday().get(a).getHour().get(b).getTemp_c());
                            df.setCondition(apiResultForecast.getForecast().getForecastday().get(a).getHour().get(b).getCondition());

                            dailyForecastModelList.add(df);
                        }
                        catch (Exception e){
                            Log.d("huehue","exception in hourForecastmodellist: " + e.toString());
                        }



                    }

                }

                //aqi



//                onFetchCurrentWeatherListener = new OnFetchCurrentWeatherListener() {
//                    @Override
//                    public void onFetchData(ApiResultCurrent resultCurrentList, String message) {
//
//                        Log.d("huehue","air qality: " + resultCurrentList.getAir_quality());
//
//                        o3.setText(String.valueOf(resultCurrentList.getAir_quality().getO3()));
//                        so2.setText(String.valueOf(resultCurrentList.getAir_quality().getSo2()));
//                        co.setText(String.valueOf(resultCurrentList.getAir_quality().getCo()));
//                        no2.setText(String.valueOf(resultCurrentList.getAir_quality().getNo2()));
//                        pm10.setText(String.valueOf(resultCurrentList.getAir_quality().getPm10()));
//                        pm25.setText(String.valueOf(resultCurrentList.getAir_quality().getPm2_5()));
//
//
//                    }
//
//                    @Override
//                    public void onError(String message) {
//
//                        Log.d("huehue","error");
//
//                    }
//                };


//                RequestManagerWeathercurrent requestManagerWeathercurrent = new RequestManagerWeathercurrent(MoreDetails.this);
//                requestManagerWeathercurrent.fetchCurrentWeatherDetails(null,MainActivity.selectedCityName);






                Log.d("huehue","size of hour list: " + hourForecastModelList.size() + " and size of daily list: " + dailyForecastModelList.size());


                displayResult(hourForecastModelList);
                displayDailyResult(dailyForecastModelList);



            }

            @Override
            public void onError(String message) {

                Log.d("huehue","message error");

            }
        };


        RequestManagerWeatherForecast requestManagerWeatherForecast = new RequestManagerWeatherForecast(this);

        requestManagerWeatherForecast.fetchWeatherForecastDetails(listener,MainActivity.selectedCityName);


    }

    public void displayDailyResult(List<DailyForecastModel> data){

        dailyRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        dailyRecyclerView.setAdapter(new com.example.skycast.DailyRecycleView.ProgrammingAdapter(data));

    }

    public void displayResult(List<HourForecastModel> data){
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(new ProgrammingAdapter(data));
        scrollView.setBackground(MainActivity.currentBackgroundDrawable);
        animationDrawable = (AnimationDrawable) scrollView.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}