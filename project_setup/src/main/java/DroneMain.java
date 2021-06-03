import NetworkTopology.PingThread;
import NetworkTopology.QuitDroneThread;
import NetworkTopology.ServerDroneThread;
import NetworkTopology.ClientDroneThreadGRPC;
import REST.Drone;
import Consegne.DroneMqttThread;

import java.util.List;

public class DroneMain {
    public static void main(String[] args) throws InterruptedException {
        Drone d = new Drone(15, "localhost", 15, "localhost:1337");
        Thread mqtt = new DroneMqttThread(d);
        //mqtt.start();
        d.connect();
        Thread server = new ServerDroneThread(d);
        Thread console = new QuitDroneThread();
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
        Thread ping = new PingThread(d);
        ping.start();

        //in caso di terminazione del thread console, esco dalla rete di droni e chiudo completamente il processo
        console.join();
        //System.out.println("ciao");
        d.disconnect();
        System.exit(0);
    }
}
