package com.smarthome.asteroids.DTO;


public class Orbit_class
{
    private String orbit_class_description;

    private String orbit_class_range;

    private String orbit_class_type;

    public String getOrbit_class_description ()
    {
        return orbit_class_description;
    }

    public void setOrbit_class_description (String orbit_class_description)
    {
        this.orbit_class_description = orbit_class_description;
    }

    public String getOrbit_class_range ()
    {
        return orbit_class_range;
    }

    public void setOrbit_class_range (String orbit_class_range)
    {
        this.orbit_class_range = orbit_class_range;
    }

    public String getOrbit_class_type ()
    {
        return orbit_class_type;
    }

    public void setOrbit_class_type (String orbit_class_type)
    {
        this.orbit_class_type = orbit_class_type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [orbit_class_description = "+orbit_class_description+", orbit_class_range = "+orbit_class_range+", orbit_class_type = "+orbit_class_type+"]";
    }
}