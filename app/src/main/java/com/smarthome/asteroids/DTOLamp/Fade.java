package com.smarthome.asteroids.DTOLamp;

public class Fade {
    private int n;
    private int d;

    public  Fade(int n, int d){
        this.n = n;
        this.d = d;
    }

    public int getN(){return this.n;}
    public int getD(){return this.d;}
    public void setN(int n){this.n = n;}
    public void setD(int d){this.d = d;}
}
