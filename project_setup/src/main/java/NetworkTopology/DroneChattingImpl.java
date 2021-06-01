package NetworkTopology;

import REST.Drone;
import io.grpc.stub.StreamObserver;

import java.util.List;

import static com.example.grpc.DroneChattingGrpc.*;
import static com.example.grpc.DroneChattingOuterClass.*;

public class DroneChattingImpl extends DroneChattingImplBase {
    private Drone drone;

    public DroneChattingImpl(Drone d) {
        this.drone = d;
    }

    @Override
    public void discover(Request request, StreamObserver<Response> responseObserver) {
        System.out.println(request.getMessage() + "\nid: " + request.getId() + "\nport: " + request.getPort());
        Drone d = new Drone(request.getId(), request.getPort(), request.getMaster());
        int[] posizione = {request.getPos().getX(), request.getPos().getY()};
        //System.out.println(posizione[0] + posizione[1]);
        d.setPosizione(posizione);
        this.drone.addDroneToLocalList(d);
        //notifyAll();
        List<Drone> copy = this.drone.getDrones();
        if (copy != null){
                System.out.print("Lista droni: ");
            for (Drone drone :
                    copy) {
                System.out.print(" Drone id: " + drone.getId());
            }
            System.out.println();
        }

        Response response = Response.newBuilder().setIdMaster(this.drone.getIdMaster()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void ping(Ping request, StreamObserver<Ping> responseObserver) {
        System.out.println(request.getMessage());
        Ping response = Ping.newBuilder().setMessage("[[PING]] " + drone.getId()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
