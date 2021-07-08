package com.example.mylogi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mylogi.adapters.TruckListAdapter;
import com.example.mylogi.viewmodel.DriverViewModel;
import com.example.mylogi.viewmodel.TruckViewModel;

public class TruckListFragment extends Fragment {
    RecyclerView recyclerView;
    Spinner spinner;
    TruckViewModel truckViewModel;

    public TruckListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_truck_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.truckListRecyclerView);
        spinner = view.findViewById(R.id.spinner);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        TruckListAdapter adapter = new TruckListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(requireActivity(), R.array.spinner,
                android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        truckViewModel = ViewModelProviders.of(this).get(TruckViewModel.class);
        truckViewModel.getAllTrucks().observe(getViewLifecycleOwner(), adapter::setTrucks);

    }
}