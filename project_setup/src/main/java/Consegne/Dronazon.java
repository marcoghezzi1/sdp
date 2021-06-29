package Consegne;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Random;

public class Dronazon {
    public static void main(String[] args) throws MqttException, InterruptedException {
        MqttClient client;
        String broker = "tcp://localhost:1883";
        String clientId = MqttClient.generateClientId();
        String topic = "dronazon/smartcity/orders";
        int qos = 2;
        client = new MqttClient(broker,clientId);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        System.out.println(clientId + " Connecting Broker " + broker);
        client.connect(connOpts);
        System.out.println(clientId + " Connected - Thread PID: " + Thread.currentThread().getId());

        while (true) {
            Random rand = new Random();
            String id = "dronazon" + rand.nextInt();
            int[] ritiro = {rand.nextInt(10), rand.nextInt(10)};
            int[] consegna = {rand.nextInt(10), rand.nextInt(10)};
            Order order = new Order(id, ritiro, consegna);
            Gson gson = new Gson();
            String payload = gson.toJson(order);
            MqttMessage message = new MqttMessage(payload.getBytes());
            message.setQos(qos);
            System.out.println("messaggio pubblicato: " + payload);
            client.publish(topic, message);
            Thread.sleep(4000);
        }
    }

}
