package com.example.carcab.AuthenticateFeature.AuthenticationComponents.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carcab.AuthenticateFeature.AuthenticationComponents.Models.Abstractions_Obsolete.IDrivable;
import com.example.carcab.AuthenticateFeature.AuthenticationComponents.Models.UserInfo;
import com.example.carcab.AuthenticateFeature.AuthenticationComponents.Repository.DatabaseDriverFinder;
import com.example.carcab.AuthenticateFeature.AuthenticationComponents.Repository.FirebaseConnector;
import com.example.carcab.AuthenticateFeature.AuthenticationComponents.Repository.IAuthenticate;
import com.example.carcab.AuthenticateFeature.AuthenticationComponents.Repository.IDataFinder;
import com.example.carcab.AuthenticateFeature.AuthenticationComponents.Repository.RegularAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationViewModel {

    private static AuthenticationViewModel mSelfAuthVM;
    FirebaseUser userInSession;
    UserInfo userMetadata;
    private IAuthenticate authenticator;
    private AuthenticationViewModel()
    {

    }

    public static AuthenticationViewModel getInstance()
    {
        if (mSelfAuthVM == null)
        {
            mSelfAuthVM = new AuthenticationViewModel();
            mSelfAuthVM.setAuthenticator(new RegularAuth());
        }
        return mSelfAuthVM;
    }

    public boolean initLoginProcess(String email, String password)
    {
        UserInfo userData = new UserInfo(email, password);
        boolean loginResult = !(email.isBlank() || password.isBlank()) ? getAuthenticator().performLogin(userData) : false;
        if (loginResult == true)
        {
            FirebaseUser loggedUser = FirebaseConnector
                    .getInstance()
                    .getFirebaseAuthInstance()
                    .getCurrentUser();
            String userUID = loggedUser.getUid();
            // check which user group this one belongs to...
            IDataFinder<String> finder = new DatabaseDriverFinder();
            boolean findDriver = finder.isAvailable(userUID);
            userData.setDriver(findDriver);
            userMetadata = userData;
        }
        return loginResult;
    }

    public boolean initRegisterProcess(String email, String password, String password_2, String userType, String comparableConstant) throws Exception {
        if (password.hashCode() == password_2.hashCode() && !(password.isBlank() || password_2.isBlank() || userType.isBlank() ||comparableConstant.isBlank()))
        {
            boolean isDriver = userType.equalsIgnoreCase(comparableConstant) ? true : false;
            UserInfo user = new UserInfo(email, password, isDriver);
            if (isDriver) {
                user.setVehicle(new IDrivable() {
                    @Override
                    public String getVehicleType() {
                        return "Not configured";
                    }
                });
            }
            userMetadata = user;
            boolean status = getAuthenticator().performRegister(user);
            if (status)
            {
                userInSession = FirebaseConnector.getInstance().getFirebaseAuthInstance().getCurrentUser();
                return true;
            }
        }
        return false;
    }

    private void setAuthenticator(IAuthenticate auth)
    {
        this.authenticator = auth;
    }

    private IAuthenticate getAuthenticator()
    {
        return this.authenticator;
    }


}
