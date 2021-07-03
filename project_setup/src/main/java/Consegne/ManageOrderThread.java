package Consegne;

import REST.Drone;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.grpc.DroneChattingGrpc.*;
import static com.example.grpc.DroneChattingOuterClass.*;

public class ManageOrderThread extends Thread {
    private final Drone drone;

    public ManageOrderThread(Drone d) {
        this.drone = d;
    }

    @Override
    public void run() {
        while (true) {
            if (!drone.sonoMaster()) {
                try {
                    drone.waitingToBeMaster();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            List<Order> orderList = drone.getOrdiniPendingMaster();
            synchronized (drone.getOrdiniPendingMaster()) {
                if (orderList.size() == 0 && !drone.isWantToQuit()) {
                    continue;
                } else if (orderList.size() == 0) {
                    drone.getOrdiniPendingMaster().notify();
                    break;
                }
            }
            Order order = drone.getOrder(orderList);
            int[] ritiro = order.getRitiro();
            Drone scelto = drone.chooseDrone(ritiro);
            if (scelto != null) {
                drone.removeOrder(orderList);
                String indirizzo = scelto.getIndirizzoIp()+":" + scelto.getPort();
                //System.out.println("Indirizzo: " + indirizzo);
                final ManagedChannel channel = ManagedChannelBuilder.forTarget(indirizzo).usePlaintext().build();
                DroneChattingStub stub = newStub(channel);
                OrderMessage request = OrderMessage.newBuilder()
                        .setIdOrder(order.getId())
                        .setXRitiro(order.getRitiro()[0]).setYRitiro(order.getRitiro()[1])
                        .setXConsegna(order.getConsegna()[0]).setYConsegna(order.getConsegna()[1])
                        .build();
                //System.out.println("Drone scelto: "+scelto.getId());
                scelto.setInConsegna(true);
                //System.out.println("status consegna: "+scelto.isInConsegna());
                stub.deliver(request, new StreamObserver<GlobalStats>() {
                    @Override
                    public void onNext(GlobalStats value) {
                        int[] newPosDrone = {value.getConsegna().getXConsegna(), value.getConsegna().getYConsegna()};
                        scelto.setPosizione(newPosDrone);
                        scelto.setBatteryLevel(value.getBatteryLevel());
                        System.out.println("Il drone "+scelto.getId()+" per l'ultima consegna ha percorso "+ String.format("%.2f", value.getKm())+" km. " +
                                "Ha un livello di batteria pari a "+value.getBatteryLevel()+"\n");
                        scelto.setInConsegna(false);
                        double kmPercorsi = value.getKm();
                        int batteryLeft = value.getBatteryLevel();
                        List<Double> pollutionRegistered = value.getPollutionList();
                        GlobalStatsToMaster stats = new GlobalStatsToMaster(new Timestamp(value.getTimestamp()),
                                newPosDrone, kmPercorsi, batteryLeft,
                                pollutionRegistered);
                        drone.getListGlobal().add(stats);
                        synchronized (drone.getListGlobal()) {
                            drone.getListGlobal().notify();
                        }

                    }

                    @Override
                    public void onError(Throwable throwable) {

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
            }


        }
    }


}
