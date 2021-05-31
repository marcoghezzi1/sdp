import NetworkTopology.QuitDroneThread;
import NetworkTopology.ServerDroneThread;
import NetworkTopology.ClientDroneThreadGRPC;
import REST.Drone;
import REST.DroneMqttThread;

import java.util.Comparator;
import java.util.List;

public class DroneMain {
    public static void main(String[] args) {
        Drone d = new Drone(15, "localhost", 2452, "localhost:1337");
        Thread mqtt = new DroneMqttThread(d);
        //mqtt.start();
        Thread server = new ServerDroneThread(d.getPort());
        Thread console = new QuitDroneThread();
        d.connect();
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
