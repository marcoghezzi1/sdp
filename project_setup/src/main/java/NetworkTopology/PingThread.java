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

public class PingThread extends Thread {
    private Drone self;
    public PingThread(Drone drone) {
        this.self = drone;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //wait();
                List<Drone> drones = self.getDrones();
                if (drones!=null && drones.size()!=0) {
                    for (Drone d: drones) {
                        String indirizzo = "";
                        indirizzo =  "localhost:" + d.getPort();
                        System.out.println("Indirizzo: " +indirizzo);
                        ManagedChannel channel = ManagedChannelBuilder.forTarget(indirizzo)
                                .usePlaintext().build();

                        DroneChattingStub stub = DroneChattingGrpc.newStub(channel);
                        Ping ping = Ping.newBuilder().build();
                        stub.ping(ping, new StreamObserver<Ping>() {
                            @Override
                            public void onNext(Ping value) {
                                System.out.println(value.getMessage());
                            }

                            @Override
                            public void onError(Throwable t) {
                                System.out.println("Canale chiuso");
                                self.removeDroneToLocalList(d);
                                if (d.getId() == self.getIdMaster()) {
                                    Thread election = new ElectionThread(self);
                                    election.start();
                                }
                            }

                            @Override
                            public void onCompleted() {
                                channel.shutdown();
                            }
                        });


                    }
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {

            }
        }
    }
}
