package com.example.apppersonal_adrian.list_pelis;

import java.io.Serializable;

public class PeList implements Serializable {
    private String titulo;
    private String anio;
    private int caratula;

    public PeList(String titulo, String anio, int caratula) {
        this.titulo = titulo;
        this.anio = anio;
        this.caratula = caratula;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAnio() {
        return anio;
    }

    public int getCaratula() {
        return caratula;
    }
}
