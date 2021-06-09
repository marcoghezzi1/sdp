package Consegne;

import java.sql.Timestamp;

public class GlobalStatsToSend {

    private Timestamp arrivo;
    private int[] posizione;
    private double distTot;
    private int batteryLevel;

    public GlobalStatsToSend(Timestamp arrivo, int[] posizione, double distTot, int batteryLevel) {
        this.arrivo = arrivo;
        this.posizione = posizione;
        this.distTot = distTot;
        this.batteryLevel = batteryLevel;
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
}
