package REST.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StormoDroni {
    @XmlElement(name = "Stormo")
    private List<Drone> stormo;
    private static StormoDroni instance;

    private StormoDroni() {
        stormo = new ArrayList<Drone>();
    }

    //singleton per ritornare l'istanza di dizionario
    public synchronized static StormoDroni getInstance() {
        if (instance==null)
            instance = new StormoDroni();
        return instance;
    }

}
