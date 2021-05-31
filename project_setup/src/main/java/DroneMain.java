import NetworkTopology.QuitDroneThread;
import NetworkTopology.ServerDroneThread;
import NetworkTopology.ClientDroneThreadGRPC;
import REST.Drone;
import REST.DroneMqttThread;

import java.util.Comparator;
import java.util.List;

public class DroneMain {
    public static void main(String[] args) {
        Drone d = new Drone(54, "localhost", 2476, "localhost:1337");
        Thread mqtt = new DroneMqttThread(d);
        //mqtt.start();
        d.connect();
        Thread server = new ServerDroneThread(d);
        Thread console = new QuitDroneThread();
        server.start();
        console.start();
        List<Drone> copy = d.getDrones();
        if (copy!= null && copy.size()!=0) {
            for (Drone a: copy) {
                Thread client = new ClientDroneThreadGRPC(a, d);
                client.start();
            }
        }
        else
            d.setMaster(true);
    }
}
