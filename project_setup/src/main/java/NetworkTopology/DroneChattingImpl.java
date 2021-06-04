package NetworkTopology;

import REST.Drone;
import com.example.grpc.DroneChattingGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
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
        //System.out.println(request.getMessage());
        Ping response = Ping.newBuilder().setMessage("[[PING]] " + drone.getId()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void election(ElectionMessage request, StreamObserver<ElectionMessage> responseObserver) {
        int idReceived = request.getId();
        Drone next = drone.nextDrone();
        int myId = drone.getId();
        //System.out.println("id ricevuto: " +idReceived);
        System.out.println("next id: " +next.getId());
        System.out.println("tipo messaggio: " + request.getMessage());
        String indirizzo = "localhost:"+next.getPort();
        final ManagedChannel channel = ManagedChannelBuilder.forTarget(indirizzo).usePlaintext().build();
        DroneChattingStub stub = DroneChattingGrpc.newStub(channel);
        ElectionMessage newElection = null;
        //String message = null;
        if (request.getMessage().equals("Election")) {
            if (idReceived > myId) {
                newElection = request;
                drone.setPartecipanteElezione(true);
            } else {
                if (idReceived < myId) {
                    if (!drone.isPartecipanteElezione()) {
                        newElection = ElectionMessage.newBuilder().setId(myId).setMessage("Election").build();
                        drone.setPartecipanteElezione(true);
                    }
                }
                else
                    newElection = ElectionMessage.newBuilder().setId(myId).setMessage("Elected").build();

            }
        }
        //elected
        else if (request.getMessage().equals("Elected")) {
            drone.setIdMaster(idReceived);
            drone.setPartecipanteElezione(false);
            if (myId!=idReceived) {
                newElection = ElectionMessage.newBuilder().setId(idReceived).setMessage("Elected").build();
            }
            else {
                drone.setMaster(true);
                newElection = null;
            }
        }
        if (newElection!=null)
            stub.election(newElection, new StreamObserver<ElectionMessage>() {
                @Override
                public void onNext(ElectionMessage value) {

                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onCompleted() {
                    channel.shutdown();
                }
            });


    }

    @Override
    public void sendPos(Position request, StreamObserver<Position> responseObserver) {
        super.sendPos(request, responseObserver);
    }
}
