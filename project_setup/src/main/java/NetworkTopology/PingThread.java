package NetworkTopology;

import Consegne.DroneMqttThread;
import REST.Drone;
import com.example.grpc.DroneChattingGrpc;
import com.example.grpc.DroneChattingOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.grpc.DroneChattingGrpc.*;
import static com.example.grpc.DroneChattingOuterClass.*;


// ping a tutti i droni o solo al master?

public class PingThread extends Thread {
    private Drone self;
    private boolean stopCondition = false;
    public PingThread(Drone drone) {
        this.self = drone;
    }
    //ping a tutti

    @Override
    public void run() {
        try {
            while (!stopCondition) {
                List<Drone> drones = self.getDrones();
                if (drones!= null && drones.size() != 0) {
                    for (Drone d : drones) {

                        String indirizzo = "";

                        indirizzo = d.getIndirizzoIp()+":" + d.getPort();
                        //System.out.println("Indirizzo: " +indirizzo);
                        ManagedChannel channel = ManagedChannelBuilder.forTarget(indirizzo)
                                .usePlaintext().build();

                        DroneChattingStub stub = DroneChattingGrpc.newStub(channel);
                        Ping ping = Ping.newBuilder().build();
                        stub.ping(ping, new StreamObserver<Ping>() {
                            @Override
                            public void onNext(Ping value) {
                                //System.out.println(value.getMessage());
                            }

                            @Override
                            public void onError(Throwable t) {
                                System.out.println("Canale chiuso");
                                self.removeDroneToLocalList(d);
                                if (d.getId() == self.getIdMaster()) {
                                    Thread election = new ElectionThread(self);
                                    election.start();
                                }
                                channel.shutdown();
                            }

                            @Override
                            public void onCompleted() {
                                channel.shutdown();
                            }
                        });
                    }
                    Thread.sleep(2000);

                }

            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setStopCondition() {
        stopCondition = true;
    }
}
