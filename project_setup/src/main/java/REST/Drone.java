package REST;

import Consegne.GlobalStatsToMaster;
import Consegne.Order;
import REST.beans.GlobalStat;
import REST.beans.RispostaServerAdd;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import com.google.gson.GsonBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.eclipse.paho.client.mqttv3.*;


public class Drone {
    @Expose
    private int id;
    @Expose
    private String indirizzoIp;
    @Expose
    private int port;
    private String indirizzoServerREST;
    private int[] posizione;
    private int batteryLevel;
    private int idMaster;
    private boolean partecipanteElezione = false;
    private boolean inConsegna = false;
    @JsonIgnore
    private final Object objectDelivery = new Object();
    private List<Drone> drones = new ArrayList<>();
    private List<Double> mediaMisurazioni;
    private List<Order> ordiniPendingMaster;
    private List<GlobalStatsToMaster> listGlobal;
    private MqttClient mqttClient;
    private int posizioniRicevute;
    private int numeroConsegneEffettuate;
    private double kmPercorsi;
    private boolean wantToQuit;
    private boolean electionGoing;

    public Drone() {
    }
    public Drone(int id, String indirizzoIp, int port, String indirizzoServer) {
        this.id = id;
        this.indirizzoIp = indirizzoIp;
        this.port = port;
        this.indirizzoServerREST = indirizzoServer;
        this.batteryLevel = 100;
        this.drones = new ArrayList<>();
        this.mediaMisurazioni = new ArrayList<>();
        this.ordiniPendingMaster = new ArrayList<>();
        this.listGlobal = new ArrayList<>();
        this.posizioniRicevute = 0;
        numeroConsegneEffettuate = 0;
        kmPercorsi = 0.0;
        wantToQuit = false;
        electionGoing = false;
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
        return this.getId()==this.getIdMaster();
    }

    public int getId() {
        return id;
    }

    public Object getObjectDelivery() {
        return objectDelivery;
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

    public boolean isWantToQuit() {
        return wantToQuit;
    }

    public void setWantToQuit(boolean wantToQuit) {
        this.wantToQuit = wantToQuit;
    }

    public String getIndirizzoServerREST() {
        return indirizzoServerREST;
    }

    public synchronized List<Drone> getDrones() {
        if (drones!=null)
            return new ArrayList<>(drones);
        return null;
    }

    public boolean isPartecipanteElezione() {
        return partecipanteElezione;
    }

    public synchronized void setPartecipanteElezione(boolean partecipanteElezione) {
        this.partecipanteElezione = partecipanteElezione;
    }

    public synchronized boolean isInConsegna() {
        return inConsegna;
    }

    public synchronized void setInConsegna(boolean inConsegna) {
        this.inConsegna = inConsegna;
    }

    public synchronized void setElection(boolean b) {
        this.electionGoing = b;
    }

    public synchronized boolean isElectionGoing() {
        return electionGoing;
    }
    public int getNumeroConsegneEffettuate() {
        return numeroConsegneEffettuate;
    }

    public void setNumeroConsegneEffettuate(int numeroConsegneEffettuate) {
        this.numeroConsegneEffettuate = numeroConsegneEffettuate;
    }

    public double getKmPercorsi() {
        return kmPercorsi;
    }

    public void setKmPercorsi(double kmPercorsi) {
        this.kmPercorsi = kmPercorsi;
    }

    public void connectToServerREST() {
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
                System.out.print("Droni attualmente nella smart city:\n");
                for (Drone d : copy) {
                    System.out.print("- Drone id: " + d.getId()
                            + "\n\t- Indirizzo IP: " + d.getIndirizzoIp()
                            +"\n\t- Porta: " + d.getPort());
                    System.out.println();
                }
        }
        System.out.print("Posizione assegnata al drone: ");
        int[] posizione = output.getPosizione();
        System.out.println("("+posizione[0] +", " + posizione[1]+")");
        this.setPosizione(posizione);
    }

    public void disconnectFromServerREST() {
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
            this.drones = new ArrayList<>();
        this.drones.add(drone);
        this.drones.sort(Comparator.comparing(Drone::getId));
    }

    public synchronized void removeDroneToLocalList(Drone d) {
        System.out.println("Drone "+d.getId()+" removed");
        this.drones.remove(d);
    }

