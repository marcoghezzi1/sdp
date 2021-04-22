import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

class TCPClient2 {
	public static void main(String argv[]) throws Exception { 

		/* Inizializza l'input stream (da tastiera) */
		BufferedReader inFromUser = 
			new BufferedReader(new InputStreamReader(System.in)); 

		/* Inizializza una socket client, connessa al server */
		Socket clientSocket = new Socket("localhost", 6789);
		/* Inizializza lo stream di output verso la socket */
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		Exam a = new Exam(18, "architettura", "2030");
		Exam b = new Exam(20, "sisop", "2009");
		ArrayList<Exam> esami = new ArrayList<Exam>();
		esami.add(a);
		esami.add(b);
		Student marco = new Student("Matteo", "Limoncini", "Tursela", 1998, esami);
		Gson g = new Gson();
		String json = g.toJson(marco);
		/* Inizializza lo stream di input dalla socket */
		BufferedReader inFromServer =
				new BufferedReader(new
						InputStreamReader(clientSocket.getInputStream()));

		/* Invia la linea al server */
		//String s = inFromUser.readLine();
		outToServer.writeBytes(json + '\n');

		/* Legge la risposta inviata dal server (linea terminata da \n) */
		//modifiedSentence = inFromServer.readLine();

		clientSocket.close();
	}
}
