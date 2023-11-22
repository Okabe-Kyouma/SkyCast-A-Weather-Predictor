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
import android.view.View;
import android.widget.Button;
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


        TextView appNameTextView = findViewById(R.id.appNameTextView);


        ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(appNameTextView, "alpha", 0f, 1f);
        fadeInAnimator.setDuration(1000);

        fadeInAnimator.start();

        ImageView sunImageView = findViewById(R.id.sunImageView);

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(sunImageView, "scaleX", 0.5f, 1f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(sunImageView, "scaleY", 0.5f, 1f);
        ObjectAnimator fadeInAnimator2 = ObjectAnimator.ofFloat(sunImageView, "alpha", 0f, 1f);


        scaleXAnimator.setDuration(1000);
        scaleYAnimator.setDuration(1000);
        fadeInAnimator2.setDuration(1000);


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
                            finish();

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

                AlertDialog.Builder builder = new AlertDialog.Builder(Splash_Screen.this);
                View view = getLayoutInflater().inflate(R.layout.custom_loading_dialog, null);
                builder.setView(view);

                TextView titleTextView = view.findViewById(R.id.dialog_title);
                TextView messageTextView = view.findViewById(R.id.dialog_message);

                titleTextView.setText("Location Permission Required");
                messageTextView.setText("You have not granted us location permission. By default, we will show New Delhi location. Is that alright?");

                Button btnYes = view.findViewById(R.id.btnYes);
                Button btnNo = view.findViewById(R.id.btnNo);

                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Splash_Screen.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        askPermission();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();


            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }




}