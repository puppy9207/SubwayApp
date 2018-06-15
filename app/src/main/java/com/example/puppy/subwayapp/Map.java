package com.example.puppy.subwayapp;


import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.puppy.subwayapp.R.id.map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Map extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback {


    private boolean mPermissionDenied = false;
    GoogleMap gMap;
    MapFragment mapFragment;
    Double latitude = 37.344157;
    Double longitude = 126.736713;
    MainActivity activity = (MainActivity)getActivity();


    double subLat[] = {37.395788, 37.379736, 37.414436, 37.401243, 37.446556, 37.455646, 37.494093
            , 37.488071, 37.505184, 37.502671, 37.488825};
    double subLong[] = {126.652274, 126.661714, 126.676867, 126.723928, 126.702535, 126.719913, 126.723218,
            126.752551, 126.752424, 126.774348, 126.779543
    };
    LocationManager manager1;

    public Map() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        startLocationService();

        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.344157, 126.736713), 15));
        gMap.getUiSettings().setZoomControlsEnabled(true);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        gMap.animateCamera(zoom); //근거리 부드럽게 움직이기
        for (int idx = 0; idx < subLat.length; idx++) {
            // 1. 마커 옵션 설정 (만드는 과정)
            MarkerOptions makerOptions = new MarkerOptions();
            makerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                    .position(new LatLng(subLat[idx], subLong[idx]))
                    .title("마커" + idx); // 타이틀.

            // 2. 마커 생성 (마커를 나타냄)
            gMap.addMarker(makerOptions);
        }

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    private void startLocationService() {

        manager1 = (LocationManager)getActivity().getSystemService(getActivity().LOCATION_SERVICE);

        long minTime = 1000;
        float minDistance = 1;


        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(),"위치정보를 받아올수 없습니다.",Toast.LENGTH_LONG).show();

            return;
        }
        manager1.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, mLocationListener);
        manager1.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,minTime,minDistance,mLocationListener);
    }
    private void stopLocationService(){
        MainActivity activity = (MainActivity)getActivity();
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(),"위치정보를 받아올수 없습니다.",Toast.LENGTH_LONG).show();

            return;
        }
        manager1.removeUpdates(mLocationListener);

    }
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            stopLocationService();

        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

}
