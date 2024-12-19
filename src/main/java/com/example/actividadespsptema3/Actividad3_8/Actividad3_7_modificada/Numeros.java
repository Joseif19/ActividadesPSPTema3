package com.example.actividadespsptema3.Actividad3_8.Actividad3_7_modificada;

public class Numeros {
    private int n;
    private long cuadrado;
    private long cubo;

    public Numeros(int n) {
        this.n = n;
        this.cuadrado = (long) Math.pow(n, 2);
        this.cubo = (long) Math.pow(n, 3);
    }

    public int getN() {
        return n;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public long getCubo() {
        return cubo;
    }

    @Override
    public String toString() {
        return "Número: " + n + ", Cuadrado: " + cuadrado + ", Cubo: " + cubo;
    }
}
