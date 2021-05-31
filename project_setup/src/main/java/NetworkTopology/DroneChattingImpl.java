package NetworkTopology;

import REST.Drone;
import REST.beans.StormoDroni;
import io.grpc.stub.StreamObserver;

import java.util.List;

import static com.example.grpc.DroneChattingGrpc.*;
import static com.example.grpc.DroneChattingOuterClass.*;

public class DroneChattingImpl extends DroneChattingImplBase {
    private List<Drone> drones;

    public DroneChattingImpl(List<Drone> drones) {
        this.drones = drones;
    }

    @Override
    public void discover(Request request, StreamObserver<Response> responseObserver) {
        System.out.println(request);
        Drone d = new Drone(request.getId(), request.getPort(), request.getMaster());
        this.drones.add(d);
        /*
        if (this.drones!=null)
            for (Drone drone :
                    drones) {
                System.out.println(drone.getId());
            }
         */
        Response response = Response.newBuilder().setMessage("prova").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
