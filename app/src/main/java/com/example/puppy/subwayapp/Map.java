package com.example.puppy.subwayapp;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Arrays;

public class Map extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap googleMap;
    LocationManager locationManager;
    RelativeLayout boxMap;
    Double latitude = 0.0;
    Double longitude = 0.0;
    double subLat[] = {37.395788, 37.414436, 37.401243, 37.446556, 37.455646, 37.494093
            , 37.488071, 37.505184, 37.502671, 37.488825, 37.344113, 37.309989, 37.309021,
            37.484351,37.480131,37.540966, 37.518518, 37.481785, 37.553837, 37.553457,
            37.576325, 37.402084, 37.382625,37.394534
    };
    double subLong[] = {126.652274, 126.676867, 126.723928, 126.702535, 126.719913, 126.723218,
            126.752551, 126.752424, 126.774348, 126.779543, 126.736697, 126.829052, 126.851824
            ,126.899121, 126.880974,126.840624, 126.905358, 126.996989, 126.923625, 126.973385,
            126.971482, 126.922413, 126.959863, 126.962866
    };
    String subwayName[] = {"송도점","연수점","인천논현역점","구월동로데오점","모래내시장역점","부평중앙점","부천송내역점",
    "부천상동점","부천중동점","부천역점","정왕점","안산고잔점","한대앞역점","구로디지털점"
    ,"구로디지털단지점","화곡역점","영등포점","방배역점","홍대점","서울역동자점",
    "경복궁점","안양점","평촌학원가점","안양평촌점"};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_map,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.menuMap1:
                double straight[] = new double[subLat.length];
                int shortValue;
                for(int i=0; i<subLat.length; i++){
                    straight[i] = Math.sqrt(Math.pow((latitude-subLat[i]),2)+Math.pow((longitude-subLong[i]),2));
                }
                shortValue = (int)min(straight);
                LatLng shortCut = new LatLng(subLat[shortValue], subLong[shortValue]);
                this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shortCut, 13));
                break;
        }
        return true;
    }

    public static double min(double n[]) {
        double min = n[0];
        int a = 0;

        for (int i = 1; i < n.length; i++) {
            if (n[i] < min) {
                min = n[i];
                a = i;
            }

        }
        return a;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        boxMap = (RelativeLayout) findViewById(R.id.boxMap);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.sublogo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //LocationManager
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        //GPS가 켜져있는지 체크
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //GPS 설정화면으로 이동
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            startActivity(intent);
            finish();
        }

        //마시멜로 이상이면 권한 요청하기
        if (Build.VERSION.SDK_INT >= 23) {
            //권한이 없는 경우
            if (ContextCompat.checkSelfPermission(Map.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(Map.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Map.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            //권한이 있는 경우
            else {
                requestMyLocation();
            }
        }
        //마시멜로 아래
        else {
            requestMyLocation();
        }



    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        LatLng position = new LatLng(latitude, longitude);
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));
        boxMap.setVisibility(View.VISIBLE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        //현재 위치 버튼 눌렀을때 위도 경도 정보도 다시 얻어옴.
        this.googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                requestMyLocation();
                return false;
            }
        });
        for (int idx = 0; idx < subLat.length; idx++) {
            MarkerOptions makerOptions = new MarkerOptions();
            makerOptions
                    .position(new LatLng(subLat[idx], subLong[idx]))
                    .title(subwayName[idx]);
            googleMap.addMarker(makerOptions);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            //권한받음
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                requestMyLocation();
            }
            //권한못받음
            else{
                Toast.makeText(this, "권한없음", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    //나의 위치 요청
    public void requestMyLocation(){
        if(ContextCompat.checkSelfPermission(Map.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(Map.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        //요청
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 10, locationListener);
    }

    //위치정보 구하기 리스너
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if(ContextCompat.checkSelfPermission(Map.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(Map.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                return;
            }
            //나의 위치를 한번만 가져오기 위해
            locationManager.removeUpdates(locationListener);

            //위도 경도
            latitude = location.getLatitude();   //위도
            longitude = location.getLongitude(); //경도

            //맵생성
            SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
            //콜백클래스 설정
            mapFragment.getMapAsync(Map.this);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) { Log.d("gps", "onStatusChanged"); }

        @Override
        public void onProviderEnabled(String provider) { }

        @Override
        public void onProviderDisabled(String provider) { }
    };



}
