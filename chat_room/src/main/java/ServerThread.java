import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread{
    Socket connectionSocket = null;
    BufferedReader inFromClient;
    DataOutputStream outToClient;
    String clientSentence;
    public ServerThread(Socket s) {
        connectionSocket = s;
        try {
            inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        while(true) {
            /* Legge una linea (terminata da \n) dal client */
            try {
                clientSentence = inFromClient.readLine();
                System.out.println(clientSentence);
                /* Invia la risposta al client */
                outToClient.writeBytes(clientSentence + "\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
