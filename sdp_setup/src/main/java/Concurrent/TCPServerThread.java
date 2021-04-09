package Concurrent;

import java.io.*;
import java.net.*;

public class TCPServerThread extends Thread {

    private Socket connectionSocket = null;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;

    public TCPServerThread(Socket s) {
        connectionSocket = s;
        try {
            inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        String fromclient;
        String capitalizedSentence;
        try {
            fromclient = inFromClient.readLine();
            System.out.println("waiting...");
            Thread.sleep(10000);
            String[] addendi = fromclient.split(" ");

            int[] numbers = new int[addendi.length];

            for (int i = 0; i < addendi.length; i++) {
                numbers[i] = Integer.parseInt(addendi[i]);
            }

            int sum = 0;
            for (int i = 0; i < numbers.length; i++) {
                sum += numbers[i];
            }

            /* Invia la risposta al client */
            outToClient.write(sum);

            connectionSocket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
	}

}