package NetworkTopology;

import REST.Drone;
import com.example.grpc.DroneChattingGrpc;
import com.example.grpc.DroneChattingOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.List;

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
        Position position = Position.newBuilder()
                .setId(drone.getId())
                .setX(myPos[0])
                .setY(myPos[1]).build();
        List<Drone> copy = drone.getDrones();
        //controllare che copy non sia vuota
        assert copy!=null;
        String indirizzo = "";
        for (Drone d :
                copy) {
            if (d.getId()==drone.getIdMaster()) {
                indirizzo = "localhost:"+d.getPort();
                //System.out.println(indirizzo);
                break;
            }
        }
        final ManagedChannel channel = ManagedChannelBuilder.forTarget(indirizzo).usePlaintext().build();
        DroneChattingStub stub = newStub(channel);
        stub.sendPos(position, new StreamObserver<Position>() {
            @Override
            public void onNext(Position value) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }
}
