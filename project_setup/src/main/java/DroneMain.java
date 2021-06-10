import Consegne.ManageOrderThread;
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

        /*TODO
        ping di tutti solo al master, appena questo thread muore, si fa partire una nuova elezione.
        Finita questa ho un nuovo master a cui devo mandare sia la posizione che il livello di batteria rimanente
         */

        Thread manageOrders = new ManageOrderThread(d);
        manageOrders.start();

        //in caso di terminazione del thread console, esco dalla rete di droni e chiudo completamente il processo
        //console.join();
        //battery.join();
        //System.out.println("server di ascolto chiuso, consegne terminate");
        quitting(d, console);

    }

    private static void quitting(Drone d, Thread console) throws MqttException {
        while (true) {
            if (!console.isAlive() || d.getBatteryLevel() < 15)
                d.setWantToQuit(true);
            if (d.isWantToQuit()) {
                if (d.sonoMaster()) {
                    d.disconnectFromMqtt();
                    while (d.getOrdiniPendingMaster().size()!=0);
                }
                while (d.isInConsegna());
                d.disconnectFromServerREST();
                System.exit(0);
            }
        }
    }
}
