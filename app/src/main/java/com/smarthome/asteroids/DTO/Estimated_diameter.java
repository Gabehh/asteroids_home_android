package com.smarthome.asteroids.DTO;

public class Estimated_diameter
{
    private Feet feet;

    private Kilometers kilometers;

    private Meters meters;

    private Miles miles;

    public Feet getFeet ()
    {
        return feet;
    }

    public void setFeet (Feet feet)
    {
        this.feet = feet;
    }

    public Kilometers getKilometers ()
    {
        return kilometers;
    }

    public void setKilometers (Kilometers kilometers)
    {
        this.kilometers = kilometers;
    }

    public Meters getMeters ()
    {
        return meters;
    }

    public void setMeters (Meters meters)
    {
        this.meters = meters;
    }

    public Miles getMiles ()
    {
        return miles;
    }

    public void setMiles (Miles miles)
    {
        this.miles = miles;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [feet = "+feet+", kilometers = "+kilometers+", meters = "+meters+", miles = "+miles+"]";
    }
}
