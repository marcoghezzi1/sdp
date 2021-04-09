import java.io.*; 
import java.net.*; 

class TCPServer { 

	public static void main(String argv[]) throws Exception 
	{ 
		String clientSentence;
		String risultato;

		/* Crea una "listening socket" sulla porta specificata */
		ServerSocket welcomeSocket = new ServerSocket(6789); 

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
			String[] addendi =clientSentence.split(" ");

			int [] numbers = new int[addendi.length];

			for (int i = 0; i < addendi.length; i++) {
				numbers[i] = Integer.parseInt(addendi[i]);
			}

			int sum =0;
			for (int number : numbers) {
				sum += number;
			}
			/* Invia la risposta al client */
			outToClient.write(sum);

			connectionSocket.close();

		}
	}
}
