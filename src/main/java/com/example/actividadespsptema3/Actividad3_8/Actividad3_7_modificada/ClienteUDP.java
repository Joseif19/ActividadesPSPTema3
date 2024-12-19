package com.example.actividadespsptema3.Actividad3_8.Actividad3_7_modificada;

import java.net.*;
import java.util.*;

public class ClienteUDP {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String host = "localhost";
        int port = 1111;

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(host);

            while (true) {
                //se pide al cliente que introduzca un numero
                System.out.print("Introduce un número (para salir introduce un número <= 0): ");
                int n = sc.nextInt();

                //si el numero es <= 0 se finaliza el programa
                if (n <= 0) {
                    System.out.println("Fin del cliente.");
                    break;
                }

                //se envia el numero al servidor
                byte[] mandarNumero = String.valueOf(n).getBytes();
                DatagramPacket enviarPaquete = new DatagramPacket(mandarNumero, mandarNumero.length, address, port);
                socket.send(enviarPaquete);

                //se recibe la respuesta del servidor
                byte[] recibirNumero = new byte[1024];
                DatagramPacket recibePaquete = new DatagramPacket(recibirNumero, recibirNumero.length);
                socket.receive(recibePaquete);

                //se procesan los resultados recibidos
                String respuesta = new String(recibePaquete.getData(), 0, recibePaquete.getLength());
                System.out.println(respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
