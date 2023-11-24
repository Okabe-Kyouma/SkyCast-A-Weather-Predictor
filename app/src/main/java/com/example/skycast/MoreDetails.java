package com.example.skycast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoreDetails extends AppCompatActivity {

    ScrollView scrollView;
    TextView aqi_text,aqi_result,aqi_result_advice;
    List<HourForecastModel> hourForecastModelList;
    String currentHourString = MainActivity.CurrentHourOfCurrentCity;
    RecyclerView recyclerView,dailyRecyclerView;
    AnimationDrawable animationDrawable;
    OnFetchCurrentWeatherListener onFetchCurrentWeatherListener;
    List<DailyForecastModel> dailyForecastModelList;
    float valueOfpm25,valueOfpm10;
    AlertDialog.Builder builder,builder1;
    AlertDialog dialog,dialog1;
    ImageView aqi_last_img;
    TextView sunRise,sunSet,moonRise,moonSet,humidity,windSpeed,uv,viSiBiliTy;
    OnFetchWeatherForecastListener listener;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_more_details);

        RequestManagerWeatherForecast requestManagerWeatherForecast = new RequestManagerWeatherForecast(this);



        scrollView = findViewById(R.id.viewScroll);
        recyclerView = findViewById(R.id.recycle2);
        dailyRecyclerView = findViewById(R.id.recycleDaily);
        sunRise = findViewById(R.id.sunrise_time);
        sunSet = findViewById(R.id.sunset_time);
        moonRise = findViewById(R.id.moonrise_time);
        moonSet = findViewById(R.id.moonset_time);
        humidity = findViewById(R.id.humidity_time);
        windSpeed = findViewById(R.id.wind_time);
        viSiBiliTy = findViewById(R.id.visi_time);
        uv = findViewById(R.id.uv_time);
        aqi_text = findViewById(R.id.aqi_text);
        aqi_result = findViewById(R.id.aqi_result);
        aqi_result_advice = findViewById(R.id.aqi_result_advice);
        aqi_last_img = findViewById(R.id.aqi_last_img);
        hourForecastModelList = new ArrayList<>();
        dailyForecastModelList = new ArrayList<>();


        scrollView.setBackground(MainActivity.currentBackgroundDrawable);
        animationDrawable = (AnimationDrawable) scrollView.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        builder = new AlertDialog.Builder(MoreDetails.this);
        View view = getLayoutInflater().inflate(R.layout.cutom_progress_bar, null);
        dialog = builder.create();
        builder.setView(view);
        builder.setCancelable(false);
        dialog.show();

        builder1 = new AlertDialog.Builder(MoreDetails.this);
        View view1 = getLayoutInflater().inflate(R.layout.custom_progress_bar,null);
        builder1.setView(view1);
        dialog1 = builder1.create();
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        builder.setCancelable(false);
        dialog1.show();



         listener = new OnFetchWeatherForecastListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onFetchDate(ApiResultForecast apiResultForecast, String message) {

                valueOfpm10 = apiResultForecast.getCurrent().getAir_quality().getPm10();
                valueOfpm25 = apiResultForecast.getCurrent().getAir_quality().getPm2_5();
                aqi_text.setText(String.valueOf(Math.max(valueOfpm25, valueOfpm10)));


                int val = (int) Float.parseFloat(aqi_text.getText().toString());

                if (val < 50) {
                    aqi_result.setText("Good");
                    aqi_result_advice.setText("Air quality is considered satisfactory, and air pollution poses little or no risk.");
                    aqi_last_img.setImageResource(R.drawable.good);
                    aqi_result.setTextColor(Color.GREEN);
                }
                else if (val >= 50 && val < 100) {
                    aqi_result.setText("Moderate");
                    aqi_result_advice.setText("Air quality is acceptable; however, for some pollutants, there may be a moderate health concern for a very small number of people who are unusually sensitive to air pollution.");
                    aqi_last_img.setImageResource(R.drawable.moderate);
                    aqi_result.setTextColor(Color.YELLOW);
                }
                else if (val >= 100 && val < 150) {
                    aqi_result.setText("Unhealthy for Sensitive Groups");
                    aqi_result_advice.setText("Members of sensitive groups, such as individuals with respiratory or heart conditions, may experience health effects. The general public is less likely to be affected.");
                    aqi_last_img.setImageResource(R.drawable.sensitive);
                    aqi_result.setTextColor(Color.rgb(255, 165, 0));
                }
                else if (val >= 150 && val < 200) {
                    aqi_result.setText("Unhealthy");
                    aqi_result_advice.setText("Everyone may begin to experience adverse health effects, and members of sensitive groups may experience more serious effects.");
                    aqi_last_img.setImageResource(R.drawable.unhealthy);
                    aqi_result.setTextColor(Color.rgb(255, 0, 0));
                }
                else if (val >= 200 && val < 300) {
                    aqi_result.setText("Very Unhealthy");
                    aqi_result_advice.setText("Health alert: everyone may experience more serious health effects. It is advisable to avoid prolonged or heavy exertion.");
                    aqi_last_img.setImageResource(R.drawable.veryunhealthy);
                    aqi_result.setTextColor(Color.rgb(204, 0, 102));
                }
                else if (val >= 300) {
                    aqi_result.setText("Hazardous");
                    aqi_result_advice.setText("Health warnings of emergency conditions. The entire population is more likely to be affected.");
                    aqi_last_img.setImageResource(R.drawable.hazardous);
                    aqi_result.setTextColor(Color.rgb(128, 0, 128));
                }





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


                int lengthOfHourList = apiResultForecast.getForecast().getForecastday().size();
                int lengthOfForecastList = apiResultForecast.getForecast().getForecastday().get(0).getHour().size();

                Log.d("huehue1","Size of Hour list in MoreDetails: " + lengthOfHourList + " length of forecast list in more details: " + lengthOfForecastList);

                //Daily Forecast:

                for(int a = 1;a<lengthOfHourList;a++){

                    for(int b = 0;b<lengthOfForecastList;b++){

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




                Log.d("huehue","size of hour list: " + hourForecastModelList.size() + " and size of daily list: " + dailyForecastModelList.size());


                displayResult(hourForecastModelList);
                displayDailyResult(dailyForecastModelList);

                try {

                    sunRise.setText(apiResultForecast.getForecast().getForecastday().get(0).getAstro().getSunrise());
                    sunSet.setText(apiResultForecast.getForecast().getForecastday().get(0).getAstro().getSunset());
                    moonRise.setText(apiResultForecast.getForecast().getForecastday().get(0).getAstro().getMoonrise());
                    moonSet.setText(apiResultForecast.getForecast().getForecastday().get(0).getAstro().getMoonset());
                    humidity.setText(String.valueOf(apiResultForecast.getCurrent().getHumidity()));
                    windSpeed.setText(String.valueOf(apiResultForecast.getCurrent().getWind_kph() + " kph"));
                    uv.setText(String.valueOf(apiResultForecast.getCurrent().getUv()));
                    viSiBiliTy.setText(String.valueOf(apiResultForecast.getCurrent().getVis_km() + " km"));

                }
                catch (Exception e){
                    e.printStackTrace();
                    Log.d("huehue","exception at astro details: " + e.toString());
                }


                dialog.dismiss();
                dialog1.dismiss();



            }

            @Override
            public void onCityNotFound(String message) {


                requestManagerWeatherForecast.fetchWeatherForecastDetails(listener,MainActivity.latitude + "," + MainActivity.longitude);

            }

            @Override
            public void onError(String message) {

                Log.d("huehue","message error");

            }
        };


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

            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MoreDetails.this);
            View view = getLayoutInflater().inflate(R.layout.custom_loading_dialog, null);
            builder.setView(view);
            builder.setCancelable(false);

            TextView titleTextView = view.findViewById(R.id.dialog_title);
            TextView messageTextView = view.findViewById(R.id.dialog_message);

            titleTextView.setText("Back Confirmation");
            messageTextView.setText("If you go back now your current city data will be fetched is that alright?");

            Button btnYes = view.findViewById(R.id.btnYes);
            Button btnNo = view.findViewById(R.id.btnNo);

            btnNo.setText("No");

            androidx.appcompat.app.AlertDialog dialog = builder.create();
            dialog.show();

            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  Intent intent = new Intent(MoreDetails.this,MainActivity.class);
                  startActivity(intent);
                  finish();

                }
            });

            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });


        }
}