package com.smarthome.asteroids.DTO;

import java.util.ArrayList;
import java.util.Map;

public class Asteroids
{
    private Map<String, ArrayList<Asteroid>> near_earth_objects;

    private String element_count;

    private Links links;

    public  Map<String, ArrayList<Asteroid>> getNear_earth_objects ()
    {
        return near_earth_objects;
    }

    public void setNear_earth_objects( Map<String, ArrayList<Asteroid>> near_earth_objects)
    {
        this.near_earth_objects = near_earth_objects;
    }

    public String getElement_count ()
    {
        return element_count;
    }

    public void setElement_count (String element_count)
    {
        this.element_count = element_count;
    }

    public Links getLinks ()
    {
        return links;
    }

    public void setLinks (Links links)
    {
        this.links = links;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [near_earth_objects = "+near_earth_objects+", element_count = "+element_count+", links = "+links+"]";
    }
}