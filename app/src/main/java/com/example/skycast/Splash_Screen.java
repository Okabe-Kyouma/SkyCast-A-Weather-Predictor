package com.example.skycast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class Splash_Screen extends AppCompatActivity {

    private final static int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // Inside your Splash_Screen activity or fragment
        TextView appNameTextView = findViewById(R.id.appNameTextView);

// Create an ObjectAnimator for alpha property
        ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(appNameTextView, "alpha", 0f, 1f);
        fadeInAnimator.setDuration(1000); // Set the duration in milliseconds

// Start the animation
        fadeInAnimator.start();

        // Inside your Splash_Screen activity or fragment
        ImageView sunImageView = findViewById(R.id.sunImageView);

// Create an ObjectAnimator for scaling (X and Y) and alpha properties
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(sunImageView, "scaleX", 0.5f, 1f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(sunImageView, "scaleY", 0.5f, 1f);
        ObjectAnimator fadeInAnimator2 = ObjectAnimator.ofFloat(sunImageView, "alpha", 0f, 1f);

// Set the duration for each animator
        scaleXAnimator.setDuration(1000);
        scaleYAnimator.setDuration(1000);
        fadeInAnimator2.setDuration(1000);

// Start the animations together
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator, fadeInAnimator2);
        animatorSet.start();




        Log.d("huehue","handler");

                if(ContextCompat.checkSelfPermission(Splash_Screen.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    Log.d("huehue","permission is granted starting main activity");

                     new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent intent = new Intent(Splash_Screen.this, MainActivity.class);
                            startActivity(intent);

                        }
                    },2000);


                }
                else {
                    Log.d("huehue","asking per");
                     askPermission();

                }


    }

    private void askPermission(){
        ActivityCompat.requestPermissions(Splash_Screen.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        Log.d("huehue","GrantResults: " + Arrays.toString(grantResults) + "khikhi" + "and requestCode: " + requestCode);

        if(requestCode==REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
               Intent intent = new Intent(Splash_Screen.this, MainActivity.class);
                    startActivity(intent);
            }
            else{

                Log.d("huehue","Showing alert Dialog");

                AlertDialog.Builder builder = new AlertDialog.Builder(Splash_Screen.this);
                builder.setTitle("You have not given us location permission!! So We will By Default show New-Delhi Location is that alright?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Splash_Screen.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No Give Location Access", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        askPermission();
                    }
                });
                builder.show();

            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }




}