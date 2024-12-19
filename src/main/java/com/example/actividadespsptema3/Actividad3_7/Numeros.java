package com.example.actividadespsptema3.Actividad3_7;

import java.io.Serializable;

//Clase Numeros que implementa Serializable y tiene los atributos n, cuadrado y cubo con sus getter y setter ,y un constructor vacio y otro con parametros

public class Numeros implements Serializable {

    int n;
    long cuadrado;
    long cubo;

    public Numeros(int numero, long cuadrado, long cubo) {
        this.n = numero;
        this.cuadrado = cuadrado;
        this.cubo = cubo;
    }

    public Numeros(){
        super();
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public long getCubo() {
        return cubo;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }
}
