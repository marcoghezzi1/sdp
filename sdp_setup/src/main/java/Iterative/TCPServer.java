package Iterative;

import java.io.*;
import java.net.*;
import java.util.Arrays;

class TCPServer {

	public static void main(String argv[]) throws Exception
	{
		//int port_number = Integer.parseInt(argv[0]);


		/* Crea una "listening socket" sulla porta specificata */
		ServerSocket welcomeSocket = new ServerSocket(6789);

		while(true) {
			/*
			 * Viene chiamata accept (bloccante).
			 * All'arrivo di una nuova connessione crea una nuova
			 * "established socket"
			 */
			Socket connectionSocket = welcomeSocket.accept();

			InetAddress client_address = connectionSocket.getInetAddress();
			int client_port = connectionSocket.getPort();
			System.out.println("client address: "+client_address.toString());
			System.out.println("client port: "+client_port);

			/* Inizializza lo stream di input dalla socket */
			BufferedReader inFromClient =
					new BufferedReader(new
							InputStreamReader(connectionSocket.getInputStream()));

			/* Inizializza lo stream di output verso la socket */
			DataOutputStream  outToClient =
					new DataOutputStream(connectionSocket.getOutputStream());

			/* Legge una linea (terminata da \n) dal client */
			String fromclient = inFromClient.readLine();

			System.out.println("waiting...");
			Thread.sleep(10000);
			String[] addendi =fromclient.split(" ");

			int [] numbers = new int[addendi.length];

			for (int i = 0; i < addendi.length; i++) {
				numbers[i] = Integer.parseInt(addendi[i]);
			}

			int sum =0;
			for (int i = 0; i < numbers.length; i++) {
				sum += numbers[i];
			}

			/* Invia la risposta al client */
			outToClient.write(sum);

			connectionSocket.close();

		}
	}
}