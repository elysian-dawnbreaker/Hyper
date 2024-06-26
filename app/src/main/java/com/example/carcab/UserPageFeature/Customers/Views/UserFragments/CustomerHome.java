package com.example.carcab.UserPageFeature.Customers.Views.UserFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carcab.R;
import com.example.carcab.UserPageFeature.BaseContextTemplates.MapFragmentTemplate;

public class CustomerHome extends MapFragmentTemplate {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupEssentialsReferences(getView().findViewById(R.id.mapView),
                getView().findViewById(R.id.focusLocation),
                getView().findViewById(R.id.btn_request_pickup));
        setupEssentialIndicatorListeners();
        userMapStyleSet(getActivity());
        selfChangedListener(false, getView().findViewById(R.id.txt_status));
        addBtnListener(getView().findViewById(R.id.txt_status), false);
        //setDatabaseDriverOrCustomerFoundListener(getView().findViewById(R.id.txt_status), false);
    }
}