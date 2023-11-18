package com.example.skycast;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.Lottie;
import com.airbnb.lottie.LottieAnimationView;
import com.example.skycast.CitySearch.OnFetchDataListener;
import com.example.skycast.CitySearch.OnItemClickListener;
import com.example.skycast.CitySearch.RequestManager;
import com.example.skycast.Model.ApiResult;
import com.example.skycast.Model.Data;
import com.example.skycast.Model.WeatherModel.ApiResultCurrent;
import com.example.skycast.Recyler_View.CityList;
import com.example.skycast.Recyler_View.ProgrammingAdapter;
import com.example.skycast.WeatherCurrent.OnFetchCurrentWeatherListener;
import com.example.skycast.WeatherCurrent.RequestManagerWeathercurrent;
import com.github.matteobattilana.weather.PrecipType;
import com.github.matteobattilana.weather.WeatherView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    TextView city,temp,getMoreDetails;
    FusedLocationProviderClient fusedLocationProviderClient;
    ImageView img_weather;
    WeatherView weatherView;

    SearchView searchView;
    RecyclerView recyclerView;
    static String selectedCityName = "";
    List<CityList> selectedCity = new ArrayList<>();
    TextView currentDate;
    private static final long UPDATE_INTERVAL = 1000;
    String currentHourString;
    String currentTimeZone = " Asia/Kolkata";


    private final static int REQUEST_CODE = 100;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        setDate(currentTimeZone);

        recyclerView = findViewById(R.id.recycle);

        getMoreDetails = findViewById(R.id.get_more_details);

        getMoreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,MoreDetails.class);
                startActivity(intent);

            }
        });



        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setDate(currentTimeZone);
                    }
                });
            }
        }, 0, UPDATE_INTERVAL);



