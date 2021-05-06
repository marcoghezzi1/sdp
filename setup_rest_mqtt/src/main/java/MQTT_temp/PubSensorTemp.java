package MQTT_temp;

import org.eclipse.paho.client.mqttv3.*;

import java.util.Random;

public class PubSensorTemp {
    public static void main(String[] args) {
        MqttClient client;
        String broker = "tcp://localhost:1883";
        String clientId = MqttClient.generateClientId();
        String topic = "home/sensors/temp";
        int qos = 2;

        try {
            client = new MqttClient(broker,clientId);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            // client connection to broker
            System.out.println(clientId + " Connecting Broker " + broker);
            client.connect(connOpts);
            System.out.println(clientId + " Connected - Thread PID: " + Thread.currentThread().getId());

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection lost: " + cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    // not used in publisher only
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    if (token.isComplete())
                        System.out.println(clientId + " Message delivered, pid thread:  " + Thread.currentThread().getId());
                }
            });

            while (true) {
                Random a = new Random();
                int temperature = a.nextInt(5) + 18;
                String temp = String.valueOf(temperature);
                MqttMessage message = new MqttMessage(temp.getBytes());

                message.setQos(qos);
                System.out.println("Publishing message: temperature " + temp);

                client.publish(topic, message);
                System.out.println(clientId + " Message published - Thread PID: " + Thread.currentThread().getId());
                Thread.sleep(1000*5);
            }


            /*if (client.isConnected())
                client.disconnect();*/

        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
