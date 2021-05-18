package REST;

public class DroneMain {
    public static void main(String[] args) {
        Drone d = new Drone(7, "localhost", 19);
        Thread mqtt = new DroneMqttThread(d);
        //mqtt.start();
        d.connect();
    }
}
