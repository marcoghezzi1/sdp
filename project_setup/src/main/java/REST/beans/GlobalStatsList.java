package REST.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GlobalStatsList {

    @XmlElement (name = "Statistiche Globali")
    private List<GlobalStat> listaStatisticheRaccolte;

    private static GlobalStatsList instance;

    private GlobalStatsList() {
        listaStatisticheRaccolte = new ArrayList<>();
    }

    public GlobalStatsList(List<GlobalStat> lista) {
        listaStatisticheRaccolte = lista;
    }

    //singleton per ritornare l'istanza di GlobalStats
    public synchronized static GlobalStatsList getInstance() {
        if (instance==null)
            instance = new GlobalStatsList();
        return instance;
    }

    public synchronized void addStat(GlobalStat stat) {
        listaStatisticheRaccolte.add(stat);
    }
    public synchronized List<GlobalStat> getLista() {
        return new ArrayList<>(listaStatisticheRaccolte);
    }

    public synchronized List<GlobalStat> getLista(int n) {
        List<GlobalStat> copy = getLista();
        if (n > copy.size())
            n = copy.size();
        return new ArrayList<>(copy.subList(copy.size()-n, copy.size()));
    }

    public synchronized List<GlobalStat> getLista(String a, String b) {
        Timestamp t1 = Timestamp.valueOf(a);
        Timestamp t2 = Timestamp.valueOf(b);
        List<GlobalStat> copy = getLista();
        List<GlobalStat> sublist = new ArrayList<>();
        copy.sort(Comparator.comparing(GlobalStat::getTimestamp));
        for (GlobalStat g: copy) {
            if (g.getTimestamp().compareTo(t1)>= 0 && g.getTimestamp().compareTo(t2)<=0)
                sublist.add(g);
        }
        return sublist;
    }


}
