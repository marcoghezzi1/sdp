package REST.beans;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Drone {
    private int id;
    private String indirizzoIp;
    private int port;
    private String posizione;
    private List<Drone> drones;
    public Drone() {

    }

    public Drone(int id, String indirizzoIp, int port) {
        this.id = id;
        this.indirizzoIp = indirizzoIp;
        this.port = port;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndirizzoIP() {
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

    public void setPosizione(String posizione) {
        this.posizione = posizione;
    }
}
