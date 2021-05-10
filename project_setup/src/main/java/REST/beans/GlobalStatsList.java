package REST.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.*;

@XmlRootElement
public class GlobalStatsList {

    private List<GlobalStat> listaStatisticheRaccolte;

    private static GlobalStatsList instance;

    private GlobalStatsList() {
        listaStatisticheRaccolte = new ArrayList<GlobalStat>();
    }

    //singleton per ritornare l'istanza di dizionario
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

    public static void main(String[] args) {
        Timestamp t1 = new Timestamp(121, 4, 10, 19, 7, 30, 4);
        Timestamp t2 = new Timestamp(121, 4, 10, 19, 40, 30, 4);

        GlobalStat g1 = new GlobalStat(43.2, 10.4, 15, 10.9, t2);
        GlobalStat g2 = new GlobalStat(32.2, 10.4, 15, 11, t1);

        GlobalStatsList.getInstance().addStat(g1);
        GlobalStatsList.getInstance().addStat(g2);

        List<GlobalStat> copy = getInstance().getLista();
        copy.sort(Comparator.comparing(GlobalStat::getTimestamp));
        for (GlobalStat g: copy) {
            System.out.println(g.toString());
        }

    }


}
