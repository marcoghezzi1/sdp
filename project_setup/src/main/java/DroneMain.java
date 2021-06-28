import Consegne.ManageOrderThread;
import Consegne.PollutionThread;
import Consegne.SendingStatsThread;
import NetworkTopology.*;
import REST.Drone;
import Consegne.DroneMqttThread;
import SimulatoriProgettoSDP2021.Buffer;
import SimulatoriProgettoSDP2021.Measurement;
import SimulatoriProgettoSDP2021.PM10Simulator;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.ArrayList;
import java.util.List;

public class DroneMain {
    public static void main(String[] args) throws InterruptedException, MqttException {
        Drone d = new Drone(6, "localhost", 6, "localhost:1337");
        Thread mqttThread = new DroneMqttThread(d);
        d.connectToServerREST();
        Thread server = new ServerDroneThread(d);
        Thread console = new QuitDroneThread();
        Thread ping = new PingThread(d);

        //thread per l'ascolto di altri droni che entrano nella rete
        server.start();

        //thread per inserimento del quit per far uscire il drone e della batteria
        console.start();

        //presentazione a tutti gli altri droni
        List<Drone> copy = d.getDrones();
        if (copy!= null && copy.size()!=0) {
            for (Drone a: copy) {
                Thread client = new ClientDroneThreadGRPC(a, d);
                client.start();
            }
        }
        //mi auto-proclamo master se la lista è vuota
        else {
            d.setIdMaster(d.getId());
        }

        //sistema di ping per capire l'assenza di un drone
        ping.start();

        //inizio a gestire gli ordini
        mqttThread.start();

        //managing orders
        Thread manageOrders = new ManageOrderThread(d);
        manageOrders.start();

        //thread per vedere le statistiche dei droni
        SendingStatsThread sendingStats = new SendingStatsThread(d);
        sendingStats.start();



        //thread PM10 simulator
        Buffer buffer = new Buffer() {
            private final List<Measurement> measurements = new ArrayList<>();

            @Override
            public synchronized void addMeasurement(Measurement m) {
                measurements.add(m);
                if (measurements.size()==8)
                    this.notify();
            }

            @Override
            public synchronized List<Measurement> readAllAndClean() {
                try {
                    //System.out.println("Waiting to receive 8 measures");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println("Statistiche ricevute");
                double overlapPercentage = 0.5;
                int overlap = (int) (measurements.size()* overlapPercentage);
                List<Measurement> copy = new ArrayList<>(measurements);
                if (overlap > 0)
                    measurements.subList(0, overlap).clear();
                return copy;
            }
        };
        Thread pm10 = new PM10Simulator(buffer);
        pm10.start();

        //thread per la raccolta di inquinamento
        Thread pollution = new PollutionThread(d, buffer);
        pollution.start();

        while (true) {
            if (!console.isAlive() || d.getBatteryLevel() < 15)
                d.setWantToQuit(true);
            if (d.isWantToQuit()) {
                if (d.sonoMaster()) {
                    d.disconnectFromMqtt();
                    synchronized (d.getOrdiniPendingMaster()) {
                        if (d.getOrdiniPendingMaster().size() != 0) {
                            System.out.println("Waiting to manage all orders");
                            d.getOrdiniPendingMaster().wait();
                            manageOrders.join();
                        }
                    }
                }
                while (d.isInConsegna())
                    assert true;
                System.out.println("I'm out");
                sendingStats.setStopCondition();
                System.out.println("Invio le statistiche al server");
                d.sendStatsToRest();
                d.disconnectFromServerREST();
                ping.interrupt();
                System.exit(0);
            }
        }

    }

}
