package NetworkTopology;

import REST.Drone;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.grpc.DroneChattingGrpc.*;
import static com.example.grpc.DroneChattingOuterClass.*;

public class SendPosThread extends Thread {
    private Drone drone;

    public SendPosThread(Drone d) {
        this.drone = d;
    }

    @Override
    public void run() {
        int [] myPos = drone.getPosizione();
        PositionAndBattery position = PositionAndBattery.newBuilder()
                .setId(drone.getId())
                .setX(myPos[0])
                .setY(myPos[1])
                .setBattery(drone.getBatteryLevel()).build();
        List<Drone> copy = drone.getDrones();
        //controllare che copy non sia vuota
        assert copy!=null;
        String indirizzo = "";
        for (Drone d :
                copy) {
            if (d.getId()==drone.getIdMaster()) {
                indirizzo = d.getIndirizzoIp()+":"+d.getPort();
                //cancellata stampa invio posizione
                //System.out.println(indirizzo);
                break;
            }
        }
        final ManagedChannel channel = ManagedChannelBuilder.forTarget(indirizzo).usePlaintext().build();
        DroneChattingStub stub = newStub(channel);
        stub.sendPos(position, new StreamObserver<Message>() {
            @Override
            public void onNext(Message value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable t) {

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
