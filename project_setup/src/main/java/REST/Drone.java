package REST;

import REST.beans.GlobalStat;
import REST.beans.RispostaServerAdd;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.google.gson.GsonBuilder;

public class Drone {
    @Expose
    private int id;
    @Expose
    private String indirizzoIp;
    @Expose
    private int port;
    private String indirizzoServerREST;
    private int[] posizione;
    private boolean master;
    private int batteryLevel;
    private List<Drone> drones;
    private int idMaster;
    public Drone() {
    }

    public Drone(int id, String indirizzoIp, int port, String indirizzoServer) {
        this.id = id;
        this.indirizzoIp = indirizzoIp;
        this.port = port;
        this.indirizzoServerREST = indirizzoServer;
        this.batteryLevel = 100;
    }

    public Drone(int id, int port, boolean master) {
        this.id = id;
        this.port = port;
        this.master = master;
    }

    public int getIdMaster() {
        return idMaster;
    }

    public void setIdMaster(int idMaster) {
        this.idMaster = idMaster;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
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

    public String getIndirizzoServerREST() {
        return indirizzoServerREST;
    }

    public synchronized List<Drone> getDrones() {
        return drones;
    }

    public void connect() {
        Client client = Client.create();
        String serverAddress = "http://" + this.getIndirizzoServerREST();
        WebResource resource = client.resource(serverAddress+"/drone/add");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String input = gson.toJson(this);
        ClientResponse response = resource.type("application/json").post(ClientResponse.class, input);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        System.out.println("Drone " + this.id+ " aggiunto nella smart city\n");
        RispostaServerAdd output = response.getEntity(RispostaServerAdd.class);
        List<Drone> copy = output.getDronesAlreadyInCity();
        this.drones=copy;
        if (copy==null) {
            System.out.println("Nessun drone attualmente nella smart city");
        }
        else {
                System.out.print("droni attualmente nella smart city:\n");
                for (Drone d : copy) {
                    System.out.print("- Drone id: " + d.getId() + "\n\t- Indirizzo IP: " + d.getIndirizzoIp() +"\n\t- Porta: " + d.getPort());
                    System.out.println();
                }
        }
        System.out.print("Posizione assegnata al drone: ");
        int[] posizione = output.getPosizione();
        System.out.println("("+posizione[0] +", " + posizione[1]+")");
        this.setPosizione(posizione);
    }

    public void disconnect() {
        Client client = Client.create();
        String serverAddress = "http://" + this.getIndirizzoServerREST();
        WebResource resource = client.resource(serverAddress+"/drone/delete/"+this.getId());
        ClientResponse response = resource.delete(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
    }

    public synchronized void addDroneToLocalList(Drone drone) {
        if (this.getDrones() == null)
            this.drones = new ArrayList<Drone>();
        this.drones.add(drone);
        this.drones.sort(Comparator.comparing(Drone::getId));
    }

    public synchronized void removeDroneToLocalList(Drone d) {
        this.drones.remove(d);
    }

}
