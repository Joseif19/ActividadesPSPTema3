package com.example.actividadespsptema3.Actividad3_7;

import java.io.*;
import java.net.*;

//este programa tiene una clase Servidor, otra Cliente y una clase Numeros. el cliente introduce un numero por teclado que inicializa un objeto Numeros y se le manda al servidor. el servidor calcula el cuadrado y el cubo del numero y lo muestra por pantalla y lo envia al cliente. cuando se introduce un numero <= 0 se acaba el programa

public class Servidor {

    public static void main(String[] args) {
        int port = 1111; //puerto al que se conectara el cliente

        try (ServerSocket servidor = new ServerSocket(port)) { //se crea el servidor que escucha en el puerto 1111 y se manejan errores
            System.out.println("Servidor iniciado. Esperando conexiones...");

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado.");

                //se crean los inputs y outputs para recibir y mandar objetos
                ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());

                //se recibe un objeto Numeros del cliente
                Numeros numeros = (Numeros) in.readObject();

                //se calcula el cuadrado y el cubo
                int n = numeros.getN();
                numeros.setCuadrado((long) Math.pow(n, 2));
                numeros.setCubo((long) Math.pow(n, 3));

                //se envia el objeto al cliente
                out.writeObject(numeros);
                out.flush();

                //se imprime por pantalla el resultado
                System.out.println("Resultado enviado al cliente: Número = " + n + ", Cuadrado = " + numeros.getCuadrado() + ", Cubo = " + numeros.getCubo());

                //se cierran las conexiones
                in.close();
                out.close();
                cliente.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
