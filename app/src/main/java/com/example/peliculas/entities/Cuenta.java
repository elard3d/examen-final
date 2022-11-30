package com.example.peliculas.entities;

import java.io.Serializable;

public class Cuenta implements Serializable {
     String  montoTipo;
     int monto;
     String motivo;
     String id;
    String Lat;
    String Long;

    public Cuenta() {
    }

    public String getMontoTipo() {
        return montoTipo;
    }

    public void setMontoTipo(String montoTipo) {
        this.montoTipo = montoTipo;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLong() {
        return Long;
    }

    public void setLong(String aLong) {
        Long = aLong;
    }
}
