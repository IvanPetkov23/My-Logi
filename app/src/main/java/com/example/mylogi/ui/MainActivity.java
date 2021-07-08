package com.example.mylogi.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylogi.R;
import com.example.mylogi.adapters.DriverListAdapter;
import com.example.mylogi.data.AppDatabase;
import com.example.mylogi.data.PopulateDatabase;
import com.example.mylogi.data.dao.LocationsDAO;
import com.example.mylogi.data.entities.DriverEntity;
import com.example.mylogi.data.entities.LocationsEntity;
import com.example.mylogi.data.relations.DriverAndTruck;
import com.example.mylogi.viewmodel.DriverViewModel;
import com.example.mylogi.viewmodel.LocationsViewModel;
import com.example.mylogi.viewmodel.TruckViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private PopulateDatabase populateDatabaseInstance = new PopulateDatabase();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Populate database if empty
        populateDatabaseInstance.populateDatabase(this, getApplication());

        EditText username = findViewById(R.id.editText1);
        EditText password = findViewById(R.id.editTextTextPassword2);
        Button loginButton = findViewById(R.id.button2);

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (username.getText().toString().trim().length() > 0 && password.getText().toString().trim().length() > 0) {
                    loginButton.setBackgroundResource(R.drawable.login_button_shape2);
                } else {
                    loginButton.setBackgroundResource(R.drawable.login_button_shape1);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (username.getText().toString().trim().length() > 0 && password.getText().toString().trim().length() > 0) {
                    loginButton.setBackgroundResource(R.drawable.login_button_shape2);
                } else {
                    loginButton.setBackgroundResource(R.drawable.login_button_shape1);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        DriverViewModel driverViewModel = ViewModelProviders.of(this)
                .get(DriverViewModel.class);

        loginButton.setOnClickListener(view -> driverViewModel.getLoggedByUserAndPass(username.getText().toString(),
                password.getText().toString()).observe(this, driverEntity -> {
            if (driverEntity != null) {
                //Toast.makeText(getApplicationContext(), "Driver id: " + driverEntity.getDriverId(),
                //      Toast.LENGTH_SHORT).show();

                saveLoggedUser(driverEntity);

                Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Fail",
                        Toast.LENGTH_LONG).show();
            }
        }));
    }

    private void saveLoggedUser(DriverEntity driverEntity) {
        SharedPreferences sharedPreferences = getSharedPreferences("loggedUser",
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("UserId", driverEntity.getDriverId());
        editor.apply();
    }
}