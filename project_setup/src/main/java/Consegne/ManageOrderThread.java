package Consegne;

import REST.Drone;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.grpc.DroneChattingGrpc.*;
import static com.example.grpc.DroneChattingOuterClass.*;

public class ManageOrderThread extends Thread {
    private Drone drone;

    public ManageOrderThread(Drone d) {
        this.drone = d;
    }

    @Override
    public void run() {
        while (true) {
            if (!drone.sonoMaster()) {
                try {
                    this.waitingToBeMaster();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            List<Order> copy = drone.getOrdiniPendingMaster();
            if (copy == null)
                continue;
            if (copy.size() == 0)
                continue;
            Order order = drone.getAndRemoveOrder(copy);
            int[] ritiro = order.getRitiro();
            Drone scelto = drone.chooseDrone(ritiro);
            String indirizzo = "localhost:" + scelto.getPort();
            System.out.println("Indirizzo: " + indirizzo);
            final ManagedChannel channel = ManagedChannelBuilder.forTarget(indirizzo).usePlaintext().build();
            DroneChattingStub stub = newStub(channel);
            OrderMessage request = OrderMessage.newBuilder()
                    .setIdOrder(order.getId())
                    .setXRitiro(order.getRitiro()[0]).setYRitiro(order.getRitiro()[1])
                    .setXConsegna(order.getConsegna()[0]).setYConsegna(order.getConsegna()[1])
                    .build();
            scelto.setInConsegna(true);
            stub.deliver(request, new StreamObserver<OrderMessage>() {
                @Override
                public void onNext(OrderMessage value) {

                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onCompleted() {

                }
            });
            try {
                channel.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private synchronized void waitingToBeMaster() throws InterruptedException {
        this.wait();
    }
}
