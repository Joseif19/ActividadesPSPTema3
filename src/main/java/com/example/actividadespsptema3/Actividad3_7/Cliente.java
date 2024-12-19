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
                //se pide al cliente que introduzca un numero
                System.out.print("Introduce un número (para salir introduce un número <= 0): ");
                int n = sc.nextInt();

                //si el numero es menor o igual que 0 se finaliza el programa
                if (n <= 0) {
                    System.out.println("Fin del cliente.");
                    break;
                }

                //se crea un objeto Numeros con el numero que se ha introducido por teclado
                Numeros numeros = new Numeros();
                numeros.setN(n);

                //se hace la conexion al servidor
                Socket socket = new Socket(host, port);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                //se envia el objeto Numeros al servidor
                out.writeObject(numeros);
                out.flush();

                //recibe objeto Numero modificado del servidor
                Numeros resultado = (Numeros) in.readObject();

                //se muestra por pantalla el resultado
                System.out.println("Número: " + resultado.getN());
                System.out.println("Cuadrado: " + resultado.getCuadrado());
                System.out.println("Cubo: " + resultado.getCubo());

                //se cierran las conexiones
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
