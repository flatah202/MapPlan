package com.raodmapplan.mapplan;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class Users extends AppCompatActivity {


    String username;
    String password;
    Date sessionExpiryDate;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSessionExpiryDate(Date sessionExpiryDate) {
        this.sessionExpiryDate = sessionExpiryDate;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getSessionExpiryDate() {
        return sessionExpiryDate;
    }




}// End of Users class
