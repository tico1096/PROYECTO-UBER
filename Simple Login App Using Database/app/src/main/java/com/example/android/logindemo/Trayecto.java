package com.example.android.logindemo;

public class Trayecto {
    String cedula;
    String nombre;
    String nombrepas;
    String fechainicio;
    String fechafin;
    String valor;

    public Trayecto(String cedula, String nombre,String nombrepas,String fechainicio,String fechafin,String valor) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.nombrepas = nombrepas;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.valor = valor;
    }

    public String getIdUsuario() { return cedula; }

    public void setIdUsuario(String cedula) { this.cedula = cedula; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombrepas() {
        return nombrepas;
    }

    public void setNombrepas(String nombrepas) {
        this.nombrepas = nombrepas;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}