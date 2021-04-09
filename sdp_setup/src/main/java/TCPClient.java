import java.io.*; 
import java.net.*;

class TCPClient { 
	public static void main(String argv[]) throws Exception { 
		String sentence; 
		int risultato;
		String a;
		String b;

		/* Inizializza l'input stream (da tastiera) */
		BufferedReader inFromUser = 
			new BufferedReader(new InputStreamReader(System.in));

		String address = argv[0];
		int port = Integer.parseInt(argv[1]);

		/* Inizializza una socket client, connessa al server */
		Socket clientSocket = new Socket(address, port);

		/* Inizializza lo stream di output verso la socket */
		DataOutputStream outToServer = 
			new DataOutputStream(clientSocket.getOutputStream()); 

		/* Inizializza lo stream di input dalla socket */
		BufferedReader inFromServer = 
			new BufferedReader(new
					InputStreamReader(clientSocket.getInputStream())); 

		/* Legge una linea da tastiera */
		System.out.println("Inserisci il primo addendo: ");
		a = inFromUser.readLine();
		System.out.println("Inserisci il secondo addendo: ");
		b = inFromUser.readLine();

		/* Invia la linea al server */
		outToServer.writeBytes(a + ' ' + b + '\n');

		/* Legge la risposta inviata dal server (linea terminata da \n) */
		risultato = inFromServer.read();

		System.out.println("FROM SERVER: " + risultato);

		clientSocket.close(); 
	}
}
