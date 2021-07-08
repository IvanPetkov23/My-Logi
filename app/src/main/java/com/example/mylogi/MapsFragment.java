package com.example.mylogi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylogi.data.entities.LocationsEntity;
import com.example.mylogi.viewmodel.LocationsViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapsFragment extends Fragment {
    FusedLocationProviderClient fusedLocationProviderClient = null;
    LocationsViewModel locationsViewModel;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */


        @Override
        public void onMapReady(GoogleMap googleMap) {
           /* LatLng sydney = new LatLng(-34, 151);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/


            /*Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @SuppressLint("MissingPermission")
                @Override
                public void run() {

                    fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
                        LatLng newPoint = new LatLng(location.getLatitude(), location.getLongitude());
                        googleMap.addMarker(new MarkerOptions().position(newPoint).title("Speed: "
                                + location.getSpeed()));
                        //googleMap.setMinZoomPreference(15.0f);
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newPoint, 20.0f));
                        handler.postDelayed(this, 1000);
                    });
                }
            };
            handler.postDelayed(runnable, 1000);*/
            /*List<LatLng> listOfPoints = new ArrayList<>();
            locationsViewModel = ViewModelProviders.of(requireActivity())
                    .get(LocationsViewModel.class);

           locationsViewModel.getAllLocations().observe(getViewLifecycleOwner(), locationsEntities -> {


               for (LocationsEntity locationsEntity : locationsEntities) {
                   LatLng point = new LatLng(Double.parseDouble(locationsEntity.getLocationLatitude()),
                           Double.parseDouble(locationsEntity.getLocationLongitude()));
                   listOfPoints.add(point);
                   Log.e("Coordinates: ", Double.parseDouble(locationsEntity.getLocationLatitude()) + " " +
                           Double.parseDouble(locationsEntity.getLocationLongitude()));
               }
               Polyline historyLine  = googleMap.addPolyline(new PolylineOptions().clickable(true)
                       .addAll(listOfPoints));
               historyLine.setVisible(true);
           });



            System.out.println(listOfPoints);*/
        }


    };

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}