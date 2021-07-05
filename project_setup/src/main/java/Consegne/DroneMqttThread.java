package Consegne;

import REST.Drone;
import org.eclipse.paho.client.mqttv3.*;


public class DroneMqttThread extends Thread {
    private final Drone drone;

    public DroneMqttThread(Drone d) {
        drone = d;
    }

    public void run() {
        try {
            drone.connectToMqtt();
        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
