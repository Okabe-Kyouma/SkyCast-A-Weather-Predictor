package com.example.skycast;

import static com.example.skycast.WeatherNotification.CHANNEL_ID;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.skycast.Model.WeatherModel.ApiResultCurrent;
import com.example.skycast.WeatherCurrent.OnFetchCurrentWeatherListener;
import com.example.skycast.WeatherCurrent.RequestManagerWeathercurrent;

import java.util.Arrays;
import java.util.List;

public class WeatherUpdateService extends Service {

    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "channel_id";


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String input = intent.getStringExtra("inputExtra");

        Intent noficcationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0,noficcationIntent,PendingIntent.FLAG_IMMUTABLE);

        Notification builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.location_icon)
                .setContentIntent(pendingIntent)
                .build();


        startForeground(1,builder);

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


    protected void onHandleIntent(Intent intent) {
        if (intent != null) {

            String userLocation = MainActivity.latitude + "," + MainActivity.longitude;

            if (userLocation != null) {

                String cityName = "Agra,Uttar Pradesh,India";


                RequestManagerWeathercurrent weatherManager = new RequestManagerWeathercurrent(this);
                weatherManager.fetchCurrentWeatherDetails(new OnFetchCurrentWeatherListener() {
                    @Override
                    public void onFetchData(ApiResultCurrent data, String message) {

                        Log.d("huehue","weather service data is fetched successfully");

                        List<String> suggestedPlaces = suggestPlaces(userLocation, data);


                        sendNotification(userLocation, data, suggestedPlaces);
                    }

                    @Override
                    public void onCityNotFound(String location) {
                        Log.d("WeatherUpdateService", "City not found: " + location);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Log.d("WeatherUpdateService", "Error fetching weather data: " + errorMessage);
                    }
                }, cityName);
            }
        }
    }

    private List<String> suggestPlaces(String userLocation, ApiResultCurrent weatherData) {
       //Couldn't find the api for this
        return Arrays.asList("Taj Mahal");
    }

    private void sendNotification(String userLocation, ApiResultCurrent weatherData, List<String> suggestedPlaces) {

        Log.d("huehue","weather service sending notifications");


        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.location_icon) // Replace with your notification icon
                .setContentTitle("Weather Update")
                .setContentText("Current weather in " + userLocation + ": " + weatherData.getCurrent().getTemp_c())
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Suggested Places: " + TextUtils.join(", ", suggestedPlaces)))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);


        createNotificationChannel(notificationManager);


        startForeground(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel(NotificationManagerCompat notificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Weather Update Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }
    }
}
