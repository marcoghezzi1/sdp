package REST;

import REST.beans.Drone;
import REST.beans.GlobalStatsList;
import REST.beans.StormoDroni;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Scanner;

@XmlRootElement
public class ClientAmministratore {
    public static void main(String[] args) {
        Client client = Client.create();
        int i = 0;
        while (true) {
            if (i == 0)
                System.out.print("Benvenuto! ");
            System.out.println("Cosa desideri fare? \n " +
                    "-elenco droni (1)\n " +
                    "-ultime n statistiche (2)\n " +
                    "-media consegne (3)\n " +
                    "-media chilometri (4)\n " +
                    "-esci (5)");
            Scanner scan = new Scanner(System.in);
            int risposta = scan.nextInt();
            String drone = "http://localhost:1337/drone";
            String statistiche = "http://localhost:1337/statistiche";
            WebResource webResource;
            ClientResponse response;
            String output;
            Gson gson = new Gson();
            if (risposta==5)
                break;
            switch (risposta) {
                case 1:
                    webResource = client.resource(drone);
                    response = webResource.accept("application/json")
                            .get(ClientResponse.class);
                    if (response.getStatus() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + response.getStatus());
                    }
                    output = response.getEntity(String.class);
                    StormoDroni droni = gson.fromJson(output, StormoDroni.class);
                    List<Drone> copy = droni.getStormo();
                    System.out.println("I droni nella smart city sono:");
                    for (Drone d: copy) {
                        System.out.println("Drone: " + d.getId() + "\n\t -indirizzo ip: " + d.getIndirizzoIp() +
                                "\n\t -porta: "+ d.getPort());
                    }

                    break;
                case 2:
                    System.out.println("Inserisci n: ");
                    int n = scan.nextInt();
                    webResource = client.resource(statistiche+n);
                    response = webResource.accept("application/json")
                            .get(ClientResponse.class);
                    if (response.getStatus() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + response.getStatus());
                    }
                    output = response.getEntity(String.class);
                    GlobalStatsList list = gson.fromJson(output, GlobalStatsList.class);
                    System.out.println("Le ultime " +n+" statistiche sono:");
                    System.out.println(list.toString());
                    break;
                case 3:
                    webResource = client.resource(statistiche+"avg/delivery");
                    response = webResource.accept("application/json")
                            .get(ClientResponse.class);
                    if (response.getStatus() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + response.getStatus());
                    }
                    output = response.getEntity(String.class);
                    System.out.println("Media delle consegne:");
                    System.out.println(output);
                    break;
                case 4:
                    webResource = client.resource(statistiche+"avg/km");
                    response = webResource.accept("application/json")
                            .get(ClientResponse.class);
                    if (response.getStatus() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + response.getStatus());
                    }
                    output = response.getEntity(String.class);
                    System.out.println("Media dei chilometri:");
                    System.out.println(output);
                    break;
            }
            i++;
        }
    }
}
