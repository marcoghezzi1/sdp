package NetworkTopology;


import REST.Drone;
import com.example.grpc.DroneChattingGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.List;


import static com.example.grpc.DroneChattingGrpc.*;
import static com.example.grpc.DroneChattingOuterClass.*;



public class PingThread extends Thread {
    private Drone self;
    private boolean stopCondition = false;
    public PingThread(Drone drone) {
        this.self = drone;
    }

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
                    Thread.sleep(3000);

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
