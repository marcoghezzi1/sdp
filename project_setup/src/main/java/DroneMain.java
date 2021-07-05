import Consegne.*;
import NetworkTopology.*;
import REST.Drone;
import SimulatoriProgettoSDP2021.Buffer;
import SimulatoriProgettoSDP2021.Measurement;
import SimulatoriProgettoSDP2021.PM10Simulator;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.ArrayList;
import java.util.List;

public class DroneMain {
    public static void main(String[] args) throws InterruptedException, MqttException {
        Drone d = new Drone(7, "localhost", 7777, "localhost:1337");
        Thread mqttThread = new DroneMqttThread(d);
        ServerDroneThread server = new ServerDroneThread(d);
        Thread console = new QuitDroneThread();
        PingThread ping = new PingThread(d);
        Thread manageOrders = new ManageOrderThread(d);
        SendingStatsThread sendingStats = new SendingStatsThread(d);
        LocalStatsThread printingLocalStats = new LocalStatsThread(d);

        //connessione al server rest
        d.connectToServerREST();

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

        //sistema di ping per capire l'assenza di un drone (ping a tutti)
        ping.start();

        //connessione all'mqtt
        mqttThread.start();

        //managing orders
        manageOrders.start();

        //thread per vedere le statistiche dei droni
        sendingStats.start();

        //thread per stampare
        printingLocalStats.start();


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
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                double overlapPercentage = 0.5;
                int overlap = (int) (measurements.size()* overlapPercentage);
                List<Measurement> copy = new ArrayList<>(measurements);
                if (overlap > 0)
                    measurements.subList(0, overlap).clear();
                return copy;
            }
        };
        PM10Simulator pm10 = new PM10Simulator(buffer);
        pm10.start();

        //thread per la raccolta di inquinamento
        PollutionThread pollution = new PollutionThread(d, buffer);
        pollution.start();

        while (true) {
            if ((!console.isAlive() || d.getBatteryLevel() < 15) && !d.isElectionGoing())
                d.setWantToQuit(true);
            if (d.isWantToQuit()) {
                printingLocalStats.setStopCondition();
                pm10.stopMeGently();
                pollution.setStopCondition();
                synchronized (d.getObjectDelivery()) {
                    while (d.isInConsegna()) {
                        System.out.println("Aspetto di finire la consegna");
                        d.getObjectDelivery().wait();
                    }
                }
                if (d.sonoMaster()) {
                    d.disconnectFromMqtt();
                    synchronized (d.getOrdiniPendingMaster()) {
                        if (d.getOrdiniPendingMaster().size() != 0) {
                            System.out.println("Drone in uscita: finisco di gestire tutti gli ordini rimanenti");
                            d.getOrdiniPendingMaster().wait();
                            //aspetto che tutti mandino le stats al master
                        }
                    }
                    manageOrders.join();
                    //System.out.println("Gestiti tutti gli ordini");
                    sendingStats.setStopCondition();
                    //System.out.println("Invio le statistiche al server");
                    d.sendStatsToRest();
                    synchronized (d.getListGlobal()) {
                        if (d.getListGlobal().size()!=0) {
                            //System.out.println("Aspetto di ricevere le statistiche indietro");
                            d.getListGlobal().wait();
                        }
                    }
                }
                //System.out.println("Chiudo il server di ascolto");
                server.stopCondition();
                d.disconnectFromServerREST();
                ping.setStopCondition();
                System.out.println("Drone "+d.getId()+ " uscito dalla smart-city.");
                System.exit(0);
            }
        }

    }

}
