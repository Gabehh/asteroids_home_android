package com.smarthome.asteroids;

import com.smarthome.asteroids.DTOLamp.Color;
import com.smarthome.asteroids.DTOLamp.Fade;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface LampApiServer {
    @PUT("/ring/on/")
    Call<Result> onLamp();

    @PUT("/ring/off/")
    Call<Result> offLamp();

    @PUT("/ring/color/")
    Call<Result> setColor(@Body Color color);

    @PUT("/ring/fade/")
    Call<Result> setFade(@Body Fade fade);

    @PUT("/ring/rainbow/")
    Call<Result> setRainbow();

    @PUT("/speaker/beep/")
    Call<Result> setBeep();

}
