package com.smarthome.asteroids;


import com.smarthome.asteroids.DTO.Asteroids;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApiService {
    @GET("/neo/rest/v1/feed")
    Call<Asteroids> getLogin(@Query("start_date") String start_date, @Query("end_date") String end_date, @Query("api_key") String api_key);


}
