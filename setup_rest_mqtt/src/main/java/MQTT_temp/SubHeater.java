package MQTT_temp;

import org.eclipse.paho.client.mqttv3.*;

import java.util.Optional;
import java.util.Scanner;

public class SubHeater {
    public static void main(String[] args) {
        MqttClient client;
        String broker = "tcp://localhost:1883";
        String clientId = MqttClient.generateClientId();
        String topic = "home/controllers/temp";
        int qos = 2;

        try {
            client = new MqttClient(broker, clientId);
            MqttConnectOptions connOpt = new MqttConnectOptions();
            connOpt.setCleanSession(true);
            client.connect(connOpt);
            client.setCallback(new MqttCallback() {
                private boolean isOn = false;
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println(clientId + " Connectionlost! cause:" + cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String receivedMessage = new String(message.getPayload());

                    System.out.println("received message: "+receivedMessage);
                    if (isOn)
                        System.out.println("now heater is on");
                    else
                        System.out.println("now heater is off");


                    if (receivedMessage.contains("on") && !isOn){
                        isOn = true;
                        System.out.println("heater switched to on");
                    }

                    if(receivedMessage.contains("off") && isOn) {
                        isOn=false;
                        System.out.println("heater switched to off");
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {

                }
            });
            System.out.println(clientId + " Subscribing " + Thread.currentThread().getId());
            client.subscribe(topic,qos);
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
