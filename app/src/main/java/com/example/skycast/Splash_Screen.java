package com.example.skycast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;

public class Splash_Screen extends AppCompatActivity {

    private final static int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

                Log.d("huehue","handler");

                if(ContextCompat.checkSelfPermission(Splash_Screen.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    Log.d("huehue","permission is granted starting main activity");

                    Intent intent = new Intent(Splash_Screen.this, MainActivity.class);
                    startActivity(intent);


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