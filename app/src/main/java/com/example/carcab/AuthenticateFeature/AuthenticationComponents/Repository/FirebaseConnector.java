package com.example.carcab.AuthenticateFeature.AuthenticationComponents.Repository;

import com.example.carcab.BuildConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConnector {
    private static FirebaseConnector mSelfFirebase;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private FirebaseConnector()
    {
        this.mAuth = FirebaseAuth.getInstance();
        this.mDatabase = FirebaseDatabase.getInstance(BuildConfig.DATABASE_API_KEY).getReference();
    }

    public static FirebaseConnector getInstance()
    {
        if (mSelfFirebase == null)
        {
            mSelfFirebase = new FirebaseConnector();
        }
        return mSelfFirebase;
    }

    public FirebaseAuth getFirebaseAuthInstance()
    {
        return this.mAuth;
    }

    public DatabaseReference getFirebaseDatabaseInstance()
    {
        return this.mDatabase;
    }

}
