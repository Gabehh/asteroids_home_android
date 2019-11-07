package com.smarthome.asteroids.DTO;

public class Relative_velocity
{
    private String kilometers_per_hour;

    private String kilometers_per_second;

    private String miles_per_hour;

    public String getKilometers_per_hour ()
    {
        return kilometers_per_hour;
    }

    public void setKilometers_per_hour (String kilometers_per_hour)
    {
        this.kilometers_per_hour = kilometers_per_hour;
    }

    public String getKilometers_per_second ()
    {
        return kilometers_per_second;
    }

    public void setKilometers_per_second (String kilometers_per_second)
    {
        this.kilometers_per_second = kilometers_per_second;
    }

    public String getMiles_per_hour ()
    {
        return miles_per_hour;
    }

    public void setMiles_per_hour (String miles_per_hour)
    {
        this.miles_per_hour = miles_per_hour;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [kilometers_per_hour = "+kilometers_per_hour+", kilometers_per_second = "+kilometers_per_second+", miles_per_hour = "+miles_per_hour+"]";
    }
}

