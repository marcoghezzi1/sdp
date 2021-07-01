package NetworkTopology;

import REST.Drone;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ServerDroneThread extends Thread{
    private final int porta;
    private final Drone drone;
    private Server server;

    public ServerDroneThread(Drone d) {
        this.porta = d.getPort();
        this.drone = d;
    }

    @Override
    public void run() {
        try {
            //System.out.println(this.porta);
            server = ServerBuilder.forPort(porta).addService(new DroneChattingImpl(this.drone)).build();
            server.start();
            System.out.println();

            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }


    }

    public void stopCondition() {
        server.shutdownNow();
    }
}
