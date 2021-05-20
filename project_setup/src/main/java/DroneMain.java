import REST.Drone;
import REST.DroneMqttThread;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

public class DroneMain {
    public static void main(String[] args) {
        Drone d = new Drone(13, "localhost", 6);
        Thread mqtt = new DroneMqttThread(d);
        //mqtt.start();
        Thread server = new ServerDroneThread(d.getPort());
        server.start();
        d.connect();
        List<Drone> copy = d.getDrones();
        if (copy!= null && copy.size()!=0) {
            for (Drone a: copy) {
                Thread client = new UpdateDroneList(a, d);
                client.start();
            }
        }
    }
}
