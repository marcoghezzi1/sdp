import Consegne.ManageOrderThread;
import NetworkTopology.*;
import REST.Drone;
import Consegne.DroneMqttThread;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.List;

public class DroneMain {
    public static void main(String[] args) throws InterruptedException, MqttException {
        Drone d = new Drone(5, "localhost", 1105, "localhost:1337");
        Thread mqttThread = new DroneMqttThread(d);
        d.connectToServerREST();
        Thread server = new ServerDroneThread(d);
        Thread console = new QuitDroneThread();
        Thread ping = new PingThread(d);

        //thread per l'ascolto di altri droni che entrano nella rete
        server.start();

        //thread per inserimento del quit per far uscire il drone
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

        //in caso di terminazione del thread console, esco dalla rete di droni e chiudo completamente il processo
        console.join();
        if (d.sonoMaster())
            d.disconnectFromMqtt();
        d.disconnectFromServerREST();
        System.exit(0);
    }
}
