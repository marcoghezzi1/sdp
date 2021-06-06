package NetworkTopology;

import REST.Drone;
import com.example.grpc.DroneChattingOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import static com.example.grpc.DroneChattingGrpc.*;
import static com.example.grpc.DroneChattingOuterClass.*;

public class ElectionThread extends Thread {
    private Drone self;
    public ElectionThread(Drone drone) {
        this.self = drone;
    }

    @Override
    public void run() {
        System.out.println("Elezione iniziata---");
        Drone next = self.nextDrone();
        if (next!=null) {
            String indirizzo = "localhost:"+next.getPort();
            final ManagedChannel channel = ManagedChannelBuilder.forTarget(indirizzo).usePlaintext().build();
            DroneChattingStub stub = newStub(channel);
            self.setPartecipanteElezione(true);
            ElectionMessage request = ElectionMessage.newBuilder().setId(self.getId()).setMessage("Election").build();
            stub.election(request, new StreamObserver<ElectionMessage>() {
                @Override
                public void onNext(ElectionMessage value) {
                    System.out.println(value.getMessage() + " " + value.getId());
                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onCompleted() {

                }
            });
        }
        else {
            self.setIdMaster(self.getId());
            self.setMaster(true);
            self.notifyIamMaster();
        }



    }
}