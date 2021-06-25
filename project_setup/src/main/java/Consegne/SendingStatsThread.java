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
    private Drone drone;

    public SendingStatsThread(Drone d) {
        drone = d;
    }

    @Override
    public void run() {
        while(true) {
            try {

                double totKm = 0;
                double totBattery = 0;
                double totPollution = 0;
                for (GlobalStatsToMaster stats :
                        drone.getListGlobal()) {
                    /*System.out.println("batteria rimanente: "+stats.getBatteryLevel()+"\nkm percorsi: "+stats.getDistTot()
                            +"\ntimestamp: "+stats.getArrivo().toString());*/
                    totKm += stats.getDistTot();
                    totBattery+=stats.getBatteryLevel();
                    for (double pollution :
                            stats.getAvgPollutionList()) {
                        totPollution += pollution;
                    }
                }
                int lenList = drone.getListGlobal().size();
                if (lenList!=0) {
                    double lenDrones;
                    if (drone.getDrones()!=null)
                        lenDrones = drone.getDrones().size()+1;
                    else
                        lenDrones = 1;
                    Timestamp now = Timestamp.valueOf(LocalDateTime.now());
                    GlobalStat statToRest = new GlobalStat(lenList / lenDrones,
                            totKm / lenList, totBattery / lenList,
                            totPollution / lenList, now);
                    //creating client rest
                    Client client = Client.create();
                    //System.out.println(now);
                    String statistiche = "http://localhost:1337/statistiche/add";
                    String input = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                            .create()
                            .toJson(statToRest);
                    WebResource webResource = client.resource(statistiche);
                    System.out.println(input);
                    ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
                    if (response.getStatus() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + response.getStatus());
                    }
                }
                drone.setListGlobal(new ArrayList<>());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
