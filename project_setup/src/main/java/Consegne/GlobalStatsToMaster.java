package Consegne;

import java.sql.Timestamp;
import java.util.List;


public class GlobalStatsToMaster {

    private Timestamp arrivo;
    private int[] posizione;
    private double distTot;
    private int batteryLevel;
    private List<Double> avgPollutionList;

    public GlobalStatsToMaster() {
    }

    public GlobalStatsToMaster(Timestamp arrivo, int[] posizione, double distTot, int batteryLevel, List<Double> pollution) {
        this.arrivo = arrivo;
        this.posizione = posizione;
        this.distTot = distTot;
        this.batteryLevel = batteryLevel;
        this.avgPollutionList = pollution;
    }

    public Timestamp getArrivo() {
        return arrivo;
    }

    public int[] getPosizione() {
        return posizione;
    }

    public double getDistTot() {
        return distTot;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public List<Double> getAvgPollutionList() {
        return avgPollutionList;
    }
}
