package NetworkTopology;

import REST.Drone;

public class ElectionThread extends Thread {
    private Drone drone;

    public ElectionThread(Drone self) {
        this.drone = self;
    }

    @Override
    public void run() {

    }
}
