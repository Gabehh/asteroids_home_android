package com.smarthome.asteroids;
import android.os.Parcel;
import android.os.Parcelable;

public class AsteroidHistory implements Parcelable {

    private Integer id;
    private String name;
    private String isDangerous;
    private String magnitude;

    public AsteroidHistory()
    {

    }

    public AsteroidHistory(Integer id, String name, String isDangerous, String magnitude)
    {
        this.id = id;
        this.name = name;
        this.isDangerous = isDangerous.equals("false") ? "No": "Si";
        this.magnitude = magnitude;
    }

    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}
    public String getIsDangerous(){return this.isDangerous;}
    public void setIsDangerous(String isDangerous){this.isDangerous = isDangerous;}
    public String getMagnitude(){return this.magnitude;}
    public void setMagnitude(String magnitude){this.magnitude = magnitude;}
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
    }

    protected AsteroidHistory(Parcel in) {
        id = in.readInt();
        name = in.readString();
        isDangerous = in.readString();
        magnitude = in.readString();
    }


    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AsteroidHistory> CREATOR = new Parcelable.Creator<AsteroidHistory>() {
        @Override
        public AsteroidHistory createFromParcel(Parcel in) {
            return new AsteroidHistory(in);
        }

        @Override
        public AsteroidHistory[] newArray(int size) {
            return new AsteroidHistory[size];
        }
    };
}
