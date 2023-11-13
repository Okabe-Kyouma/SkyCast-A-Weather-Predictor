package com.example.skycast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skycast.CitySearch.OnFetchDataListener;
import com.example.skycast.CitySearch.RequestManager;
import com.example.skycast.Model.ApiResult;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView city;
    FusedLocationProviderClient fusedLocationProviderClient;

    SearchView searchView;


    private final static int REQUEST_CODE = 100;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        city = findViewById(R.id.City);
        searchView  = findViewById(R.id.search);

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
                 return false;
             }
         });

        RequestManager requestManager = new RequestManager(this);

         searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String query) {

                 requestManager.fetchCityNames(listener,query);

                 return false;
             }

             @Override
             public boolean onQueryTextChange(String newText) {
                 return false;
             }
         });




    }

    private void getLastLocation(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onSuccess(Location location) {
                    Geocoder geocoder = null;
                    if(location!=null)
                         geocoder = new Geocoder(MainActivity.this, Locale.getDefault());

                    List<Address> addressList = null;
                    try {
                        addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
//                        lat.setText("Latitude: " + addressList.get(0).getLatitude());
//                        longitude.setText("Longitude: " + addressList.get(0).getLongitude());
//                        add.setText("Address: " + addressList.get(0).getAddressLine(0));
                        city.setText(addressList.get(0).getLocality());
//                        country.setText("Country: " + addressList.get(0).getCountryName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
        else{

            city.setText("New-Delhi");
//            askPermission();

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

                    Log.d("huehue", String.valueOf(str.address.name));

                    if(str.type=="city")
                        Log.d("huehue",str.address.name);

                }

            }

    }

        @Override
        public void onError(String message) {

            Toast.makeText(MainActivity.this, "Error-onError", Toast.LENGTH_SHORT).show();
        }

        };


    }