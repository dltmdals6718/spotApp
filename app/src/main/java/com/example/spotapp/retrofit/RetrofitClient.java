package com.example.spotapp.retrofit;


import com.example.spotapp.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if(retrofit==null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://" + BuildConfig.SERVER_IP + ":8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
        }
        return retrofit;
    }
}
