package com.example.actividadespsptema3.Actividad3_7;

import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {
        int port = 1111;

        try (ServerSocket servidor = new ServerSocket(port)) {
            System.out.println("Servidor iniciado. Esperando conexiones...");

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado.");

                ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());

                // Recibir objeto Numeros del cliente
                Numeros numeros = (Numeros) in.readObject();

                // Calcular cuadrado y cubo
                int numero = numeros.getNumero();
                numeros.setCuadrado((long) Math.pow(numero, 2));
                numeros.setCubo((long) Math.pow(numero, 3));

                // Enviar objeto modificado al cliente
                out.writeObject(numeros);
                out.flush();

                System.out.println("Resultado enviado al cliente: Número = " + numero +
                        ", Cuadrado = " + numeros.getCuadrado() +
                        ", Cubo = " + numeros.getCubo());

                // Cerrar conexiones
                in.close();
                out.close();
                cliente.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
