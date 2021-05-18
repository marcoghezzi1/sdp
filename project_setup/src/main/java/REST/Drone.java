package REST;

import REST.beans.RispostaServerAdd;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jackson.map.annotate.JsonRootName;

import javax.xml.bind.annotation.*;
import java.util.List;
import com.google.gson.GsonBuilder;
public class Drone {
    @Expose
    private int id;
    @Expose
    private String indirizzoIp;
    @Expose
    private int port;
    private int[] posizione;
    private boolean master;
    private List<Drone> drones;
    public Drone() {
    }

    public Drone(int id, String indirizzoIp, int port) {
        this.id = id;
        this.indirizzoIp = indirizzoIp;
        this.port = port;
    }

    public boolean sonoMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndirizzoIp() {
        return indirizzoIp;
    }

    public void setIndirizzoIp(String indirizzoIp) {
        this.indirizzoIp = indirizzoIp;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int[] getPosizione() {
        return posizione;
    }

    public void setPosizione(int[] posizione) {
        this.posizione = posizione;
    }

    public void connect() {
        Client client = Client.create();
        WebResource resource = client.resource("http://localhost:1337/drone/add");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String input = gson.toJson(this);
        ClientResponse response = resource.type("application/json").post(ClientResponse.class, input);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        System.out.println("Output from Server .... \n");
        String output = response.getEntity(String.class);
        Gson gson1 = new Gson();
        RispostaServerAdd risposta = gson1.fromJson(output, RispostaServerAdd.class);
        System.out.println("input \n " + input);
        System.out.println(output);
        System.out.println(risposta.getDronesAlreadyInCity());
        int[] posizione = risposta.getPosizione();
        System.out.println(posizione[0] +" " + posizione[1]);
    }

}
