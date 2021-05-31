package NetworkTopology;

import REST.Drone;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.List;

public class ServerDroneThread extends Thread{
    private final int porta;
    private final List<Drone> drones;
    private final Drone drone;

    public ServerDroneThread(Drone d) {
        this.porta = d.getPort();
        this.drones = d.getDrones();
        this.drone = d;
    }

    @Override
    public void run() {
        try {
            //System.out.println(this.porta);
            Server server = ServerBuilder.forPort(porta).addService(new DroneChattingImpl(this.drones)).build();
            server.start();
            System.out.println("Server started!");

            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}