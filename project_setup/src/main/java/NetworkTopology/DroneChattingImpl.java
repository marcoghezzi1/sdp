package NetworkTopology;

import Consegne.GlobalStatsToMaster;
import REST.Drone;
import com.example.grpc.DroneChattingGrpc;
import io.grpc.Context;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
        System.out.println("\nNuovo drone inserito\nid: " + request.getId() + "\nport: " + request.getPort());
        Drone d = new Drone(request.getId(), request.getIndirizzo(), request.getPort(), drone.getIndirizzoServerREST());
        int[] posizione = {request.getPos().getX(), request.getPos().getY()};
        d.setPosizione(posizione);
        this.drone.addDroneToLocalList(d);

        Response response = Response.newBuilder().setIdMaster(this.drone.getIdMaster()).setElectionGoing(this.drone.isElectionGoing()).build();
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
        if (!drone.isElectionGoing()) {
            drone.setElection(true);
            //System.out.println("Elezione iniziata una volta ricevuto \nun messaggio di election da un altro drone");
        }/*
        else
            //System.out.println("Stavo gi?? facendo l'elezione");*/
        int batteryReceived = request.getBattery();
        int idReceived = request.getId();
        Drone next = drone.nextDrone();
        Drone nextNext = drone.nextNextDrone();
        //System.out.println("next: "+next.getId());
        /*if (next.nextDrone()!=null)
            System.out.println("next next: "+next.nextDrone().getId());*/
        int selfBattery = drone.getBatteryLevel();
        int selfId = drone.getId();
/*
        System.out.println("id ricevuto: " +idReceived);
        System.out.println("next id: " +next.getId());
        System.out.println("tipo messaggio: " + request.getMessage());

 */

        String indirizzo = next.getIndirizzoIp()+":"+next.getPort();
        Context.current().fork().run(() -> {
            ElectionMessage newElection = null;
            //String message = null;
    /*
    ricevo un messaggio di election:
    - se non sono partecipante:
        - mi segno partecipante
        - se la batteria ricevuta ?? maggiore:
            - propago il messaggio
        - se la batteria ricevuta ?? minore:
            - cambio il messaggio inserendo il mio id e la mia batteria
        - se la batteria ricevuta ?? uguale alla mia:
            - se l'id ricevuto ?? minore
                - cambio il messaggio inserendo il mio id e la mia batteria
            - se l'id ricevuto ?? maggiore
                - propago il messaggio
    - se sono partecipante:
        - se la batteria ricevuta ?? maggiore:
            - propago il messaggio
        - se la batteria ricevuta ?? minore:
            - non propago
        - se la batteria ?? uguale:
            - se l'id ricevuto ?? uguale:
                - propago un messaggio di elected
            - se l'id ricevuto ?? minore:
                - non propago il messaggio
            - se l'id ricevuto ?? maggiore:
                - propago il messaggio cos?? com'??
     */


            if (request.getMessage().equals("Election")) {
                //se la batteria ricevuta ?? maggiore, propago il messaggio
                if (batteryReceived > selfBattery ) {
                    newElection = request;
                    drone.setPartecipanteElezione(true);
                }
                if (!drone.isPartecipanteElezione()) {
                    drone.setPartecipanteElezione(true);
                    if (batteryReceived < selfBattery) {
                        newElection = ElectionMessage.newBuilder()
                                .setId(selfId)
                                .setBattery(selfBattery)
                                .setMessage("Election").build();
                    }
                    if (batteryReceived == selfBattery) {
                        if (idReceived < selfId) {
                            newElection = ElectionMessage.newBuilder()
                                    .setId(selfId)
                                    .setBattery(selfBattery)
                                    .setMessage("Election").build();
                        }
                        if (idReceived > selfId)
                            newElection = request;
                    }
                }
                //se sono partecipante
                else {
                    if (batteryReceived < selfBattery)
                        //non propago il messaggio
                        newElection = null;
                    if (batteryReceived == selfBattery) {
                        if (idReceived == selfId) {
                            newElection = ElectionMessage.newBuilder()
                                    .setId(selfId)
                                    .setMessage("Elected").build();
                            //se ricevo un messaggio di election con il mio id, posso rimuovere il master dalla mia lista
                            for(Drone d: drone.getDrones())
                                if (d.getId()==drone.getIdMaster())
                                    drone.removeDroneToLocalList(d);
                            drone.setIdMaster(selfId);
                            drone.setPartecipanteElezione(false);
                        }
                        if (idReceived < selfId) {
                            newElection = null;
                        }
                        if (idReceived > selfId)
                            newElection = request;
                    }
                }


            }
            //altrimenti ricevo un messaggio elected
            else if (request.getMessage().equals("Elected")) {
                //mi metto non partecipante
                drone.setPartecipanteElezione(false);
                //se non sono il master, propago il messaggio e invio la posizione al master
                if (selfId!=idReceived) {
                    Thread invioPosizione = new SendPosThread(drone);
                    invioPosizione.start();
                    //setto l'id del master
                    drone.setIdMaster(idReceived);
                    newElection = ElectionMessage.newBuilder().setId(idReceived).setMessage("Elected").build();
                }
                else {
                    newElection = null;
                }
                //System.out.println("Sono uscito dall'elezione");
                drone.setElection(false);

            }



            if (newElection!=null) {
                final ManagedChannel channel = ManagedChannelBuilder.forTarget(indirizzo).usePlaintext().build();
                DroneChattingStub stub = DroneChattingGrpc.newStub(channel);
                ElectionMessage finalNewElection = newElection;
                stub.election(newElection, new StreamObserver<ElectionMessage>() {
                    @Override
                    public void onNext(ElectionMessage value) {
                        System.out.println(value);
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (nextNext!=null) {
                            String indirizzo = nextNext.getIndirizzoIp() + ":" + nextNext.getPort();
                            //System.out.println(indirizzo);
                            final ManagedChannel channel = ManagedChannelBuilder.forTarget(indirizzo).usePlaintext().build();
                            DroneChattingStub stub = DroneChattingGrpc.newStub(channel);
                            stub.election(finalNewElection, new StreamObserver<ElectionMessage>() {
                                @Override
                                public void onNext(ElectionMessage value) {
                                    System.out.println(value);
                                }

                                @Override
                                public void onError(Throwable t) {

                                }

                                @Override
                                public void onCompleted() {
                                    //System.out.println("Canale chiuso con " + indirizzo);
                                    channel.shutdown();
                                }
                            });

                        }
                        channel.shutdown();
                    }

                    @Override
                    public void onCompleted() {
                        //System.out.println("Canale chiuso con " +indirizzo);
                        channel.shutdown();
                    }
                });
                try {
                    channel.awaitTermination(10, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //extracted common code in if-else
            responseObserver.onNext(ElectionMessage.newBuilder().build());
            responseObserver.onCompleted();

        });

    }

    @Override
    public void sendPos(PositionAndBattery request, StreamObserver<Message> responseObserver) {
        //System.out.println("Posizione di "+request.getId()+": (" + request.getX() + ", "+request.getY()+")");
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
            int xRitiro = request.getXRitiro();
            int yRitiro = request.getYRitiro();
            int xConsegna = request.getXConsegna();
            int yConsegna = request.getYConsegna();
            /*System.out.println("Ritiro dell'ordine "+idOrder+" da ("+xRitiro+", "+yRitiro+"), a ("
                    +xConsegna+", "+yConsegna+")");*/


            GlobalStatsToMaster global = drone.manageOrder(xRitiro, yRitiro, xConsegna, yConsegna);
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
            //System.out.println("Batteria rimasta: " +drone.getBatteryLevel()+"\n");
            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
