package Concurrent;

import java.io.*;
import java.net.*;

class TCPMultiServer {
    public static void main(String argv[]) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while(true) {
            Socket connectionSocket = welcomeSocket.accept();

            TCPServerThread theThread = new TCPServerThread(connectionSocket);

            theThread.start();
        }
    }
}
