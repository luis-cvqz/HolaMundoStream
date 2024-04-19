package grpcholamundostream.Servidor;

import com.proto.saludo.SaludoServiceGrpc;
import com.proto.saludo.Holamundo.SaludoRequest;
import com.proto.saludo.Holamundo.SaludoResponse;

import io.grpc.stub.StreamObserver;

public class ServidorImpl extends SaludoServiceGrpc.SaludoServiceImplBase {
    @Override
    public void saludo(SaludoRequest saludoRequest, StreamObserver<SaludoResponse> responseStreamObserver) {
        SaludoResponse saludoResponse = SaludoResponse.newBuilder().setResultado("Hola " + saludoRequest.getNombre()).build();
        responseStreamObserver.onNext(saludoResponse);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void saludoStream(SaludoRequest request, StreamObserver<SaludoResponse> responseStreamObserver) {
        for (int i = 0; i <= 10; i++) {
            SaludoResponse saludoResponse = SaludoResponse.newBuilder()
                    .setResultado("Hola " + request.getNombre() + " por " + i + "° vez.").build();

            responseStreamObserver.onNext(saludoResponse);
        }
        responseStreamObserver.onCompleted();
    }
}
