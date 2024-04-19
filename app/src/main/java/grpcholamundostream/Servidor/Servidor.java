package grpcholamundostream.Servidor;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Servidor {
    public static void main(String[] args) throws IOException, InterruptedException {
        int puerto = 9191;

        Server servidor = ServerBuilder.forPort(puerto).addService(new ServidorImpl()).build();
        servidor.start();

        System.out.println("Servidor iniciado...");
        System.out.println("Escuchando en el puerto: " + puerto);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Recibiendo solicitud de apagado...");
            servidor.shutdown();
            System.out.println("Servidor detenido.");
        }));

        servidor.awaitTermination();
    }
}