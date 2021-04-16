import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket connectionSocket = null;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;
    private String stampa;
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
        try {
            Gson gs = new Gson();
            Student st = gs.fromJson(inFromClient.readLine(), Student.class);
            stampa = st.getName() + " " + st.getSurname() + ". Nato nel "+ st.getYear_of_birth() +
                    ", residente a " + st.getPlace_of_residence() +
                    "\nEsami passati: ";
            System.out.println(stampa);
            for (Exam e: st.getEsami_passati()) {
                System.out.println("\t" + e.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
