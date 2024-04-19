package grpcholamundostream.Cliente;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import com.proto.saludo.SaludoServiceGrpc;
import com.proto.saludo.Holamundo.SaludoRequest;
import com.proto.saludo.Holamundo.SaludoResponse;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 9191;

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, puerto).usePlaintext().build();

        saludarUnario(channel);
        saludarStream(channel);

        System.out.println("Apagando");
        channel.shutdown();
    }

    private static void saludarUnario(ManagedChannel ch) {
        SaludoServiceGrpc.SaludoServiceBlockingStub stub = SaludoServiceGrpc.newBlockingStub(ch);
        SaludoRequest request = SaludoRequest.newBuilder().setNombre("Luis").build();
        SaludoResponse response = stub.saludo(request);

        System.out.println("Respuesta RPC: " + response.getResultado());
    }

    private static void saludarStream(ManagedChannel ch) {
        SaludoServiceGrpc.SaludoServiceBlockingStub stub = SaludoServiceGrpc.newBlockingStub(ch);
        SaludoRequest request = SaludoRequest.newBuilder().setNombre("Luis").build();

        stub.saludoStream(request).forEachRemaining(response -> {
            System.out.println("Respuesta RPC: " + response.getResultado());
        });
    }
}
