package com.example.carcab.CompAuthentication.Models.Abstractions;

public abstract class User {
    protected String emailCredential;
    protected String passwordCredential;

    public User(String email, String password)
    {
        this.emailCredential = email;
        this.passwordCredential = passwordCredential;
    }

    public String getUserEmail()
    {
        return this.emailCredential;
    }

    public String getPassword()
    {
        return this.passwordCredential;
    }
}
