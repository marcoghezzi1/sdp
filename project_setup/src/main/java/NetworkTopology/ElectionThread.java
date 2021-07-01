package NetworkTopology;

import REST.Drone;
import com.example.grpc.DroneChattingOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

import static com.example.grpc.DroneChattingGrpc.*;
import static com.example.grpc.DroneChattingOuterClass.*;

public class ElectionThread extends Thread {
    private Drone self;
    public ElectionThread(Drone drone) {
        this.self = drone;
    }

    @Override
    public void run() {
        if (!self.isElectionGoing()) {
            self.setElection(true);
            System.out.println("---Elezione iniziata---");
            Drone next = self.nextDrone();
            if (next != null) {
                System.out.println("sending message to " + next.getId());
                String indirizzo = next.getIndirizzoIp() + ":" + next.getPort();
                final ManagedChannel channel = ManagedChannelBuilder.forTarget(indirizzo).usePlaintext().build();
                DroneChattingStub stub = newStub(channel);
                self.setPartecipanteElezione(true);
                ElectionMessage request = ElectionMessage.newBuilder()
                        .setBattery(self.getBatteryLevel())
                        .setId(self.getId())
                        .setMessage("Election").build();
                stub.election(request, new StreamObserver<ElectionMessage>() {
                    @Override
                    public void onNext(ElectionMessage value) {
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
            } else {
                self.setIdMaster(self.getId());
                self.notifyIamMaster();
            }
            self.setElection(false);
            System.out.println("Status: "+self.isElectionGoing());
        }


    }
}
