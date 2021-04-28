package Calculator;

import com.example.grpc.CalculatorServiceGrpc;
import com.example.grpc.CalculatorServiceOuterClass;
import io.grpc.stub.StreamObserver;

import static com.example.grpc.CalculatorServiceGrpc.*;
import static com.example.grpc.CalculatorServiceOuterClass.*;

public class CalculatorServiceImpl extends CalculatorServiceImplBase {
    @Override
    public void simpleSum(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println(request);
        HelloResponse response = HelloResponse.newBuilder().setSum(request.getA()+request.getB()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void repeatedSum(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println(request);
        int n = request.getA();
        int t = request.getB();
        int sum = 0;
        for (int i = 0; i < t; i++) {
            sum += n;
            HelloResponse response = HelloResponse.newBuilder().setSum(sum).build();
            responseObserver.onNext(response);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        responseObserver.onCompleted();

    }

    @Override
    public StreamObserver<HelloRequest> streamSum(StreamObserver<HelloResponse> responseObserver) {
        return new StreamObserver<HelloRequest>() {
            @Override
            public void onNext(HelloRequest request) {
                System.out.println(request);
                int n = request.getA();
                int t = request.getB();
                responseObserver.onNext(HelloResponse.newBuilder().setSum(n+t).build());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Error! "+ t.getMessage());
            }

            @Override
            public void onCompleted() {

            }
        };
    }
}
