import java.io.*; 
import java.net.*; 

class University {

	public static void main(String argv[]) throws Exception 
	{ 
		String clientSentence; 
		String capitalizedSentence;
		String serverSentence;

		/* Crea una "listening socket" sulla porta specificata */
		ServerSocket welcomeSocket = new ServerSocket(6789);
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(System.in));

		while(true) { 
			/* 
			 * Viene chiamata accept (bloccante). 
			 * All'arrivo di una nuova connessione crea una nuova
			 * "established socket"
			 */
			Socket connectionSocket = welcomeSocket.accept(); 

			/* Inizializza lo stream di input dalla socket */
			BufferedReader inFromClient = 
				new BufferedReader(new
						InputStreamReader(connectionSocket.getInputStream())); 

			/* Inizializza lo stream di output verso la socket */
			DataOutputStream  outToClient = 
				new DataOutputStream(connectionSocket.getOutputStream()); 

			/* Legge una linea (terminata da \n) dal client */
			clientSentence = inFromClient.readLine();
			serverSentence = inFromServer.readLine();
			System.out.println(clientSentence);
			/* Invia la risposta al client */
			outToClient.writeBytes(serverSentence);


			connectionSocket.close();
		}
	}
}
