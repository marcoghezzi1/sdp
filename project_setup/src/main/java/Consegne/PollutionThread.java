package Consegne;

import REST.Drone;
import SimulatoriProgettoSDP2021.Buffer;
import SimulatoriProgettoSDP2021.Measurement;

import java.util.List;

public class PollutionThread extends Thread {
    private Drone drone;
    private final Buffer buffer;
    private boolean stopCondition = false;

    public PollutionThread(Drone d, Buffer buffer) {
        drone = d;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(!stopCondition) {
            List<Measurement> lista = buffer.readAllAndClean();
            double totValue = 0;
            double avgMeasure;
            for (Measurement m :
                    lista) {
                //System.out.println("id measure: "+m.getId()+", value: "+m.getValue());
                totValue += m.getValue();
            }
            avgMeasure = totValue/lista.size();
            drone.addMedia(avgMeasure);
            /*for (double measure:
                    drone.getMediaMisurazioni()) {
                System.out.println("media: "+measure);
            }*/
        }
    }

    public void setStopCondition() {
        this.stopCondition = true;
    }

}
