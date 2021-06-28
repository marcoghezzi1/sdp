package REST.beans;

import REST.Drone;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Comparator;
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
        if (dronesAlreadyInCity!=null)
            dronesAlreadyInCity.sort(Comparator.comparing(Drone::getId));
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
