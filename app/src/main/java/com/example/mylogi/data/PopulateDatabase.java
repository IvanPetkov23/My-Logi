package com.example.mylogi.data;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mylogi.data.dao.DriversDao;
import com.example.mylogi.data.dao.LocationsDAO;
import com.example.mylogi.data.dao.TrailersDao;
import com.example.mylogi.data.dao.TrucksDao;
import com.example.mylogi.data.entities.DriverEntity;
import com.example.mylogi.data.entities.TrailerEntity;
import com.example.mylogi.data.entities.TruckEntity;
import com.example.mylogi.data.relations.DriverAndTruck;
import com.example.mylogi.data.relations.TruckAndTrailer;
import com.example.mylogi.data.repositories.DriverRepository;
import com.example.mylogi.data.repositories.TrailerRepository;
import com.example.mylogi.data.repositories.TrucksRepository;
import com.example.mylogi.viewmodel.DriverViewModel;
import com.example.mylogi.viewmodel.TrailerViewModel;

import java.util.ArrayList;
import java.util.List;

public class PopulateDatabase {

    public void populateDatabase(LifecycleOwner owner, Application application) {

        DriverRepository driverRepository = new DriverRepository(application);
        TrucksRepository trucksRepository = new TrucksRepository(application);
        TrailerRepository trailerRepository = new TrailerRepository(application);

        trailerRepository.getAllTrailers().observe(owner, trailerEntities -> {
            if (trailerEntities.isEmpty()) {
                TrailerEntity trailerEntity1 = new TrailerEntity(1L, "Doepker");
                trailerRepository.insert(trailerEntity1);
                TrailerEntity trailerEntity2 = new TrailerEntity(2L, "Felling");
                trailerRepository.insert(trailerEntity2);
                TrailerEntity trailerEntity3 = new TrailerEntity(3L, "Fruehauf");
                trailerRepository.insert(trailerEntity3);
                TrailerEntity trailerEntity4 = new TrailerEntity(4L, "Heil");
                trailerRepository.insert(trailerEntity4);
            }
        });

        trucksRepository.getAllTrucks().observe(owner, truckAndTrailers -> {
            if (truckAndTrailers.isEmpty()) {
                TruckEntity truckEntity1 = new TruckEntity(1L, "MAN TGX 480", "CA4848XT", 1L);
                trucksRepository.insert(truckEntity1);
                TruckEntity truckEntity2 = new TruckEntity(2L, "Mercedes-Benz Actros 6", "CA7523XT", 2L);
                trucksRepository.insert(truckEntity2);
                TruckEntity truckEntity3 = new TruckEntity(3L, "Volvo FH16", "CA7412XT", 3L);
                trucksRepository.insert(truckEntity3);
                TruckEntity truckEntity4 = new TruckEntity(4L, "Iveco S-Way", "CA8437", 4L);
                trucksRepository.insert(truckEntity4);
            }
        });

        driverRepository.getAllDrivers().observe(owner, driverAndTrucks -> {
            if (driverAndTrucks.isEmpty()) {
                DriverEntity driverEntity1 = new DriverEntity(1L, "Ivan", "Ivailov", "Ivanov", "7809198258",
                        "Varna", "ul. Roza 21", "0896512874", " ivan123@abv.bg", "ivan123", " ivan123",
                        "/storage/13FC-0310/MyLogiImages/DriversPhotos/driverid=1.jpg", 1L);
                driverRepository.insert(driverEntity1);
                DriverEntity driverEntity2 = new DriverEntity(2L, "Petar", "Stoyanov", "Stefanov", "8510247539", "Sliven", "ul. Rakovska 34", "0885412536",
                        "petar123@abv.bg", "petar123", "petar123", "/storage/13FC-0310/MyLogiImages/DriversPhotos/driverid=2.jpg", 2L);
                driverRepository.insert(driverEntity2);
                DriverEntity driverEntity3 = new DriverEntity(3L, "Stoyan", "Stefanov", "Petrov", "6705098752", "Vratsa", "ul. Bulgaria 123",
                        "0879643152", "stoyan123@abv.bg", "stoyan123", "stoyan123", "/storage/13FC-0310/MyLogiImages/DriversPhotos/driverid=3.jpg", 3L);
                driverRepository.insert(driverEntity3);
                DriverEntity driverEntity4 = new DriverEntity(4L, "Vasil", "Hristov", "Hristov", "6907188523", "Stara Zagora", "ul. Nova 12",
                        "0894753698", "vasil123@abv.bg", "vasil123", "vasil123", "/storage/13FC-0310/MyLogiImages/DriversPhotos/driverid=4.jpg", 4L);
                driverRepository.insert(driverEntity4);
            }
        });
    }
}
