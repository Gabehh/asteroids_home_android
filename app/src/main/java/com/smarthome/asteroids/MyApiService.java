package com.smarthome.asteroids;
import com.smarthome.asteroids.DTO.AsteroidMetaData;
import com.smarthome.asteroids.DTO.Asteroids;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApiService {

    @GET("/neo/rest/v1/feed")
    Call<Asteroids> getAsteroids(@Query("start_date") String start_date, @Query("end_date") String end_date, @Query("api_key") String api_key);

    @GET("/neo/rest/v1/neo/{id}")
    Call<AsteroidMetaData> getOnlyAsteroid(@Path("id") Integer id, @Query("api_key") String api_key);

}
