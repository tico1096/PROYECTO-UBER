package com.example.android.logindemo;

public class Usuario {
    String cedula;
    String nombre;
    String placa;
    String calificacion;
    String observacion;

    public Usuario(String cedula, String nombre, String placa, String calificacion, String observacion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.placa = placa;
        this.calificacion = calificacion;
        this.observacion = observacion;
    }

    public String getIdUsuario() {
        return cedula;
    }

    public void setIdUsuario(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return placa;
    }

    public void setTelefono(String placa) {
        this.placa = placa;
    }

    public String getEmail() {
        return calificacion;
    }

    public void setEmail(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getUsuario() {
        return observacion;
    }

    public void setUsuario(String observacion) {
        this.observacion = observacion;
    }

}
