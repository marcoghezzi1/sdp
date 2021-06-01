import NetworkTopology.PingThread;
import NetworkTopology.QuitDroneThread;
import NetworkTopology.ServerDroneThread;
import NetworkTopology.ClientDroneThreadGRPC;
import REST.Drone;
import REST.DroneMqttThread;

import java.util.Comparator;
import java.util.List;

public class DroneMain {
    public static void main(String[] args) throws InterruptedException {
        Drone d = new Drone(2, "localhost", 2, "localhost:1337");
        Thread mqtt = new DroneMqttThread(d);
        //mqtt.start();
        d.connect();
        Thread server = new ServerDroneThread(d);
        Thread console = new QuitDroneThread();
        server.start();
        console.start();
        Thread ping = new PingThread(d);
        ping.start();
        List<Drone> copy = d.getDrones();
        if (copy!= null && copy.size()!=0) {
            for (Drone a: copy) {
                Thread client = new ClientDroneThreadGRPC(a, d);
                client.start();
            }
        }
        else {
            d.setMaster(true);
            d.setIdMaster(d.getId());
        }

        //ping.start();
        console.join();
        System.out.println("ciao");
        d.disconnect();
        System.exit(0);
    }
}
