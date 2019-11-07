package com.smarthome.asteroids;

public class AsteroidTable {

    private String name;
    private String isDangerous;
    private String date;
    private String magnitude;
    private String distance;

    public AsteroidTable()
    {

    }

    public AsteroidTable(String name, String isDangerous, String magnitude, String date, String distance)
    {
        this.name = name;
        this.isDangerous = isDangerous == "false" ? "No": "Si";
        this.magnitude = magnitude;
        this.date = date;
        this.distance = distance;
    }

    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}
    public String getIsDangerous(){return this.isDangerous;}
    public void setIsDangerous(String isDangerous){this.isDangerous = isDangerous;}
    public String getMagnitude(){return this.magnitude;}
    public void setMagnitude(String magnitude){this.magnitude = magnitude;}
    public String getDate(){return this.date;}
    public void setDate(String date){this.date = date;}
    public String getDistance(){return this.distance;}
    public void setDistance(String date){this.distance = distance;}
}
