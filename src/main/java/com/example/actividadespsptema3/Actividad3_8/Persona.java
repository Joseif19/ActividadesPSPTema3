package com.example.actividadespsptema3.Actividad3_8;

import java.io.Serializable;

//clase Persona que implementa Serializable y tiene los atributos nombre y edad con sus getter y setter y un constructor con parametros

public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona-> " + "Nombre: '" + nombre + '\'' + ", Edad: " + edad + '.';
    }
}
