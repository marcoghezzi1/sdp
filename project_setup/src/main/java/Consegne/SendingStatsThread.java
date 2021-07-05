package Consegne;

import REST.Drone;

public class SendingStatsThread extends Thread {
    private final Drone drone;
    private boolean stopCondition = false;

    public SendingStatsThread(Drone d) {
        drone = d;
    }

    @Override
    public void run() {
        while(!stopCondition) {
            try {
                if (!drone.sonoMaster()) {
                    drone.waitingToBeMaster();
                }
                //System.out.println("Sending stats");
                drone.sendStatsToRest();
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void setStopCondition() {
        stopCondition = true;
    }

}
