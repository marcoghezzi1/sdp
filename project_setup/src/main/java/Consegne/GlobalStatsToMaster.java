package Consegne;

import java.sql.Timestamp;


public class GlobalStatsToMaster {

    private Timestamp arrivo;
    private int[] posizione;
    private double distTot;
    private int batteryLevel;
    private double avgPollution;

    public GlobalStatsToMaster() {
    }

    public GlobalStatsToMaster(Timestamp arrivo, int[] posizione, double distTot, int batteryLevel, double pollution) {
        this.arrivo = arrivo;
        this.posizione = posizione;
        this.distTot = distTot;
        this.batteryLevel = batteryLevel;
        this.avgPollution = pollution;
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

    public double getAvgPollution() {
        return avgPollution;
    }
}
