package com.example.android.logindemo;

public class Ingreso {
    String metodo;
    String cedula;
    String valor;
    String fecha;

    public Ingreso(String metodo, String cedula, String valor, String fecha) {
        this.metodo = metodo;
        this.cedula = cedula;
        this.valor = valor;
        this.fecha = fecha;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getIdUsuario() {
        return cedula;
    }

    public void setIdUsuario(String cedula) {
        this.cedula = cedula;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}