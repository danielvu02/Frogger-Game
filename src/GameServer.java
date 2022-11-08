import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

	public static void main(String[] args) throws IOException {
		final String carString = "CAR";
		final String logString = "LOG";	
		final String frogString = "FROG";
		
		Object[][] array = new Object[3][];
		array[0] = new Car[3];
		for (int i=0; i<array[0].length; i++) {
			array[0][i] = new Car();
		}

		
		array[1] = new Log[3];
		for (int i=0; i<array[1].length; i++) {
			array[1][i] = new Log();
		}
		
		array[2] = new Frog[1];
		
		//declare listening port and start listening 
		final int SOCKET_PORT = 5556;
		ServerSocket server = new ServerSocket(SOCKET_PORT);
		System.out.println("Server listening for connections on port " + SOCKET_PORT);
		
		while(true) {
			Socket s = server.accept();
			System.out.println("Client connected");
			
			//spawn a thread to process the command received
			GameService myService = new GameService(s, array);
			Thread t = new Thread(myService);
			t.start();
		}
		
		
		
		
	}
	
	

}