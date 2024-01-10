package com.example.gernikaapp.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Ubicaciones implements Parcelable {

    private double lat = 0;
    private double lon = 0;
    private String nombreUbicacion;
    public Ubicaciones(double lat, double lon, String nombreUbicacion) {
        super();
        this.lat = lat;
        this.lon = lon;
        this.nombreUbicacion = nombreUbicacion;
    }

    public Ubicaciones() {
    }

    protected Ubicaciones(Parcel in) {
        lat = in.readDouble();
        lon = in.readDouble();
        nombreUbicacion = in.readString();
    }

    public static final Creator<Ubicaciones> CREATOR = new Creator<Ubicaciones>() {
        @Override
        public Ubicaciones createFromParcel(Parcel in) {
            return new Ubicaciones(in);
        }

        @Override
        public Ubicaciones[] newArray(int size) {
            return new Ubicaciones[size];
        }
    };

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getNombreUbicacion() {
        return nombreUbicacion;
    }

    public void setNombreUbicacion(String ubicacion) {
        this.nombreUbicacion = ubicacion;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeDouble(lat);
        dest.writeDouble(lon);
        dest.writeString(nombreUbicacion);
    }
}
