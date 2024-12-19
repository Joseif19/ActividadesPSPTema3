package com.example.actividadespsptema3.Actividad3_8.Ejercicio3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            Scanner scanner = new Scanner(System.in);

            System.out.println("Cliente UDP iniciado. Escribe 'salir' para terminar.");
            while (true) {
                System.out.print("Introduce el idAlumno a consultar: ");
                String idAlumno = scanner.nextLine();

                if (idAlumno.equalsIgnoreCase("salir")) {
                    break;
                }

                byte[] bufferEnvio = idAlumno.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, direccionServidor, 12345);
                socket.send(paqueteEnvio);

                byte[] bufferRecepcion = new byte[1024];
                DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
                socket.receive(paqueteRecepcion);

                String respuesta = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());
                System.out.println("Respuesta del servidor: " + respuesta);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
