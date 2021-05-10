package REST.beans;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class RispostaServerAdd {
    private List<Drone> dronesAlreadyInCity;
    private int[] posizione;

    public RispostaServerAdd() {

    }

    public RispostaServerAdd(List<Drone> dronesAlreadyInCity, int[] posizione) {
        this.dronesAlreadyInCity = dronesAlreadyInCity;
        this.posizione = posizione;
    }

    public List<Drone> getDronesAlreadyInCity() {
        return dronesAlreadyInCity;
    }

    public void setDronesAlreadyInCity(List<Drone> dronesAlreadyInCity) {
        this.dronesAlreadyInCity = dronesAlreadyInCity;
    }

    public int[] getPosizione() {
        return posizione;
    }

    public void setPosizione(int[] posizione) {
        this.posizione = posizione;
    }
}
