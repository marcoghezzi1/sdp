package REST;

import REST.beans.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Scanner;

@XmlRootElement
public class ClientAmministratore {
    public static void main(String[] args) {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        clientConfig.getClasses().add(JacksonJsonProvider.class);
        Client client = Client.create(clientConfig);
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
            StormoDroni output;
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
                    output = response.getEntity(StormoDroni.class);
                    List<Drone> copy = output.getStormo();
                    if (copy.size() != 0 ) {
                        System.out.println("I droni nella smart city sono:");
                        for (Drone d : copy) {
                            System.out.println("Drone: " + d.getId() + "\n\t -indirizzo ip: " + d.getIndirizzoIp() +
                                    "\n\t -porta: " + d.getPort());
                        }
                    }
                    else
                        System.out.println("Nessun drone presente nella smart city");

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
                    List<GlobalStat> globalStat = response.getEntity(new GenericType<List<GlobalStat>>() {});
                    if (globalStat.size()==0)
                        System.out.println("Nessuna statistica");
                    else {
                        System.out.println("Le ultime " + n + " statistiche sono:");
                        for (GlobalStat g : globalStat) {
                            System.out.println("Media consegne: " + String.format("%.2f", +g.getAvgDelivery()) + ", media km: "
                                    + String.format("%.2f", +g.getAvgKm()) + ", media batteria rimanente: " + g.getAvgBatteryLife()
                                    + ", media inquinamento: " + String.format("%.2f", +g.getAvgPollution()) + ", timestamp: " + g.getTimestamp());
                        }
                    }
                    break;
                case 3:
                    scan.nextLine();
                    System.out.print("Data inizio: ");
                    t1 = scan.nextLine();
                    System.out.print("Data fine: ");
                    t2 = scan.nextLine();
                    webResource = client.resource(statistiche+"/avg/delivery").queryParam("t1", t1).queryParam("t2", t2);
                    //System.out.println(webResource);
                    response = webResource.accept("application/json")
                            .get(ClientResponse.class);
                    if (response.getStatus() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + response.getStatus());
                    }
                    stats = response.getEntity(Statistiche.class);
                    if (Double.isNaN(stats.getMedia()))
                        System.out.println("Nessuna statistica");
                    else
                        System.out.println("Media delle consegne effettuate: " +String.format("%.2f",+stats.getMedia()));
                    //System.out.println(output);
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
                    stats = response.getEntity(Statistiche.class);
                    if (Double.isNaN(stats.getMedia()))
                        System.out.println("Nessuna statistica");
                    else
                        System.out.println("Media dei chilometri percorsi: " +String.format("%.2f",stats.getMedia()) + " km");
                    break;
            }
            i++;
            System.out.println();
        }
    }

}
