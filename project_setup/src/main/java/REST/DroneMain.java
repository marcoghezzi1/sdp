package REST;

import javax.xml.bind.annotation.XmlRootElement;

public class DroneMain {
    public static void main(String[] args) {
        Drone d = new Drone(18, "localhost", 19);
        Thread mqtt = new DroneMqttThread(d);
        //mqtt.start();
        d.connect();
    }
}
