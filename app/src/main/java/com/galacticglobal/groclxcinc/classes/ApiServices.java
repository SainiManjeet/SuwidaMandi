package com.galacticglobal.groclxcinc.classes;

import android.app.Application;

import com.galacticglobal.groclxcinc.util.ConnectivityHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServices {
    public static Gson gson = new GsonBuilder().setLenient().create();
    private static Retrofit retrofit = null;
    public static Retrofit apiService(Application application){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
             //   .readTimeout(1,TimeUnit.SECONDS)
                .readTimeout(1,TimeUnit.MINUTES)
                .writeTimeout(1,TimeUnit.SECONDS)
                .build();// if (ConnectivityHelper.isConnectedToNetwork(application)) {
        if(retrofit == null && (ConnectivityHelper.isConnectedToNetwork(application))){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    // 2
    public static Retrofit apiService(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                //   .readTimeout(1,TimeUnit.SECONDS)
                .readTimeout(1,TimeUnit.MINUTES)
                .writeTimeout(1,TimeUnit.SECONDS)
                .build();// if (ConnectivityHelper.isConnectedToNetwork(application)) {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

}
