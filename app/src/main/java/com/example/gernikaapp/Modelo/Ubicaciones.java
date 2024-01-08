package com.example.gernikaapp.Modelo;

public class Ubicaciones {

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




}
