package com.example.skycast;

import static com.example.skycast.MainActivity.context;
import static com.example.skycast.MainActivity.itWorkedOnFirstTime;
import static com.example.skycast.MainActivity.latitude;
import static com.example.skycast.MainActivity.longitude;
import static com.example.skycast.MainActivity.selectedCityName;
import static com.example.skycast.WeatherNotification.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.skycast.CitySearch.RequestManager;
import com.example.skycast.Model.WeatherModel.ApiResultCurrent;
import com.example.skycast.WeatherCurrent.OnFetchCurrentWeatherListener;
import com.example.skycast.WeatherCurrent.RequestManagerWeathercurrent;

public class ExampleService extends Service {

    RequestManagerWeathercurrent weatherManager;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

         String input = intent.getStringExtra("inputExtra");

         if(input==null){
             startService();
         }
         else {

             Log.d("huehue", "Received Content: " + input);

             Intent noficcationIntent = new Intent(this, MainActivity.class);
             PendingIntent pendingIntent = PendingIntent.getActivity(this,
                     0, noficcationIntent, PendingIntent.FLAG_IMMUTABLE);

             Notification builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                     .setContentTitle("Weather Forecast")
                     .setContentText(input)
                     .setSmallIcon(R.drawable.notificon)
                     .setContentIntent(pendingIntent)
                     .build();


             startForeground(1, builder);
//             startService();

         }

        return START_NOT_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void startService(){
        Intent serviceIntent = new Intent(this,ExampleService.class);

        String cityName = "";

//        if(!itWorkedOnFirstTime)
//        cityName = userLocation;
//        else
            cityName = selectedCityName;

        Log.d("huehue",cityName + "that i got from Mainactivity to ExampleService");


        weatherManager = new RequestManagerWeathercurrent(this);
        weatherManager.fetchCurrentWeatherDetails(

                new OnFetchCurrentWeatherListener() {
            @Override
            public void onFetchData(ApiResultCurrent data, String message) {

                Log.d("huehue","weather service data is fetched successfully");

                String condition = data.getCurrent().getCondition().getText();

                String cityNameCurrent = data.getLocation().getName();

                Log.d("huehue","Example service class the cityNameCurrent: "  + cityNameCurrent);

                int temperature = (int) data.getCurrent().getTemp_c();


                String verdict = "";

                if (temperature < 0) {
                    verdict = "Be Careful! It's freezing cold Outside";
                } else if (temperature <= 5) {
                    verdict = "Yeaa We know! It's very cold Outside Please take care";
                } else if (temperature <= 10) {
                    verdict = "It's really cold Stay inside";
                } else if (temperature <= 15) {
                    verdict = "Such a wow temperature just from indoors tho";
                } else if (temperature <= 20) {
                    verdict = "Well It's mild but still take care";
                } else if (temperature <= 25) {
                    verdict = "The coolest temperature level in our opinion";
                } else if (temperature <= 30) {
                    verdict = "Hmmmm good one for playing outside";
                } else if (temperature <= 35) {
                    verdict = "Damn It's hot isn't it?";
                } else if (temperature <= 40) {
                    verdict = "Oof Please Drink water regularly  It's extremely hot";
                } else if (temperature <= 45) {
                    verdict = "Stay indoors as much as you can It's scorching";
                } else if (temperature <= 50) {
                    verdict = "Please Take care Stay indoors It's blazing hot Outside";
                } else if (temperature <= 55) {
                    verdict = "Warning Don't Go outside It's sweltering";
                } else if (temperature <= 60) {
                    verdict = "public Warning! The temperature levels are beyond of us now";
                } else {
                    verdict = "Temperature out of range";
                }





                serviceIntent.putExtra("inputExtra", "" + cityNameCurrent + " / " + condition + " / "  + data.getCurrent().getTemp_c()  + "\u00B0" + " / " + "\n"  + verdict);

                startService(serviceIntent);






            }

            @Override
            public void onCityNotFound(String location) {
                Log.d("huehue", " CITY NOT FOUND IN EXAMPLESERVICE TERMINATION SEQUENCE: " + location);
                 weatherManager = new RequestManagerWeathercurrent(ExampleService.this);
//                 weatherManager.fetchCurrentWeatherDetails(onFetchCurrentWeatherListener,latitude + "," + longitude);

            }

            @Override
            public void onError(String errorMessage) {

                try {

                    Log.d("WeatherUpdateService", "Error fetching weather data: " + errorMessage);
                    Toast.makeText(context, "Please Check Your Internet Connection!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Log.d("huehue","exception raise: " + e.toString());
                }
            }
        }, cityName);
    }


}
