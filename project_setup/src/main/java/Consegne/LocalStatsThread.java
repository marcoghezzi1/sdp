package Consegne;

import REST.Drone;

public class LocalStatsThread extends Thread {
    private final Drone drone;
    private boolean stopCondition = false;

    public LocalStatsThread(Drone d) {
        drone = d;
    }

    @Override
    public void run() {
        while (!stopCondition) {
            try {
                drone.printStats();
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setStopCondition() {
        this.stopCondition = true;
    }
}
