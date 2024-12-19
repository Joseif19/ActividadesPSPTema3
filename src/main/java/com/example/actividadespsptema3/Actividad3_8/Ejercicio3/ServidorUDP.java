package com.example.actividadespsptema3.Actividad3_8.Ejercicio3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class ServidorUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(12345);
            System.out.println("Servidor UDP iniciado en el puerto 12345...");

            // Crear datos de ejemplo
            Curso c1 = new Curso("c1", "PSP");
            Curso c2 = new Curso("c2", "HLC");
            Alumno[] alumnos = {
                    new Alumno("a1", "José María Iglesias", c1, 7),
                    new Alumno("a2", "Jesús Rodríguez", c2, 3)
            };

            // Map para búsqueda rápida por idAlumno
            HashMap<String, Alumno> mapaAlumnos = new HashMap<>();
            for (Alumno alumno : alumnos) {
                mapaAlumnos.put(alumno.getIdAlumno(), alumno);
            }

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);

                String idAlumno = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("Consulta recibida para idAlumno: " + idAlumno);

                String respuesta;
                if (mapaAlumnos.containsKey(idAlumno)) {
                    respuesta = mapaAlumnos.get(idAlumno).toString();
                } else {
                    respuesta = "El alumno con id " + idAlumno + " no existe.";
                }

                byte[] bufferRespuesta = respuesta.getBytes();
                InetAddress direccionCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();
                DatagramPacket paqueteEnviado = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, direccionCliente, puertoCliente);
                socket.send(paqueteEnviado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

