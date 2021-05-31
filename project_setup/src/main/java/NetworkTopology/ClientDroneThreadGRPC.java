package NetworkTopology;

import REST.Drone;
import com.example.grpc.DroneChattingGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;


import java.util.concurrent.TimeUnit;

import static com.example.grpc.DroneChattingGrpc.*;
import static com.example.grpc.DroneChattingOuterClass.*;

public class ClientDroneThreadGRPC extends Thread{
    private final Drone otherDrones;
    private final Drone self;
    public ClientDroneThreadGRPC(Drone other, Drone drone) {
        this.otherDrones = other;
        this.self = drone;
    }

    @Override
    public void run() {
        String indirizzo = otherDrones.getIndirizzoIp()+":"+otherDrones.getPort();
        //plaintext channel on the address (ip/port) which offers the GreetingService service
        final ManagedChannel channel = ManagedChannelBuilder.forTarget(indirizzo).usePlaintext().build();

        //creating an asynchronous stub on the channel
        DroneChattingStub stub = DroneChattingGrpc.newStub(channel);

        String message = "A cojone mi sono connesso!";

        //creating the HelloResponse object which will be provided as input to the RPC method
        Request request = Request.newBuilder().setMessage(message)
                .setPort(self.getPort())
                .setId(self.getId())
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
