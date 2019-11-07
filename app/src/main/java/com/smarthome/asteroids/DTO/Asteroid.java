package com.smarthome.asteroids.DTO;

public class Asteroid {
    private Estimated_diameter estimated_diameter;

    private String neo_reference_id;

    private String nasa_jpl_url;

    private String is_potentially_hazardous_asteroid;

    private String is_sentry_object;

    private String name;

    private String absolute_magnitude_h;

    private Links links;

    private String id;

    private Close_approach_data[] close_approach_data;

    public Asteroid(){

    }

    public Estimated_diameter getEstimated_diameter ()
    {
        return estimated_diameter;
    }

    public void setEstimated_diameter (Estimated_diameter estimated_diameter)
    {
        this.estimated_diameter = estimated_diameter;
    }

    public String getNeo_reference_id ()
    {
        return neo_reference_id;
    }

    public void setNeo_reference_id (String neo_reference_id)
    {
        this.neo_reference_id = neo_reference_id;
    }

    public String getNasa_jpl_url ()
    {
        return nasa_jpl_url;
    }

    public void setNasa_jpl_url (String nasa_jpl_url)
    {
        this.nasa_jpl_url = nasa_jpl_url;
    }

    public String getIs_potentially_hazardous_asteroid ()
    {
        return is_potentially_hazardous_asteroid;
    }

    public void setIs_potentially_hazardous_asteroid (String is_potentially_hazardous_asteroid)
    {
        this.is_potentially_hazardous_asteroid = is_potentially_hazardous_asteroid;
    }

    public String getIs_sentry_object ()
    {
        return is_sentry_object;
    }

    public void setIs_sentry_object (String is_sentry_object)
    {
        this.is_sentry_object = is_sentry_object;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getAbsolute_magnitude_h ()
    {
        return absolute_magnitude_h;
    }

    public void setAbsolute_magnitude_h (String absolute_magnitude_h)
    {
        this.absolute_magnitude_h = absolute_magnitude_h;
    }

    public Links getLinks ()
    {
        return links;
    }

    public void setLinks (Links links)
    {
        this.links = links;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Close_approach_data[] getClose_approach_data ()
    {
        return close_approach_data;
    }

    public void setClose_approach_data (Close_approach_data[] close_approach_data)
    {
        this.close_approach_data = close_approach_data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [estimated_diameter = "+estimated_diameter+", neo_reference_id = "+neo_reference_id+", nasa_jpl_url = "+nasa_jpl_url+", is_potentially_hazardous_asteroid = "+is_potentially_hazardous_asteroid+", is_sentry_object = "+is_sentry_object+", name = "+name+", absolute_magnitude_h = "+absolute_magnitude_h+", links = "+links+", id = "+id+", close_approach_data = "+close_approach_data+"]";
    }
}
