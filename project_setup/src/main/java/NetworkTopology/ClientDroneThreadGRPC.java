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
        final ManagedChannel channel = ManagedChannelBuilder.forTarget(indirizzo).usePlaintext().build();


        DroneChattingStub stub = DroneChattingGrpc.newStub(channel);

        String message = "Nuovo drone inserito";


        Request request = Request.newBuilder().setMessage(message)
                .setPort(self.getPort())
                .setId(self.getId())
                .setPos(Request.Posizione.newBuilder()
                        .setX(self.getPosizione()[0])
                        .setY(self.getPosizione()[1]).build())
                .setIndirizzo(self.getIndirizzoIp())
                .build();
        stub.discover(request, new StreamObserver<Response>() {
            @Override
            public void onNext(Response value) {
                System.out.println("Comunicazione da " +indirizzo+ "\nL'id del master: "
                        + value.getIdMaster()+"\nSta partecipando all'elezione: "+value.getElectionGoing());
                if (!value.getElectionGoing())
                    self.setIdMaster(value.getIdMaster());
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