//        LottieAnimationView anim = (LottieAnimationView) findViewById(R.id.sun);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                anim.playAnimation();
//            }
//        },3000);


       weatherView = findViewById(R.id.weather_view);
        weatherView.setWeatherData(PrecipType.CLEAR);

        AnimationDrawable animationDrawable = (AnimationDrawable) weatherView.getBackground();

        LinearLayout linearLayoutToolBarId = findViewById(R.id.include);

        AnimationDrawable animationDrawable1 = (AnimationDrawable) linearLayoutToolBarId.getBackground();

        animationDrawable1.setEnterFadeDuration(2500);
        animationDrawable1.setEnterFadeDuration(5000);
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        animationDrawable1.start();


        city = findViewById(R.id.City);
        searchView  = findViewById(R.id.search);
          temp = findViewById(R.id.temp);
          img_weather = findViewById(R.id.img_weather);
         fusedLocationProviderClient  = LocationServices.getFusedLocationProviderClient(this);

         getLastLocation();

         searchView.setOnSearchClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 city.setVisibility(View.INVISIBLE);
             }
         });

         searchView.setOnCloseListener(new SearchView.OnCloseListener() {
             @Override
             public boolean onClose() {
                 city.setVisibility(View.VISIBLE);
                 recyclerView.setVisibility(View.GONE);
                 return false;
             }
         });

        RequestManager requestManager = new RequestManager(this);

         searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String query) {

                 if(query.equals("")) {
                     recyclerView.setVisibility(View.GONE);
                     selectedCity.clear();
                 }

                 requestManager.fetchCityNames(listener,query);

                 if(query.equals("")) {
                     recyclerView.setVisibility(View.GONE);
                     selectedCity.clear();
                 }

                 return false;
             }

             @Override
             public boolean onQueryTextChange(String newText) {

                 if(newText.equals("")) {
                     recyclerView.setVisibility(View.GONE);
                     selectedCity.clear();
                 }


                 requestManager.fetchCityNames(listener,newText);
                 selectedCity.clear();

                 return false;
             }
         });


         if(recyclerView.getVisibility()==View.INVISIBLE || recyclerView.getVisibility()==View.GONE) {

             Log.d("huehue","working or not? ");
             getCurrentWeatherDetails();

         }


    }

    private void getLastLocation(){

        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(Location location) {
                        Geocoder geocoder = null;
                        if (location != null)
                            geocoder = new Geocoder(MainActivity.this, Locale.getDefault());

                        List<Address> addressList = null;
                        try {
                            addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                        lat.setText("Latitude: " + addressList.get(0).getLatitude());
//                        longitude.setText("Longitude: " + addressList.get(0).getLongitude());
//                        add.setText("Address: " + addressList.get(0).getAddressLine(0));
                            city.setText(addressList.get(0).getLocality());

                            currentTimeZone = "Asia/Kolkata";

                            city.setAlpha(0.0f);

                            // Create a ViewPropertyAnimator for the fade-in animation
                            city.animate()
                                    .alpha(1.0f)
                                    .setDuration(1000) // Set the duration in milliseconds
                                    .setListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationStart(Animator animation) {
                                            // Set visibility to visible when the animation starts
                                            city.setVisibility(View.VISIBLE);
                                        }
                                    })
                                    .start();

                            getCurrentWeatherDetails();
//                        country.setText("Country: " + addressList.get(0).getCountryName());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            } else {

                city.setText("New-Delhi");
                getCurrentWeatherDetails();
//            askPermission();

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

   private void askPermission(){
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }
            else{
                Toast.makeText(this, "Please Enable Location", Toast.LENGTH_SHORT).show();
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void OnFetchData(List<ApiResult> results, String message) {

            if(results.isEmpty()){
                Toast.makeText(MainActivity.this, "Error-empty", Toast.LENGTH_SHORT).show();
        }
            else{



                for(ApiResult str : results){

                    if(str.type.equals("city") || str.type.equals("town") || str.type.equals("village")) {
//                        Log.d("huehue", str.address.name + " " + str.address.state + " " + str.address.country);

                        CityList cs = new CityList();
                        cs.cityName = str.address.name;
                        cs.stateName = str.address.state;
                        cs.countryName = str.address.country;

                        selectedCity.add(cs);

                    }

                }

                displayResult(selectedCity);

//                Log.d("huehue", "size  of selectcity list: " +  String.valueOf(selectedCity.size()));

            }

    }

        @Override
        public void onError(String message) {

            Toast.makeText(MainActivity.this, "Error-onError", Toast.LENGTH_SHORT).show();
        }

        };

    public void displayResult(List<CityList> data){
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ProgrammingAdapter(data,this::OnItemClick));
    }

    public static void setSelectedCityFromRecycleView(String city_Name){
//        city.setText(city_Name);
        selectedCityName = city_Name;
    }


    @Override
    public void OnItemClick(String name) {

        city.setText(name);
        recyclerView.setVisibility(View.GONE);
        searchView.clearFocus();
        searchView.setQuery("", false);
        searchView.setIconified(true);
        selectedCity.clear();

        getCurrentWeatherDetails();

        city.setAlpha(0.0f);

        // Create a ViewPropertyAnimator for the fade-in animation
        city.animate()
                .alpha(1.0f)
                .setDuration(3000) // Set the duration in milliseconds
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        // Set visibility to visible when the animation starts
                        city.setVisibility(View.VISIBLE);
                    }
                })
                .start();

        city.setShadowLayer(5,0,0, Color.WHITE);


    }













    RequestManagerWeathercurrent requestManagerWeathercurrent = new RequestManagerWeathercurrent(this);


    private void getCurrentWeatherDetails(){
        Log.d("huehue","Calling GetcurrentweatherDetails with cityName: " + city.getText().toString());
        requestManagerWeathercurrent.fetchCurrentWeatherDetails(currentWeatherListener,city.getText().toString());
    }


    private final OnFetchCurrentWeatherListener currentWeatherListener = new OnFetchCurrentWeatherListener() {
        @Override
        public void onFetchData(ApiResultCurrent resultCurrentList, String message) {

            temp.setText("Temperature: " + resultCurrentList.getCurrent().getTemp_c());

            Log.d("huehue","the current weather fetch is working fine: " + resultCurrentList.getCurrent().getTemp_c());

            Log.d("huehue","Weather text: " + resultCurrentList.getCurrent().getCondition().getText());

            currentTimeZone = resultCurrentList.getLocation().getTz_id();

            setDate(currentTimeZone);

            if(Integer.parseInt(currentHourString)>18 || Integer.parseInt(currentHourString)<7){
                weatherView.setBackground(AppCompatResources.getDrawable(MainActivity.this,R.drawable.gradient_list_night));

                AnimationDrawable animationDrawable = (AnimationDrawable) weatherView.getBackground();
                animationDrawable.setEnterFadeDuration(2500);
                animationDrawable.setExitFadeDuration(5000);
                animationDrawable.start();
            }

            if(Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Sunny")){

                Log.d("huehue","inside sunny");

                weatherView.setBackground(AppCompatResources.getDrawable(MainActivity.this,R.drawable.gradient_list));

                AnimationDrawable animationDrawable = (AnimationDrawable) weatherView.getBackground();
                animationDrawable.setEnterFadeDuration(2500);
                animationDrawable.setExitFadeDuration(5000);
                animationDrawable.start();
                weatherView.setWeatherData(PrecipType.CLEAR);

            }
            else if(Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Partly cloudy") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Cloudy")){

                weatherView.setBackground(AppCompatResources.getDrawable(MainActivity.this,R.drawable.gradient_list_cloudy));

                AnimationDrawable animationDrawable = (AnimationDrawable) weatherView.getBackground();
                animationDrawable.setEnterFadeDuration(2500);
                animationDrawable.setExitFadeDuration(5000);
                animationDrawable.start();
                weatherView.setWeatherData(PrecipType.CLEAR);

            }
           else if(Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Mist") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Freezing fog") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Patchy freezing drizzle possible") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Fog") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Overcast")){

                weatherView.setBackground(AppCompatResources.getDrawable(MainActivity.this,R.drawable.gradient_list_mistandfog));

                AnimationDrawable animationDrawable = (AnimationDrawable) weatherView.getBackground();
                animationDrawable.setEnterFadeDuration(2500);
                animationDrawable.setExitFadeDuration(5000);
                animationDrawable.start();
                weatherView.setWeatherData(PrecipType.CLEAR);

            }
            else if(Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Heavy snow") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Blowing snow") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Patchy heavy snow") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Snow") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Moderate or heavy snow with thunder") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Light snow") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Patchy moderate snow") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Patchy heavy snow")){

                weatherView.setBackground(AppCompatResources.getDrawable(MainActivity.this,R.drawable.gradient_list_mistandfog));

                AnimationDrawable animationDrawable = (AnimationDrawable) weatherView.getBackground();
                animationDrawable.setEnterFadeDuration(2500);
                animationDrawable.setExitFadeDuration(5000);
                animationDrawable.start();

                weatherView.setWeatherData(PrecipType.SNOW);

            }
           else if(Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Patchy light drizzle") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Light drizzle") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Freezing drizzle") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Heavy freezing drizzle") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Patchy light rain") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Light rain") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Moderate rain at times") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Moderate rain") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Moderate rain at times") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Heavy rain at times") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Heavy rain") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Light freezing rain") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Moderate or heavy freezing rain") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Light sleet") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Light rain shower") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Moderate or heavy rain shower") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Torrential rain shower") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Light sleet shower") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Patchy light rain with thunder") || Objects.equals(resultCurrentList.getCurrent().getCondition().getText(), "Moderate or heavy rain with thunder")){

                weatherView.setBackground(AppCompatResources.getDrawable(MainActivity.this,R.drawable.gradient_list_cloudy));

                AnimationDrawable animationDrawable = (AnimationDrawable) weatherView.getBackground();
                animationDrawable.setEnterFadeDuration(2500);
                animationDrawable.setExitFadeDuration(5000);
                animationDrawable.start();

                weatherView.setWeatherData(PrecipType.RAIN);

            }
           else{
                weatherView.setWeatherData(PrecipType.CLEAR);
//                Log.d("huehue","this working and currenHourString: " + currentHourString);

                if(Integer.parseInt(currentHourString)>18){
                    weatherView.setBackground(AppCompatResources.getDrawable(MainActivity.this,R.drawable.gradient_list_night));

                    AnimationDrawable animationDrawable = (AnimationDrawable) weatherView.getBackground();
                    animationDrawable.setEnterFadeDuration(2500);
                    animationDrawable.setExitFadeDuration(5000);
                    animationDrawable.start();
                }
                else {

                    weatherView.setBackground(AppCompatResources.getDrawable(MainActivity.this, R.drawable.gradient_list));
                    AnimationDrawable animationDrawable = (AnimationDrawable) weatherView.getBackground();
                    animationDrawable.setEnterFadeDuration(2500);
                    animationDrawable.setExitFadeDuration(5000);
                    animationDrawable.start();

                }

            }


            String image_weather = resultCurrentList.getCurrent().getCondition().getIcon();

            Picasso.get().load("http:" + image_weather).into(img_weather);

        }

        @Override
        public void onError(String message) {

        }
    };


    private void setDate(String tz) {

        currentDate = findViewById(R.id.currentDate);

        TimeZone timeZone = TimeZone.getTimeZone(tz);

        Calendar calendar = Calendar.getInstance(timeZone);

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault());
        dateFormat.setTimeZone(timeZone);

        String currentDateString = dateFormat.format(calendar.getTime());

        Date currentTime = calendar.getTime();


        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        timeFormat.setTimeZone(timeZone);
        String currentTimeString = timeFormat.format(currentTime);

        SimpleDateFormat hourFormat = new SimpleDateFormat("HH", Locale.getDefault());
        hourFormat.setTimeZone(timeZone);
        currentHourString = hourFormat.format(currentTime);


        currentDate.setText(currentDateString + " " + currentTimeString);
    }









}