package com.example.apppersonal_adrian.list_tareas;

public class Tarea {

    private String titulo;
    private String descripcion;
    private boolean favoritos;


    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isFavoritos() {
        return favoritos;
    }
    public void setFavoritos(boolean favoritos) {
        this.favoritos = favoritos;
    }
}
