package com.example.mylogi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mylogi.services.HttpRequest;
import com.example.mylogi.services.LocationService;
import com.example.mylogi.viewmodel.DriverViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class HomePageFragment extends Fragment {

    private LocationCallback locationCallback;
    private FusedLocationProviderClient fusedLocationProviderClient;

    public HomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView temperatureTextView = view.findViewById(R.id.temperatureTextView);
        TextView greetingsTextView = view.findViewById(R.id.greetingsTextView);
        TextView cityTextView = view.findViewById(R.id.cityTextView);
        CardView driverListCardView = view.findViewById(R.id.driverListCardView);
        ImageView weatherIcon = view.findViewById(R.id.weatherIcon);
        CardView truckListCardView = view.findViewById(R.id.truckListCardView);

        //Location updates
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
        LocationRequest locationRequest = createLocationRequest();
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                for (Location location : locationResult.getLocations()) {
                    Log.e("Location latitude", String.valueOf(location.getLatitude()));
                    Log.e("Location longitude", String.valueOf(location.getLongitude()));
                    HttpRequest instance = new HttpRequest();
                    instance.weatherRequest(requireContext(), location, cityTextView, temperatureTextView,
                            weatherIcon);
                }
            }
        };
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback,
                    Looper.getMainLooper());
        }


        DriverViewModel driverViewModel = ViewModelProviders.of(this)
                .get(DriverViewModel.class);

        SharedPreferences sharedPreferences = this.requireActivity()
                .getSharedPreferences("loggedUser", Context.MODE_PRIVATE);
        Long userID = sharedPreferences.getLong("UserId", 0);


        driverViewModel.getUserById(userID).observe(this.getViewLifecycleOwner(), driverEntity -> {
            greetingsTextView.setText("Have a nice day, ".concat(driverEntity.getDriverFirstName()));
        });


        driverListCardView.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.driverListFragment);
        });

        truckListCardView.setOnClickListener(view12 -> {
            Navigation.findNavController(view).navigate(R.id.truckListFragment);
        });

    }

    private LocationRequest createLocationRequest() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(60000);
        locationRequest.setFastestInterval(40000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

}