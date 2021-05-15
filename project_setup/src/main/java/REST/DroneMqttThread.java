package REST;

import org.eclipse.paho.client.mqttv3.*;

import java.util.Scanner;

public class DroneMqttThread extends Thread {
    private Drone drone;

    public DroneMqttThread(Drone d) {
        drone = d;
    }

    public void run() {
        MqttClient client;
        String broker = "tcp://localhost:1883";
        String clientId = MqttClient.generateClientId();
        String topic = "dronazon/smartcity/orders";
        int qos = 2;
        if (drone.sonoMaster()) {
            try {
                client = new MqttClient(broker, clientId);
                MqttConnectOptions connOpt = new MqttConnectOptions();
                connOpt.setCleanSession(true);
                client.connect(connOpt);
                client.setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable cause) {
                        System.out.println(clientId + " Connectionlost! cause:" + cause.getMessage());
                    }

                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                        String receivedMessage = new String(message.getPayload());
                        System.out.println(receivedMessage);

                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {

                    }
                });
                System.out.println(clientId + " Subscribing " + Thread.currentThread().getId());
                client.subscribe(topic, qos);
                System.out.println(clientId + " Subscribed to topics : " + topic);

                System.out.println("\n ***  Press a random key to exit *** \n");
                Scanner command = new Scanner(System.in);
                command.nextLine();
                client.disconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }
}
