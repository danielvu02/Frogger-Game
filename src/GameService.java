import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameService implements Runnable{
	private Socket s;
	private Object array[][];
	
	private PrintWriter out;
	private Scanner in;
	
	public GameService() {
		super();
	}

	public GameService(Socket s, Object[][] array) {
		super();
		this.s = s;
		this.array = array;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			in = new Scanner(s.getInputStream());
			out = new PrintWriter(s.getOutputStream());
			processRequest();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
			s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void processRequest() {
		//need a loop to process commands as they are retrieved one at a time
		while (true) {
			if (!in.hasNext()) {
				return;
			}
			
			//process first command
			String command = in.next();
			System.out.println("Command received: " + command);
			
			if(command.equals("QUIT")) {
				return;
			} else {
				executeCommand(command);
			}
		}
	}
	
	public void executeCommand(String command) {
		//parse rest of 'in' request
		if (command.equals("GETCAR")) {
			System.out.println("GETCAR command received");
			int carRow = in.nextInt();
			int carInRow = in.nextInt();
			array[0][carInRow].getX();
			array[0][carInRow].getY();
			out.println(carRow + " " + array[0][carInRow].getX() + ":" + array[0][carInRow].getY()  );
			out.flush();
			return;
			
		} else if (command.equals("GETLOG")) {
			System.out.println("GETLOG command received");
			int logRow = in.nextInt();
			int logInRow = in.nextInt();
			array[1][logInRow].getX();
			array[1][logInRow].getY();
			out.println(logRow + " " + array[1][logInRow].getX() + ":" + array[1][logInRow].getY()  );
			out.flush();
			return;
			
		} else if (command.equals("GETFROG")) {
			System.out.println("GETFROG command received");
			array[2][1].getX();
			array[2][1].getY();
			out.println("Frog position: " + array[2][1].getX() + ":" + array[2][1].getY()  );
			return;
			
		} else if (command.equals("SETFROG")) {
			System.out.println("SETFROG command received");
			int x = in.nextInt();
			int y = in.nextInt();
			array[2][1].setX(x);
			array[2][1].setY(y);
			out.println("Set Frog position sucessfully");
			out.flush();
				return;
				
		} else {
			System.out.println("COMMAND NOT FOUND");
			out.println("INVALID COMMAND");
			out.flush();
			return;

		}
	}
	
}
