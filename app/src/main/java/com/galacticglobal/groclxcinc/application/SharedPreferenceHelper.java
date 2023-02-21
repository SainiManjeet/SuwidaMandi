package com.galacticglobal.groclxcinc.application;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {
    private static final String IS_LOGGED_IN = "isLoggedIn";
    private static final String AUTH_TOKEN = "authToken";
    private static final String USER_ID = "userId";
    private static final String USER_NAME = "username";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String USER_TYPES = "userTypes";
    private static final String COUNTRY_DATA = "countryData";
    private static final String COUNTRY_Success = "countrySuccess";

    // Add On
    String LOG_TAG = "SharedPreferenceHelper";
    private SharedPreferences sharedPreferences;

    public SharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("LynxPrefs", Context.MODE_PRIVATE);
    }

    public SharedPreferenceHelper(GroceryApplication application) {
        sharedPreferences = application.getSharedPreferences("LynxPrefs", Context.MODE_PRIVATE);
    }

    public void setUserID(String userID) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID, userID);
        editor.apply();
    }

    public String getUserType() {
        return sharedPreferences.getString(USER_TYPES, "");
    }

    public void setUserType(String userType) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_TYPES, userType);
        editor.apply();
    }


    public void logOut() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

    public String getUserId() {
        return sharedPreferences.getString(USER_ID, "");
    }

    public String getFisrstName() {
        return sharedPreferences.getString(FIRST_NAME, "");
    }


    public String getLastName() {
        return sharedPreferences.getString(LAST_NAME, "");
    }

    public String getEmail() {
        return sharedPreferences.getString(USER_ID, "");
    }

    public String
    getAuthToken() {
        return sharedPreferences.getString(AUTH_TOKEN, "");
    }


}
