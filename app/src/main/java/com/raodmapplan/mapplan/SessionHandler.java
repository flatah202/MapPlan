package com.raodmapplan.mapplan;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;

public class SessionHandler  extends AppCompatActivity {
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_USERNAME = "username";

    private static final String KEY_PASSWORD = "password";

    private static final String KEY_EXPIRES = "expires";
    private static final String KEY_EMPTY = "";
    private Context mContext;
    private SharedPreferences.Editor DB;
    private SharedPreferences mPreferences;

    public SessionHandler(Context mContext) {
        this.mContext = mContext;
        mPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.DB = mPreferences.edit();
    }

    /**
     * Logs in the user by saving user details and setting session
     *
     * @param username
     * @param password
     */
    public void loginUser(String username, String password) {
        DB.putString(KEY_USERNAME, username);
        DB.putString( KEY_PASSWORD, password);
        Date date = new Date();

        //Set user session for next 7 days
        long millis = date.getTime() + (7 * 24 * 60 * 60 * 1000);
        DB.putLong(KEY_EXPIRES, millis);
        DB.commit();
    }

    /**
     * Checks whether user is logged in
     *
     * @return
     */
    public boolean isLoggedIn() {
        Date currentDate = new Date();

        long millis = mPreferences.getLong(KEY_EXPIRES, 0);

        /* If shared preferences does not have a value
         then user is not logged in
         */
        if (millis == 0) {
            return false;
        }
        Date expiryDate = new Date(millis);

        /* Check if session is expired by comparing
        current date and Session expiry date
        */
        return currentDate.before(expiryDate);
    }// end of isLoggedIn

    /**
     * Fetches and returns user details
     *
     * @return user details
     */
    public Users getUserDetails() {
        //Check if user is logged in first
        if (!isLoggedIn()) {
            return null;
        }
        Users user = new Users();
        user.setUsername(mPreferences.getString(KEY_USERNAME, KEY_EMPTY));
        user.setPassword(mPreferences.getString(KEY_PASSWORD, KEY_EMPTY));
        user.setSessionExpiryDate(new Date(mPreferences.getLong(KEY_EXPIRES, 0)));

        return user;
    }// end of getUserDetails

    /**
     * Logs out user by clearing the session
     */
    public void logoutUser(){
        DB.clear();
        DB.commit();
    }// end of logoutUser


}// ENd of SessionHandler class
