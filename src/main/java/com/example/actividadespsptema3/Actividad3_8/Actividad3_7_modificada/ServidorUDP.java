package com.example .actividadespsptema3.Actividad3_8.Actividad3_7_modificada;

import java.net.*;

public class ServidorUDP {

    public static void main(String[] args) {
        int port = 1111;

        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Servidor iniciado. Esperando conexiones...");

            byte[] recibirNumero = new byte[1024];

            while (true) {
                //se recibe el numero del cliente
                DatagramPacket recibePaquete = new DatagramPacket(recibirNumero, recibirNumero.length);
                socket.receive(recibePaquete);

                String recibirDatos = new String(recibePaquete.getData(), 0, recibePaquete.getLength());
                int n = Integer.parseInt(recibirDatos);

                if (n <= 0) {
                    System.out.println("Finalizando servidor.");
                    break;
                }

                //se calculan el cuadrado y el cubo del numero
                Numeros numeros = new Numeros(n);
                System.out.println("Procesando número: " + numeros);

                //se envia el resultado de vuelta al cliente
                String respuesta = numeros.toString();
                byte[] mandarNumero = respuesta.getBytes();
                InetAddress direccionCliente = recibePaquete.getAddress();
                int puertoCliente = recibePaquete.getPort();

                DatagramPacket mandarPaquete = new DatagramPacket(mandarNumero, mandarNumero.length, direccionCliente, puertoCliente);
                socket.send(mandarPaquete);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
