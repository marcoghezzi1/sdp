package REST.beans;

import REST.Drone;
import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


public class StormoDroni {
    //@XmlElement(name = "Stormo")
    @Expose
    private List<Drone> stormo;
    private static StormoDroni instance;

    private StormoDroni() {
        stormo = new ArrayList<Drone>();
    }

    public synchronized List<Drone> getStormo(){
        return new ArrayList<>(stormo);
    }

    public synchronized void add(Drone d) {
        stormo.add(d);
    }


    //singleton per ritornare l'istanza di dizionario
    public synchronized static StormoDroni getInstance() {
        if (instance==null)
            instance = new StormoDroni();
        return instance;
    }

    public synchronized int checkDrone(Drone d) {
        List<Drone> copy = getStormo();
        for (int i = 0; i < copy.size(); i++) {
            Drone check = copy.get(i);
            if (d.getId() == check.getId())
                return i;
        }
        return -1;
    }

    public synchronized void deleteDrone(int id) {
        for (int i = 0; i < stormo.size(); i++) {
            if (stormo.get(i).getId() == id)
                stormo.remove(i);
        }
    }

}
