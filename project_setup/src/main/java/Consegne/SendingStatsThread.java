package Consegne;

import REST.Drone;
import REST.beans.GlobalStat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
