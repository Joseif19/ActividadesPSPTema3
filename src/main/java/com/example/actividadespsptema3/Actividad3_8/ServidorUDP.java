package com.example.actividadespsptema3.Actividad3_8;

import java.io.*;
import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) {
        int puertoServidor = 1111;

        try (DatagramSocket socket = new DatagramSocket(puertoServidor)) {
            System.out.println("Servidor UDP esperando datagramas en el puerto " + puertoServidor + "...");

            while (true) {
                // 1. Recibir datagrama del cliente
                byte[] bufferEntrada = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                socket.receive(paqueteRecibido);

                // 2. Deserializar objeto Persona
                ByteArrayInputStream bais = new ByteArrayInputStream(paqueteRecibido.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Persona persona = (Persona) ois.readObject();

                System.out.println("Servidor recibió: " + persona);

                // 3. Modificar objeto Persona
                persona.setNombre(persona.getNombre().toUpperCase());
                persona.setEdad(persona.getEdad() + 1);

                System.out.println("Servidor envía modificado: " + persona);

                // 4. Serializar objeto modificado
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(persona);
                oos.flush();
                byte[] bufferSalida = baos.toByteArray();

                // 5. Enviar objeto modificado al cliente
                InetAddress direccionCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();
                DatagramPacket paqueteEnviado = new DatagramPacket(bufferSalida, bufferSalida.length, direccionCliente, puertoCliente);
                socket.send(paqueteEnviado);

                System.out.println("Objeto modificado enviado al cliente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
