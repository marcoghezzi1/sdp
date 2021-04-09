import java.io.*; 
import java.net.*;

class TCPClient { 
	public static void main(String argv[]) throws Exception {
		String strFromServer;

		/* Inizializza una socket client, connessa al server */
		Socket clientSocket = new Socket("localhost", 6789);
		System.out.println("client connesso");

		/* Inizializza lo stream di input dalla socket */
		BufferedReader inFromServer = 
			new BufferedReader(new
					InputStreamReader(clientSocket.getInputStream()));


		/* Legge la risposta inviata dal server (linea terminata da \n) */
		strFromServer = inFromServer.readLine();

		System.out.println("FROM SERVER: " + strFromServer);

		clientSocket.close(); 
	}
}
