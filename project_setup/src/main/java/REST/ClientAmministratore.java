package REST;

import REST.beans.*;
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
            Statistiche stats;
            String t1;
            String t2;
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
                    webResource = client.resource(statistiche+"/" + n);

                    response = webResource.accept("application/json")
                            .get(ClientResponse.class);
                    if (response.getStatus() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + response.getStatus());
                    }
                    output = response.getEntity(String.class);
                    GlobalStatsList list = gson.fromJson(output, GlobalStatsList.class);
                    //List<GlobalStat> copy_stats = list.getLista();
                    System.out.println("Le ultime " +n+" statistiche sono:");
                    System.out.println(output.getClass());
                    break;
                case 3:
                    scan.nextLine();
                    System.out.print("Data inizio: ");
                    t1 = scan.nextLine();
                    System.out.print("Data fine: ");
                    t2 = scan.nextLine();
                    webResource = client.resource(statistiche+"/avg/delivery").queryParam("t1", t1).queryParam("t2", t2);
                    System.out.println(webResource);
                    response = webResource.accept("application/json")
                            .get(ClientResponse.class);
                    if (response.getStatus() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + response.getStatus());
                    }
                    output = response.getEntity(String.class);
                    //stats = gson.fromJson(output, Statistiche.class);
                    System.out.println("Media delle consegne:" + output);
                    System.out.println(output);
                    break;
                case 4:
                    scan.nextLine();
                    System.out.print("Data inizio: ");
                    t1 = scan.nextLine();
                    System.out.print("Data fine: ");
                    t2 = scan.nextLine();
                    webResource = client.resource(statistiche+"/avg/km").queryParam("t1", t1).queryParam("t2", t2);
                    response = webResource.accept("application/json")
                            .get(ClientResponse.class);
                    if (response.getStatus() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + response.getStatus());
                    }
                    output = response.getEntity(String.class);
                    //stats = gson.fromJson(output, Statistiche.class);
                    System.out.println("Media dei chilometri:" +output);
                    System.out.println(output);
                    break;
            }
            i++;
        }
    }
}