    public synchronized Drone nextDrone() {
        assert this.drones != null;
        for (Drone d: this.drones) {
            if (d.getId()>this.getId())
                return d;
        }
        if (this.drones.size()>=1)
            return this.drones.get(0);
        return null;
    }


    public synchronized Drone nextNextDrone() {
        assert this.drones != null;
        for (int i = 0; i < this.drones.size(); i++) {
            if (this.drones.get(i).getId()>this.getId())
                return this.drones.get((i+1)%this.drones.size());
        }
        return null;
    }




    public synchronized void connectToMqtt() throws MqttException, InterruptedException {
        if (this.getDrones()!=null && !this.sonoMaster()) {
            //System.out.println("[Aspettando di essere master]");
            this.wait();
        }
        System.out.println("\n[Sono master]");
        Gson gson = new Gson();
        String broker = "tcp://localhost:1883";
        String clientId = MqttClient.generateClientId();
        String topic = "dronazon/smartcity/orders";
        mqttClient = new MqttClient(broker, clientId);
        MqttConnectOptions connOpt = new MqttConnectOptions();
        connOpt.setCleanSession(true);
        mqttClient.connect(connOpt);
        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                System.out.println(clientId + " Connection lost! cause:" + cause.getMessage()+ "-  Thread PID: " + Thread.currentThread().getId());
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                String receivedMessage = new String(message.getPayload());
                Order order = gson.fromJson(receivedMessage, Order.class);
                System.out.println("\nNUOVA CONSEGNA: " +order.getId()+" da: ("+order.getRitiro()[0]+", "+order.getRitiro()[1]
                        +") a ("+order.getConsegna()[0]+", "+order.getConsegna()[1]+")\n");
                Drone.this.addOrderToQueue(order);
                /*List<Drone> copy = Drone.this.getDrones();
                if (copy!=null)
                    for (Drone d :
                            Drone.this.getDrones()) {
                        System.out.print("id: " +d.getId()+ ", battery level: "+d.getBatteryLevel()+"\n");
                    }

                 */

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
        //subscribe to broker
        mqttClient.subscribe(topic);
    }


    public void disconnectFromMqtt() throws MqttException {
        mqttClient.disconnect();
    }

    public synchronized void addOrderToQueue(Order order) {
        this.ordiniPendingMaster.add(order);
    }

    public synchronized List<Order> getOrdiniPendingMaster() {
        return ordiniPendingMaster;
    }

    public synchronized Order getOrder(List<Order> orders) {
        return orders.get(0);
    }


    public synchronized void removeOrder(List<Order> orders) {
        orders.remove(0);
    }

    public synchronized void addNumberOfPosReceived(){
        this.posizioniRicevute++;
        //System.out.println("Posizioni ricevute "+this.posizioniRicevute+"/"+this.getDrones().size());
        int len = 0;
        for (Drone d :
                this.getDrones()) {
            if (d.getId()!=d.getIdMaster())
                len++;
        }
        //System.out.println(len);
        if (len == this.posizioniRicevute) {
            this.notifyAll();
        }
    }

    public int getPosizioniRicevute() {
        return posizioniRicevute;
    }

    public synchronized void notifyIamMaster() {
        this.notifyAll();
    }

