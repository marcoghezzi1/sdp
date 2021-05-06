package MQTT_temp;

import org.eclipse.paho.client.mqttv3.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PubSubController {
    public static void main(String[] args) {
        MqttClient client;
        String broker = "tcp://localhost:1883";
        String clientId = MqttClient.generateClientId();
        String subTopic = "home/sensors/temp";
        String pubTopic = "home/controllers/temp";
        int subQos = 2;
        int pubQos = 2;

        try {
            client = new MqttClient(broker, clientId);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            System.out.println(clientId + " Connecting Broker " + broker);
            client.connect(connOpts);
            System.out.println(clientId + " Connected " + Thread.currentThread().getId());
            client.setCallback(new MqttCallback() {
                private ArrayList<Integer> temps = new ArrayList<>();
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection lost: " + cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    // leggere il messaggio
                    String receivedMessage = new String(message.getPayload());
                    int receivedTemp = Integer.parseInt(receivedMessage);

                    // mette in coda il valore
                    temps.add(receivedTemp);

                    // se coda > 5 rimuovi il primo
                    if(temps.size()>5) {
                        temps.remove(0);
                    }
                    int sum = 0;
                    double avg = 0.0;
                    for (int t:temps) {
                        sum+=t;
                    }
                    // calcola la media degli ultimi 5
                    avg = (double) sum/temps.size();
                    //temps.forEach(System.out::println);


                    System.out.println("La media è:" +avg);
                    double selectedAvg = 20.0;
                    String payload = "";
                    if(avg < selectedAvg){
                        payload = "turn on!";
                    }
                    else{
                        payload = "turn off";
                    }
                    MqttMessage messageSend = new MqttMessage(payload.getBytes());
                    message.setQos(pubQos);
                    client.publish(pubTopic, messageSend);


                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // un kaiser
                }
            });
            System.out.println(clientId + " Subscribing..." + Thread.currentThread().getId());
            client.subscribe(subTopic,subQos);
            System.out.println(clientId + " Subscribed to topics : " + subTopic);


            System.out.println("\n ***  Press a random key to exit *** \n");
            Scanner command = new Scanner(System.in);
            command.nextLine();
            client.disconnect();

        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
}
