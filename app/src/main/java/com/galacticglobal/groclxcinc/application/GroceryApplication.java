package com.galacticglobal.groclxcinc.application;

import android.app.Application;
import android.app.ProgressDialog;

public class GroceryApplication extends Application {

    ProgressDialog pDialog;
    // Singletons
    private SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        initSingletonsForApp();
    }

    private void initSingletonsForApp() {
        sharedPreferenceHelper = new SharedPreferenceHelper(this);
    }

    public SharedPreferenceHelper getSharedPreferenceHelper() {
        return sharedPreferenceHelper;
    }

    public void showProgressDialog() {
        pDialog = ProgressDialog.show(getApplicationContext(), null, "Loading...", true);
        pDialog.setCancelable(false);
    }

    public void hideProgressDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }


}