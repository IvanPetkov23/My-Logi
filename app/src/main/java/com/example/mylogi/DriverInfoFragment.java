package com.example.mylogi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylogi.data.entities.LocationsEntity;
import com.example.mylogi.viewmodel.DriverViewModel;
import com.example.mylogi.viewmodel.LocationsViewModel;
import com.example.mylogi.workers.BirthDateConverter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class DriverInfoFragment extends Fragment {

    DriverViewModel driverViewModel;
    LocationsViewModel locationsViewModel;
    FusedLocationProviderClient fusedLocationProviderClient = null;

    public DriverInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                DriverListFragment fragment = new DriverListFragment();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragmentContainerView2, fragment);
                transaction.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_driver_info, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        long driverId = 0L;

        ImageView profilePhoto = view.findViewById(R.id.profilePhoto);
        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView birthDateTextView = view.findViewById(R.id.birthDateTextView);
        TextView pidTextView = view.findViewById(R.id.pidTextView);
        TextView addressTextView = view.findViewById(R.id.addressTextView);
        TextView phoneNumberTextView = view.findViewById(R.id.phoneNumberTextView);
        TextView emailTextView = view.findViewById(R.id.emailTextView);
        Button trackingButton = view.findViewById(R.id.trackingButton);


        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DriverListFragment fragment = new DriverListFragment();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragmentContainerView2, fragment);
                transaction.commit();
            }
        });

        trackingButton.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.mapsFragment2);
        });

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            driverId = bundle.getLong("DriverId");
            System.out.println();
        }
        driverViewModel = ViewModelProviders.of(this)
                .get(DriverViewModel.class);
        locationsViewModel = ViewModelProviders.of(this)
                .get(LocationsViewModel.class);

        driverViewModel.getUserById(driverId).observe(requireActivity(), driverEntity -> {
            nameTextView.setText(driverEntity.getDriverFirstName() + " " + driverEntity.getDriverMiddleName()
                    + " " + driverEntity.getDriverLastName());
            birthDateTextView.setText(new BirthDateConverter().getBirthDate(driverEntity.getDriverPID()));
            addressTextView.setText(driverEntity.getDriverAddress() + ", " + driverEntity.getDriverCity());
            emailTextView.setText(driverEntity.getDriverEmail());
            phoneNumberTextView.setText(driverEntity.getDriverNumber());
            pidTextView.setText(driverEntity.getDriverPID());


            /*Picasso.get().load("https://img.freepik.com/free-photo/handsome-young-businessman-shirt-eyeglasses_85574-6228.jpg?size=626&ext=jpg")
                    .resize(300, 300).into(profilePhoto);*/
            profilePhoto.setImageDrawable(Drawable.createFromPath(driverEntity.getImagePath()));
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
            //Location thread
            /*Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }

                    fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            locationsViewModel.insert(new LocationsEntity(String.valueOf(location.getLatitude()),
                                    String.valueOf(location.getLongitude()), driverEntity.getDriverId(), Calendar.getInstance(TimeZone.getTimeZone("UTC+3")).getTime()));
                        }
                    });

                    handler.postDelayed(this, 60000);

                }
            };
            handler.postDelayed(runnable, 1000);*/

            locationsViewModel.getAllLocations().observe(getViewLifecycleOwner(), locationsEntities -> {
                //Log.e("Date and time:", locationsEntities.get(locationsEntities.size() - 1).getLocationDateTime().toString());
            });


        });

    }

}