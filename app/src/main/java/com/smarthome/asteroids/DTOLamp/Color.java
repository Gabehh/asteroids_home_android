package com.smarthome.asteroids.DTOLamp;

public class Color {
    private int r;
    private int g;
    private int b;

    public  Color(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getR(){return this.r;}
    public int getG(){return this.g;}
    public int getB(){return this.b;}
    public void setR(int r){this.r = r;}
    public void setG(int g){this.g = g;}
    public void setB(int b){this.g = b;}

}
