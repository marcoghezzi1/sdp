package NetworkTopology;

import Consegne.GlobalStatsToMaster;
import REST.Drone;
import com.example.grpc.DroneChattingGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.List;

import static com.example.grpc.DroneChattingGrpc.*;
import static com.example.grpc.DroneChattingOuterClass.*;
import static com.example.grpc.DroneChattingOuterClass.GlobalStats.*;

public class DroneChattingImpl extends DroneChattingImplBase {
    private final Drone drone;

    public DroneChattingImpl(Drone d) {
        this.drone = d;
    }

    @Override
    public void discover(Request request, StreamObserver<Response> responseObserver) {
        System.out.println(request.getMessage() + "\nid: " + request.getId() + "\nport: " + request.getPort());
        Drone d = new Drone(request.getId(), request.getIndirizzo(), request.getPort(), drone.getIndirizzoServerREST());
        int[] posizione = {request.getPos().getX(), request.getPos().getY()};
        d.setPosizione(posizione);
        this.drone.addDroneToLocalList(d);
        //List<Drone> copy = this.drone.getDrones();
        /*if (copy != null){
                System.out.print("Lista droni: ");
            for (Drone drone :
                    copy) {
                System.out.print(" Drone id: " + drone.toString());
                        //+ "Posizione: ("+drone.getPosizione()[0]+", "+drone.getPosizione()[1]+")\n");
            }
            System.out.println();
        }

         */

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
        int batteryReceived = request.getId();
        Drone next = drone.nextDrone();
        int selfBattery = drone.getId();
        //System.out.println("id ricevuto: " +idReceived);
        System.out.println("next id: " +next.getId());
        System.out.println("tipo messaggio: " + request.getMessage());
        String indirizzo = next.getIndirizzoIp()+":"+next.getPort();
        final ManagedChannel channel = ManagedChannelBuilder.forTarget(indirizzo).usePlaintext().build();
        DroneChattingStub stub = DroneChattingGrpc.newStub(channel);
        ElectionMessage newElection = null;
        //String message = null;
        if (request.getMessage().equals("Election")) {
            //se la batteria ricevuta è maggiore, propago il messaggio
            if (batteryReceived > selfBattery ) {
                newElection = request;
                drone.setPartecipanteElezione(true);
            } else {
                //se la batteria è minore o è uguale ma il mio id è maggiore
                if (batteryReceived < selfBattery) {
                    if (!drone.isPartecipanteElezione()) {
                        newElection = ElectionMessage.newBuilder().setId(selfBattery).setMessage("Election").build();
                        drone.setPartecipanteElezione(true);
                    }
                }
                //se l'id ricevuto è il mio, mi proclamo master
                else {
                    drone.setMaster(true);
                    drone.setIdMaster(selfBattery);
                    newElection = ElectionMessage.newBuilder().setId(selfBattery).setMessage("Elected").build();
                }

            }
        }
        //altrimenti ricevo un messaggio elected
        else if (request.getMessage().equals("Elected")) {
            //setto l'id del master
            drone.setIdMaster(batteryReceived);
            //mi metto non partecipante
            drone.setPartecipanteElezione(false);
            //se non sono il master, propago il messaggio e invio la posizione al master
            if (selfBattery!=batteryReceived) {
                Thread invioPosizione = new SendPosThread(drone);
                invioPosizione.start();
                newElection = ElectionMessage.newBuilder().setId(batteryReceived).setMessage("Elected").build();

            }
            else {
                newElection = null;
            }
            responseObserver.onCompleted();

        }
        //invio il messaggio di elezione se questo non è null
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
    public void sendPos(PositionAndBattery request, StreamObserver<Message> responseObserver) {
        System.out.println("Posizione di "+request.getId()+": (" + request.getX() + ", "+request.getY()+")");
        int idDronePos = request.getId();
        int[] posizione = {request.getX(),request.getY()};
        List<Drone> drones = drone.getDrones();
        for (Drone d: drones) {
            if (d.getId()==idDronePos) {
                d.setPosizione(posizione);
                d.setBatteryLevel(request.getBattery());
            }
        }
        drone.addNumberOfPosReceived();
        responseObserver.onNext(Message.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void deliver(OrderMessage request, StreamObserver<GlobalStats> responseObserver) {
        try {
            String idOrder = request.getIdOrder();
            int xRitiro = request.getXRitiro();
            int yRitiro = request.getYRitiro();
            int xConsegna = request.getXConsegna();
            int yConsegna = request.getYConsegna();
            System.out.println("Ritiro dell'ordine "+idOrder+" a: ("+xRitiro+", "+yRitiro+"), ("
                    +xConsegna+", "+yConsegna+")");

            GlobalStatsToMaster global = drone.manageOrder(idOrder, xRitiro, yRitiro, xConsegna, yConsegna);
            long timestamp = global.getArrivo().getTime();
            int[] posConsegna = global.getPosizione();
            GlobalStats response = newBuilder()
                    .setTimestamp(timestamp)
                    .setConsegna(Consegna.newBuilder()
                            .setXConsegna(posConsegna[0])
                            .setYConsegna(posConsegna[1])
                            .build())
                    .setBatteryLevel(global.getBatteryLevel())
                    .setKm(global.getDistTot())
                    .addAllPollution(global.getAvgPollutionList())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
