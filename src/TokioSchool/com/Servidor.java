package TokioSchool.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        final int puerto = 1234;

        try {
            // Crear el ServerSocket
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor iniciado. Esperando conexiones...");

            // Aceptar conexión con un cliente
            Socket clienteSocket = serverSocket.accept();
            System.out.println("Cliente conectado");

            // Crear los objetos para leer y escribir datos
            BufferedReader entradaCliente = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            PrintWriter salidaCliente = new PrintWriter(clienteSocket.getOutputStream(), true);

            String mensaje;

            // Leer los datos que envía el cliente y mostrarlos por consola
            while ((mensaje = entradaCliente.readLine()) != null) {
                System.out.println("Mensaje recibido: " + mensaje);

                // Transformar los datos a mayúsculas y enviarlos al cliente
                String mensajeMayusculas = mensaje.toUpperCase();
                salidaCliente.println(mensajeMayusculas);

                // Verificar si el cliente quiere cerrar la conexión
                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("Cliente ha cerrado la conexión");
                    break;
                }
            }

            // Cerrar conexión y buffers
            entradaCliente.close();
            salidaCliente.close();
            clienteSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
