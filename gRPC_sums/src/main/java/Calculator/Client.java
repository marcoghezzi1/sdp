package Calculator;

import com.example.grpc.CalculatorServiceGrpc;
import com.example.grpc.CalculatorServiceGrpc.*;
import com.example.grpc.CalculatorServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static com.example.grpc.CalculatorServiceOuterClass.*;

public class Client {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Cosa vuoi fare?");
        String choice = scan.nextLine();
        switch (choice) {
            case "repeated" -> repeated();
            case "simple" -> simple();
            case "stream" -> stream();
        }
    }

    private static void simple() throws InterruptedException {
        System.out.println("Inserisci primo intero:");
        int a = scan.nextInt();
        System.out.println("Inserisci secondo intero:");
        int b = scan.nextInt();
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:9999").usePlaintext().build();
        CalculatorServiceStub stub = CalculatorServiceGrpc.newStub(channel);
        HelloRequest request = HelloRequest
                .newBuilder()
                .setA(a)
                .setB(b).build();

        stub.simpleSum(request, new StreamObserver<HelloResponse>() {
            public void onNext(HelloResponse value) {
                System.out.println(value.getSum());
            }
            public void onError(Throwable t) {
                System.out.println("Error! "+ t.getMessage());
            }

            public void onCompleted() {
                channel.shutdown();
            }
        });

        channel.awaitTermination(10, TimeUnit.SECONDS);
    }

    private static void repeated() throws InterruptedException {
        System.out.println("Inserisci primo intero:");
        int n = scan.nextInt();
        System.out.println("Inserisci secondo intero:");
        int t = scan.nextInt();
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:9999").usePlaintext().build();
        CalculatorServiceStub stub = CalculatorServiceGrpc.newStub(channel);
        HelloRequest request = HelloRequest
                .newBuilder()
                .setA(n)
                .setB(t).build();
        stub.repeatedSum(request, new StreamObserver<HelloResponse>() {
            @Override
            public void onNext(HelloResponse value) {
                System.out.println("Somma: " + value.getSum());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Error! "+ t.getMessage());
            }

            @Override
            public void onCompleted() {
                channel.shutdown();
            }
        });
        channel.awaitTermination(10, TimeUnit.SECONDS);
    }
    private static void stream() throws InterruptedException {
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:9999").usePlaintext().build();
        CalculatorServiceStub stub = CalculatorServiceGrpc.newStub(channel);
        StreamObserver<HelloRequest> streamServer = stub.streamSum(new StreamObserver<HelloResponse>() {
            @Override
            public void onNext(HelloResponse value) {
                System.out.println("Somma: "+ value.getSum());
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onCompleted() {
            }
        });
        while (true) {
            System.out.print("a:");
            int n = scan.nextInt();
            System.out.print("b:");
            int t = scan.nextInt();

            HelloRequest request = HelloRequest.newBuilder().setA(n).setB(t).build();
            streamServer.onNext(request);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
