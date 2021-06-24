import Consegne.ManageOrderThread;
import Consegne.SendingStatsThread;
import NetworkTopology.*;
import REST.Drone;
import Consegne.DroneMqttThread;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.List;

public class DroneMain {
    public static void main(String[] args) throws InterruptedException, MqttException {
        Drone d = new Drone(2, "localhost", 2, "localhost:1337");
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
            d.setMaster(true);
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
        Thread sendingStats = new SendingStatsThread(d);
        sendingStats.start();

        //in caso di terminazione del thread console, esco dalla rete di droni e chiudo completamente il processo
        //console.join();
        //battery.join();
        //System.out.println("server di ascolto chiuso, consegne terminate");
        while (true) {
            if (!console.isAlive() || d.getBatteryLevel() < 15)
                d.setWantToQuit(true);
            if (d.isWantToQuit()) {
                if (d.sonoMaster()) {
                    d.disconnectFromMqtt();
                    synchronized (d.getOrdiniPendingMaster()) {
                        if (d.getOrdiniPendingMaster().size() != 0)
                            System.out.println("Waiting to manage all orders");
                            d.getOrdiniPendingMaster().wait();
                    }
                    manageOrders.interrupt();
                }
                while (d.isInConsegna())
                    assert true;
                //System.out.println("In consegna drone: "+ d.isInConsegna());
                d.disconnectFromServerREST();
                //server.interrupt();
                ping.interrupt();
                System.exit(0);
            }
        }

    }

}
