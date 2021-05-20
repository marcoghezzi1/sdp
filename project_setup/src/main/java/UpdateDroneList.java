import REST.Drone;
import com.example.grpc.DroneChattingGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;


import java.util.concurrent.TimeUnit;

import static com.example.grpc.DroneChattingGrpc.*;
import static com.example.grpc.DroneChattingOuterClass.*;

public class UpdateDroneList extends Thread{
    private Drone otherDrones;
    private Drone io;
    public UpdateDroneList(Drone altro, Drone drone) {
        this.otherDrones = altro;
        this.io = drone;
    }

    @Override
    public void run() {
        //plaintext channel on the address (ip/port) which offers the GreetingService service
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:"+ otherDrones.getPort()).usePlaintext().build();

        //creating an asynchronous stub on the channel
        DroneChattingStub stub = DroneChattingGrpc.newStub(channel);

        String message = "A cojone mi sono connesso!";

        //creating the HelloResponse object which will be provided as input to the RPC method
        Request request = Request.newBuilder().setMessage(message)
                .setPort(io.getPort())
                .setId(io.getId())
                .build();
        stub.chatting(request, new StreamObserver<Response>() {
            @Override
            public void onNext(Response value) {
                System.out.println(value.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Error! "+t.getMessage());
            }

            @Override
            public void onCompleted() {
                channel.shutdown();
            }
        });
        try {
            channel.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
