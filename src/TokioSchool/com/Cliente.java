package TokioSchool.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        final String host = "localhost";
        final int puerto = 1234;

        try {
            // Establecer conexión con el servidor
            Socket clienteSocket = new Socket(host, puerto);
            System.out.println("Conexión establecida con el servidor");

            // Crear los objetos para leer y escribir datos
            BufferedReader entradaServidor = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            BufferedReader entradaUsuario = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter salidaServidor = new PrintWriter(clienteSocket.getOutputStream(), true);

            String mensaje;

            // Leer mensaje del usuario y enviar al servidor
            while ((mensaje = entradaUsuario.readLine()) != null) {
                // Enviar mensaje al servidor
                salidaServidor.println(mensaje);

                // Leer mensaje recibido del servidor y mostrarlo por consola
                String mensajeRecibido = entradaServidor.readLine();
                System.out.println("Mensaje recibido del servidor: " + mensajeRecibido);

                // Verificar si el cliente quiere cerrar la conexión
                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("Conexión cerrada");
                    break;
                }
            }

            // Cerrar conexión y buffers
            entradaServidor.close();
            entradaUsuario.close();
            salidaServidor.close();
            clienteSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
