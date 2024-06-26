package com.example.carcab.AuthenticateFeature.AuthenticationViews.AuthFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.carcab.AuthenticateFeature.AuthenticationComponents.ViewModels.AuthViewModelHandler;
import com.example.carcab.AuthenticateFeature.AuthenticationViews.Abstractions.AuthFragment;
import com.example.carcab.R;

public class UserAuthLanding extends AuthFragment {

    public UserAuthLanding() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuthViewModel = AuthViewModelHandler.getInstance();
        return inflater.inflate(R.layout.fragment_user_auth_landing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button loginButton = getView().findViewById(R.id.proceedToLoginFragment);
        Button registerButton = getView().findViewById(R.id.proceedToRegisterFragment);

        SetupBackPressedExit();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(R.id.authFragmentContainer,new UserLogin());
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(R.id.authFragmentContainer,new UserRegister());
            }
        });
    }


}