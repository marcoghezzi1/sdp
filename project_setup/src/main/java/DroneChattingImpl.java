import io.grpc.stub.StreamObserver;
import static com.example.grpc.DroneChattingGrpc.*;
import static com.example.grpc.DroneChattingOuterClass.*;

public class DroneChattingImpl extends DroneChattingImplBase {
    //@Override
    /*public void chatting(Hello request, StreamObserver<Hello> responseObserver) {
        System.out.println(request);
        Hello response = Hello.newBuilder().setMessage("prova").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }*/

    @Override
    public void chatting(Request request, StreamObserver<Response> responseObserver) {
        System.out.println(request);
        Response response = Response.newBuilder().setMessage("prova").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
