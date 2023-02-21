package com.galacticglobal.groclxcinc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.classes.HelperClass;

import java.security.SecureRandom;

public class SplashScreen extends AppCompatActivity {

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    // No Only
    static final String NoString = "0123456789";
    static SecureRandom rnd = new SecureRandom();
    Handler handler;
    Activity activity;
   // String orderSession = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        // Log.d("Final: ",""+ randomNo(10)+"_"+randomString(32));

      /*  orderSession = HelperClass.getOrderSession(SplashScreen.this);
        if (orderSession != null && !orderSession.isEmpty()) {
            // Do Nothing
        } else {
            HelperClass.setOrderSession(randomNo(10) + "_" + randomString(32), SplashScreen.this);
        }*/
        init();

    }

    String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    String randomNo(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(NoString.charAt(rnd.nextInt(NoString.length())));
        return sb.toString();
    }


    private void init() {
        activity = SplashScreen.this;
        handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {

                Intent intent = new Intent(activity, HomeScreenActivity.class);
                startActivity(intent);

            }
        };
        handler.postDelayed(r, 2000);
    }
}