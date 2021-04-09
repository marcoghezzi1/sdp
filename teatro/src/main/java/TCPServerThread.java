import java.io.*; 
import java.net.*; 

public class TCPServerThread extends Thread{

	private Socket connectionSocket = null;
	private BufferedReader inFromClient;
	private DataOutputStream outToClient;
	private Reservations obj;

	public TCPServerThread(Socket s) {
		connectionSocket = s;
		try {
			inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public TCPServerThread(Socket s, Reservations obj) {
		this.obj = obj;
		connectionSocket = s;
		try {
			inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		String stringToClient ="";
		try {
			int res = this.obj.check_seats();
			System.out.println("prova prova prova");
			if (res < 0){
				stringToClient = "biglietti esauriti!";
			}
			else{
				obj.prenota_biglietto();

				stringToClient = "biglietto prenotato! rimangono ancora "+res+" biglietti";
			}

			outToClient.writeBytes(stringToClient);
			connectionSocket.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}