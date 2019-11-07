package com.smarthome.asteroids.DTO;

public class Close_approach_data
{
    private Relative_velocity relative_velocity;

    private String orbiting_body;

    private String close_approach_date;

    private String epoch_date_close_approach;

    private String close_approach_date_full;

    private Miss_distance miss_distance;

    public Relative_velocity getRelative_velocity ()
    {
        return relative_velocity;
    }

    public void setRelative_velocity (Relative_velocity relative_velocity)
    {
        this.relative_velocity = relative_velocity;
    }

    public String getOrbiting_body ()
    {
        return orbiting_body;
    }

    public void setOrbiting_body (String orbiting_body)
    {
        this.orbiting_body = orbiting_body;
    }

    public String getClose_approach_date ()
    {
        return close_approach_date;
    }

    public void setClose_approach_date (String close_approach_date)
    {
        this.close_approach_date = close_approach_date;
    }

    public String getEpoch_date_close_approach ()
    {
        return epoch_date_close_approach;
    }

    public void setEpoch_date_close_approach (String epoch_date_close_approach)
    {
        this.epoch_date_close_approach = epoch_date_close_approach;
    }

    public String getClose_approach_date_full ()
    {
        return close_approach_date_full;
    }

    public void setClose_approach_date_full (String close_approach_date_full)
    {
        this.close_approach_date_full = close_approach_date_full;
    }

    public Miss_distance getMiss_distance ()
    {
        return miss_distance;
    }

    public void setMiss_distance (Miss_distance miss_distance)
    {
        this.miss_distance = miss_distance;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [relative_velocity = "+relative_velocity+", orbiting_body = "+orbiting_body+", close_approach_date = "+close_approach_date+", epoch_date_close_approach = "+epoch_date_close_approach+", close_approach_date_full = "+close_approach_date_full+", miss_distance = "+miss_distance+"]";
    }
}