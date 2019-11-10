package com.smarthome.asteroids;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LampApiadapter {
    private static LampApiServer API_SERVICE;

    public static LampApiServer getApiService() {

        String baseUrl = "http://192.168.0.102/";

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            API_SERVICE = retrofit.create(LampApiServer.class);
        }

        return API_SERVICE;
    }
}
