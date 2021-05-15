package REST;

public class DroneMain {
    public static void main(String[] args) {
        Drone d = new Drone(10, "localhost", 18);
        Thread mqtt = new DroneMqttThread(d);
        //mqtt.start();
        d.connect();
    }
}
