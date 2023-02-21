package com.galacticglobal.groclxcinc.classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.ViewGroup;


import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.activity.HomeScreenActivity;
import com.galacticglobal.groclxcinc.application.GroceryApplication;
import com.google.android.material.snackbar.Snackbar;

public class HelperClass {
    public static final String COUNTRY_DATA = "countryData";
    private static final String COUNTRY_Success = "countrySuccess";
    public static String LOG_TAG = "HelperClass";
    public static String DATA = "countryData";
    static CustomLoader customLoader;
    // private static AccessibilityService context;
    private static SharedPreferences sharedPreferences;


    //


    public HelperClass(Context context) {
        sharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
    }

    public HelperClass(GroceryApplication application) {
        sharedPreferences = application.getSharedPreferences("MyPref", Context.MODE_PRIVATE);

    }

    //

    public static void snackbar(Context context, ViewGroup layout, String message) {
        Snackbar.make(layout, message, Snackbar.LENGTH_LONG)
                .setAction("Hide", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).setActionTextColor(context.getResources().getColor(R.color.white)).show();
    }

    public static void snackbar(Context context, ViewGroup layout, int message) {
        Snackbar.make(layout, message, Snackbar.LENGTH_LONG)
                .setAction("Hide", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).setActionTextColor(context.getResources().getColor(R.color.white)).show();
    }

    public static void showLoader(Activity activity) {
        try {
            customLoader = new CustomLoader(activity);
            customLoader.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            customLoader.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideLoader() {
        try {
            customLoader.dismiss();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void setSubClick(Integer subClick, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("SubClick", subClick);
        editor.apply();
    }

    public static Integer getSubClick(Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences("MyPref", 0);
        return pref.getInt("SubClick", 0);
    }

    public static boolean getNetworkInfo(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isConnected = false;
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            isConnected = (activeNetwork != null);
        }
        return isConnected;
    }


    public static void setUserStatus(String userStatus, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("UserStatus", userStatus);
        editor.apply();
    }

    public static String getUserStatus(Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences("MyPref", 0);
        return pref.getString("UserStatus", "");
    }

    public static void setUserName(String userName, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("UserName", userName);
        editor.apply();
    }

    public static String getUserName(Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences("MyPref", 0);
        return pref.getString("UserName", "");
    }
    // First Name
    public static void setFName(String userName, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("fName", userName);
        editor.apply();
    }

    public static String getFName(Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences("MyPref", 0);
        return pref.getString("fName", "");
    }
    // Last Name
    public static void setLName(String userName, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("lName", userName);
        editor.apply();
    }

    public static String getLName(Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences("MyPref", 0);
        return pref.getString("lName", "");
    }
    // Phone No
    public static void setPhone(String userName, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("phone", userName);
        editor.apply();
    }

    public static String getPhone(Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences("MyPref", 0);
        return pref.getString("phone", "");
    }






    // User Id
       public static void setUserId(String userId, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("userId", userId);
        editor.apply();
    }
    public static String getUserId(Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences("MyPref", 0);
        return pref.getString("userId", "");
    }
    // Address
    public static void setAddress(String userId, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("address", userId);
        editor.apply();
    }
    public static String getAddress(Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences("MyPref", 0);
        return pref.getString("address", "");
    }

    // Email
    public static void setEmail(String email, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("email", email);
        editor.apply();
    }
    public static String getEmail(Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences("MyPref", 0);
        return pref.getString("email", "");
    }


    // Order Session
    public static void setOrderSession(String userId, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("session", userId);
        editor.apply();
    }
    public static String getOrderSession(Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences("MyPref", 0);
        return pref.getString("session", "");
    }



   public static void LogOut(Activity activity) {
        SharedPreferences.Editor sharedPreferences = activity.getSharedPreferences(Constants.PREFERENCES, 0).edit();
        if (sharedPreferences != null) {
            sharedPreferences.clear();
            sharedPreferences.apply();
            Intent intent = new Intent(activity, HomeScreenActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }
    public static void setSubCatName(String subCat, Activity sActivity) {
        SharedPreferences pref = sActivity.getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("SubCat", subCat);
        editor.apply();
    }

    public static String getSubCatName(Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences("MyPref", 0);
        return pref.getString("SubCat", "");
    }

}
