package Calculator;

import io.grpc.ServerBuilder;
import io.grpc.Server;

import java.io.IOException;

public class CalculatorServer {
    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(9999).addService(new CalculatorServiceImpl()).build();
        try {
            server.start();
            System.out.println("Server started!");
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
