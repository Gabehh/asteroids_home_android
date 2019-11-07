package com.smarthome.asteroids.DTO;

public class Kilometers {
    private String estimated_diameter_max;

    private String estimated_diameter_min;

    public String getEstimated_diameter_max() {
        return estimated_diameter_max;
    }

    public void setEstimated_diameter_max(String estimated_diameter_max) {
        this.estimated_diameter_max = estimated_diameter_max;
    }

    public String getEstimated_diameter_min() {
        return estimated_diameter_min;
    }

    public void setEstimated_diameter_min(String estimated_diameter_min) {
        this.estimated_diameter_min = estimated_diameter_min;
    }

    @Override
    public String toString() {
        return "ClassPojo [estimated_diameter_max = " + estimated_diameter_max + ", estimated_diameter_min = " + estimated_diameter_min + "]";
    }
}