import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ServerDroneThread extends Thread{
    private int porta;

    public ServerDroneThread(int port) {
        this.porta = port;
    }

    @Override
    public void run() {
        try {
            Server server = ServerBuilder.forPort(porta).addService(new DroneChattingImpl()).build();
            server.start();
            System.out.println("Server started!");

            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}
