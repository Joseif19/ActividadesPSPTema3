package com.example.actividadespsptema3.Actividad3_8;

import java.io.*;
import java.net.*;

public class ClienteUDP {
    public static void main(String[] args) {
        String servidorHost = "localhost";
        int puertoServidor = 1111;

        try (DatagramSocket socket = new DatagramSocket()) {
            // 1. Crear objeto Persona
            Persona persona = new Persona("José María", 20);
            System.out.println("Cliente envía: " + persona);

            // 2. Serializar objeto Persona
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(persona);
            oos.flush();
            byte[] bufferSalida = baos.toByteArray();

            // 3. Enviar objeto al servidor
            InetAddress direccionServidor = InetAddress.getByName(servidorHost);
            DatagramPacket paqueteEnviado = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor, puertoServidor);
            socket.send(paqueteEnviado);

            // 4. Recibir objeto modificado del servidor
            byte[] bufferEntrada = new byte[1024];
            DatagramPacket paqueteRecibido = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            socket.receive(paqueteRecibido);

            // 5. Deserializar objeto recibido
            ByteArrayInputStream bais = new ByteArrayInputStream(paqueteRecibido.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Persona personaModificada = (Persona) ois.readObject();

            System.out.println("Cliente recibe modificado: " + personaModificada);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
