package com.smarthome.asteroids;

import android.os.Parcel;
import android.os.Parcelable;

public class AsteroidTable implements Parcelable {

    private Integer id;
    private String name;
    private String isDangerous;
    private String date;
    private String magnitude;
    private String distance;

    public AsteroidTable()
    {

    }

    public AsteroidTable(Integer id, String name, String isDangerous, String magnitude, String date, String distance)
    {
        this.id = id;
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
    public Integer getId(){return this.id;}
    public void setId(Integer id){this.id = id;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(isDangerous);
        dest.writeString(magnitude);
        dest.writeString(date);
        dest.writeString(distance);
    }

    protected AsteroidTable(Parcel in) {
        id = in.readInt();
        name = in.readString();
        isDangerous = in.readString();
        magnitude = in.readString();
        date = in.readString();
        distance = in.readString();
    }


    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AsteroidTable> CREATOR = new Parcelable.Creator<AsteroidTable>() {
        @Override
        public AsteroidTable createFromParcel(Parcel in) {
            return new AsteroidTable(in);
        }

        @Override
        public AsteroidTable[] newArray(int size) {
            return new AsteroidTable[size];
        }
    };
}
