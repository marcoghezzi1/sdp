package REST.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Drone {
    private int id;
    private String indirizzoIP;
    private int port;

    public Drone(int id, String indirizzoIP, int port) {
        this.id = id;
        this.indirizzoIP = indirizzoIP;
        this.port = port;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndirizzoIP() {
        return indirizzoIP;
    }

    public void setIndirizzoIP(String indirizzoIP) {
        this.indirizzoIP = indirizzoIP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
