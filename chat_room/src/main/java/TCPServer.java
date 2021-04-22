import java.net.*;

class TCPServer { 

	public static void main(String argv[]) throws Exception 
	{
		ServerSocket welcomeSocket = new ServerSocket(6789);

		while(true) {
			Socket connectionSocket = welcomeSocket.accept(); 

			ServerThread th = new ServerThread(connectionSocket);
			th.start();
		}
	}
}
