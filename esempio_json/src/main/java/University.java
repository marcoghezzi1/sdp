import java.io.*; 
import java.net.*; 

class University {
	public static void main(String argv[]) throws Exception {
		ServerSocket server = new ServerSocket(6789);

		while(true) {
			Socket connection = server.accept();
			ServerThread th = new ServerThread(connection);
			th.start();
		}
	}

}
