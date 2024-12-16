package com.example.actividadespsptema3.Actividad3_7;

import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String host = "localhost";
        int port = 1111;

        try {
            while (true) {
                System.out.print("Introduce un número (<= 0 para salir): ");
                int numero = sc.nextInt();

                if (numero <= 0) {
                    System.out.println("Número menor o igual a 0. Fin del cliente.");
                    break;
                }

                // Crear objeto Numeros con el número introducido
                Numeros numeros = new Numeros();
                numeros.setNumero(numero);

                // Conexión al servidor
                Socket socket = new Socket(host, port);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                // Enviar objeto Numeros al servidor
                out.writeObject(numeros);
                out.flush();

                // Recibir objeto modificado del servidor
                Numeros resultado = (Numeros) in.readObject();

                // Mostrar resultados
                System.out.println("Número: " + resultado.getNumero());
                System.out.println("Cuadrado: " + resultado.getCuadrado());
                System.out.println("Cubo: " + resultado.getCubo());

                // Cerrar conexiones
                in.close();
                out.close();
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
