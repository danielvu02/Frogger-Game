import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {

	public static void main(String[] args) throws IOException {
		
		//set up a communication channel
		final int SOCKET_PORT = 5556;
		Socket s = new Socket("localhost", SOCKET_PORT);
		
		//initialize the data stream to send the data out
		OutputStream outStream = s.getOutputStream();
		PrintWriter out = new PrintWriter(outStream);
		
		//initialize a data stream to receive the data sent back
		InputStream inStream = s.getInputStream();
		Scanner in = new Scanner(inStream);
		
		//get the frog location (x,y)
		String command = "GETFROG\n";
		System.out.println("SENDING: " + command);
		out.println(command);
		out.flush();
		String response = in.nextLine();
		System.out.println("Receiving: " + response);
	
		//get the first car in the first car array location (x,y)
		command = "GETCARS 1 1\n"; 
		System.out.println("SENDING: " + command);
		out.println(command);
		out.flush();
		response = in.nextLine();
		System.out.println("Receiving: " + response);
		
		//get the first log in the first log array location (x,y)
		command = "GETLOGS 1 1 \n"; 
		System.out.println("SENDING: " + command);
		out.println(command);
		out.flush();
		response = in.nextLine();
		System.out.println("Receiving: " + response);
		
		//set the frog position (x, y)
		command = "UPDATEFROG 123 256\n";
		System.out.println("SENDING: " + command);
		out.println(command);
		out.flush();
		response = in.nextLine();
		System.out.println("Receiving: " + response);
		
		//finish send the commands
		command = "QUIT\n";
		System.out.println("SENDING: " + command);
		out.println(command);
		out.flush();
		
		//close connection
		s.close();
	}
}