    private double getDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1), 2));
    }


    //metodo per la scelta del drone per la consegna

    public Drone chooseDrone(int[] ritiro) {
        Drone chosen = null;
        int xDrone, yDrone, xRitiro, yRitiro;
        xRitiro = ritiro[0];
        yRitiro = ritiro[1];
        double distanceMin = Double.MAX_VALUE;
        double distance;
        List<Drone> copy;
        if (this.getDrones()!=null)
            copy = new ArrayList<>(this.getDrones());
        else
            copy = new ArrayList<>();
        copy.add(this);
        copy.removeIf(d -> d.getBatteryLevel() < 15);
        copy.removeIf(Drone::isWantToQuit);
        if (copy.size()!=0)
            for (Drone toChoose :
                    copy) {
                if (toChoose.isInConsegna())
                    continue;
                xDrone = toChoose.getPosizione()[0];
                yDrone = toChoose.getPosizione()[1];
                distance = getDistance(xDrone, yDrone, xRitiro, yRitiro);
                if (distance < distanceMin) {
                    distanceMin = distance;
                    chosen = toChoose;
                }
                else if (distance == distanceMin) {
                    if (chosen!= null && chosen.getBatteryLevel() < toChoose.getBatteryLevel())
                        chosen = toChoose;
                    else if (chosen!=null && chosen.getBatteryLevel() == toChoose.getBatteryLevel()) {
                        if (chosen.getId() < toChoose.getId())
                            chosen = toChoose;
                    }
                }
            }
        return chosen;
    }
    //gestione ordine una volta ricevuto

    public GlobalStatsToMaster manageOrder(int xRitiro, int yRitiro, int xConsegna, int yConsegna) throws InterruptedException {
        this.setInConsegna(true);
        int[] posDrone = this.getPosizione();
        double distanzaDalRitiro = getDistance(posDrone[0], posDrone[1], xRitiro, yRitiro);
        double distanzaDallaConsegna = getDistance(xRitiro, yRitiro, xConsegna, yConsegna);
        double distTot = distanzaDallaConsegna+distanzaDalRitiro;
        int[] posConsegna = {xConsegna, yConsegna};
        Thread.sleep(5000);
        this.setBatteryLevel(this.getBatteryLevel()-10);
        this.numeroConsegneEffettuate++;
        this.kmPercorsi+=distTot;
        this.setPosizione(posConsegna);
        //System.out.println("posizione: "+this.getPosizione()[0]+", "+this.getPosizione()[1]);
        Timestamp arrivo = Timestamp.valueOf(LocalDateTime.now());
        GlobalStatsToMaster global = new GlobalStatsToMaster(arrivo, this.getPosizione(), distTot, this.getBatteryLevel(), this.getMediaMisurazioni());
        this.setMediaMisurazioni(new ArrayList<>());
        synchronized (this.objectDelivery) {
            this.setInConsegna(false);
            this.objectDelivery.notify();
        }
        return global;
    }
    //metodo per attendere di diventare master
    public synchronized void waitingToBeMaster() throws InterruptedException {
        this.wait();
    }
    //metodo per accedere alla lista delle statistiche da inviare al master
    public synchronized List<GlobalStatsToMaster> getListGlobal() {
        return listGlobal;
    }

    public synchronized void setListGlobal(List<GlobalStatsToMaster> listGlobal) {
        this.listGlobal = listGlobal;
    }

    //metodo per prendere la lista delle medie misurazioni
    public synchronized List<Double> getMediaMisurazioni() {
        return mediaMisurazioni;
    }

    //metodo per settare la lista delle medie misurazioni
    public synchronized void setMediaMisurazioni(List<Double> mediaMisurazioni) {
        this.mediaMisurazioni = mediaMisurazioni;
    }

    public synchronized void addMedia(double misurazione) {
        this.mediaMisurazioni.add(misurazione);
    }

    //metodo per inviare le statistiche al server rest
    public void sendStatsToRest() {
        double totKm = 0;
        double totBattery = 0;
        double totPollution = 0;
        double divisore = 0;
        for (GlobalStatsToMaster stats :
                this.getListGlobal()) {
            totKm += stats.getDistTot();
            totBattery+=stats.getBatteryLevel();
            for (double pollution :
                    stats.getAvgPollutionList()) {
                totPollution += pollution;
                divisore++;
            }
            divisore++;
        }
        int lenList = this.getListGlobal().size();
        if (lenList!=0) {
            double lenDrones;
            if (this.getDrones()!=null)
                lenDrones = this.getDrones().size()+1;
            else
                lenDrones = 1;
            Timestamp now = Timestamp.valueOf(LocalDateTime.now());
            GlobalStat statToRest = new GlobalStat(lenList / lenDrones,
                    totKm / lenList, totBattery / lenList,
                    totPollution / divisore, now);
            //creating client rest
            Client client = Client.create();
            String statistiche = "http://localhost:1337/statistiche/add";
            String input = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                    .create()
                    .toJson(statToRest);
            WebResource webResource = client.resource(statistiche);
            //System.out.println(input);
            ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
        }
        this.setListGlobal(new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", port=" + port +
                ", posizione=" + Arrays.toString(posizione) +
                '}';
    }

    public void printStats() {
        System.out.println("Numero di consegne effettuate: "+this.getNumeroConsegneEffettuate()+
                "\nKm percorsi: "+String.format("%.2f",+this.getKmPercorsi())+"\nBatteria residua: "+this.getBatteryLevel()+"\n");
    }
}
