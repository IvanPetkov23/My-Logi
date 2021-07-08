package com.example.mylogi;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.mylogi.adapters.DriverListAdapter;
import com.example.mylogi.data.entities.DriverEntity;
import com.example.mylogi.data.relations.DriverAndTruck;
import com.example.mylogi.ui.DriverListActivity;
import com.example.mylogi.viewmodel.DriverViewModel;
import com.example.mylogi.viewmodel.LocationsViewModel;

import java.util.List;
import java.util.Objects;


public class DriverListFragment extends Fragment implements DriverListAdapter.OnItemListener {
    RecyclerView recyclerView;
    SearchView searchView;
    DriverViewModel driverViewModel;

    public DriverListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                HomePageFragment fragment = new HomePageFragment();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragmentContainerView2, fragment);
                transaction.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_driver_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        searchView = view.findViewById(R.id.driversSearchView);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        DriverListAdapter adapter = new DriverListAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);


        driverViewModel = ViewModelProviders.of(this)
                .get(DriverViewModel.class);

        driverViewModel.getAllDrivers().observe(getViewLifecycleOwner(), adapter::setDrivers);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                driverViewModel.getFilteredDriversList(s)
                        .observe(getViewLifecycleOwner(), adapter::setDrivers);
                return true;
            }
        });
    }

    @Override
    public void onItemClick(int position) {

        DriverInfoFragment fragment = new DriverInfoFragment();
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putLong("DriverId", Objects.requireNonNull(driverViewModel.getAllDrivers().getValue()).get(position)
                .driverEntity.getDriverId());
        fragment.setArguments(bundle);
        transaction.replace(R.id.fragmentContainerView2, fragment);
        transaction.commit();
    }
}

