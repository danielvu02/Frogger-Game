import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GamePrep extends JFrame implements KeyListener, ActionListener {
	
	//instances of our data classes (store position, etc here)
	private Frog frog;
	private Car cars1[];
	private Log logs1[];
	
	//graphic elements
	private Container content;
	//private JLabel frogLabel, carLabel, logLabel, backgroundLabel ;
	private JLabel frogLabel, carLabel[], logLabel[], backgroundLabel ;
	//private ImageIcon frogImage, carImage, logImage, backgroundImage;
	private ImageIcon frogImage, carImage[], logImage[], backgroundImage;

	//buttons
	private JButton StartButton;
	private JButton VisibilityButton;
	
	public GamePrep() {
		//set up background
		backgroundImage = new ImageIcon(this.getClass().getResource("/background.png"));
		backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		
		//set up frog
		frog = new Frog();
		frog.setX(250);
		frog.setY(500);
		frog.setWidth(100);
		frog.setHeight(100);
		frog.setImage("frog.png");

		//set up 1 array of car
		cars1= new Car[3];
		for(int i=0;i<cars1.length;i++){
			cars1[i]= new Car();
			cars1[i].setX(200*i);
			cars1[i].setY(400);
			cars1[i].setWidth(100);
			cars1[i].setHeight(100);
			cars1[i].setVisible(true);
			cars1[i].setMoving(false);
			cars1[i].setImage("car.png");
			cars1[i].setFrog(frog);
		}
		
		//set up log
		logs1 = new Log[3];
		for(int i=0;i<logs1.length;i++){
			logs1[i]= new Log();
			logs1[i].setX(100*i);
			logs1[i].setY(100);
			logs1[i].setWidth(100);
			logs1[i].setHeight(100);
			logs1[i].setVisible(true);
			logs1[i].setMoving(false);
			logs1[i].setImage("log.png");
			logs1[i].setFrog(frog);
			
		}

		
		//set up screen
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		content = getContentPane();
		//content.setBackground(backgroundLabel);
		setLayout(null);
		
		//graphic element instantiation and add to screen
		frogLabel = new JLabel();
		frogImage = new ImageIcon(getClass().getResource(frog.getImage()));
		frogLabel.setIcon(frogImage);
		frogLabel.setSize(frog.getWidth(), frog.getHeight());
		frogLabel.setLocation(frog.getX(), frog.getY());
		for(int i=0;i<cars1.length;i++){
			cars1[i].setFrogLabel(frogLabel);
		}
		for(int i=0;i<cars1.length;i++){
			logs1[i].setFrogLabel(frogLabel);
		}
		
		carLabel = new JLabel[3];
		carImage = new ImageIcon[3];
		for(int i=0;i<cars1.length;i++){
			carLabel[i] = new JLabel();
			carImage[i] = new ImageIcon(getClass().getResource(cars1[i].getImage()));
			carLabel[i].setIcon(carImage[i]);
			carLabel[i].setSize(cars1[i].getWidth(), cars1[i].getHeight());
			carLabel[i].setLocation(cars1[i].getX(), cars1[i].getY());
			cars1[i].setCarLabel(carLabel[i]);
		}

		
		logLabel = new JLabel[3];
		logImage = new ImageIcon[3];
		for(int i=0;i<logs1.length;i++){
			logLabel[i] = new JLabel();
			logImage[i] = new ImageIcon(getClass().getResource(logs1[i].getImage()));
			logLabel[i].setIcon(logImage[i]);
			logLabel[i].setSize(logs1[i].getWidth(), logs1[i].getHeight());
			logLabel[i].setLocation(logs1[i].getX(), logs1[i].getY());
			logs1[i].setLogLabel(logLabel[i]);
		}

		
		//add a start button
		StartButton = new JButton ("Start");
		StartButton.setSize(100, 100);
		StartButton.setLocation(GameProperties.SCREEN_WIDTH-100, 
				                GameProperties.SCREEN_HEIGHT-200);
		StartButton.setFocusable(false);
		for(int i=0;i<cars1.length;i++){
			cars1[i].setStartButton(StartButton);
		}
		
		for(int i=0;i<logs1.length;i++){
			logs1[i].setStartButton(StartButton);
		}


		//populate screen
		content.add(StartButton);
		StartButton.addActionListener(this);

		content.add(frogLabel);
		for(int i=0;i<cars1.length;i++){
			content.add(carLabel[i]);
		}
		for(int i=0;i<logs1.length;i++){
			content.add(logLabel[i]);
		}
		
		content.add(backgroundLabel);
		
		content.addKeyListener(this);
		content.setFocusable(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	public static void main( String args []) {
		GamePrep myGame = new GamePrep();
		myGame.setVisible(true);
	}


	@Override
	public void keyTyped(KeyEvent e) {}


	@Override
	public void keyPressed(KeyEvent e) {
		int x = frog.getX();
		int y = frog.getY();
		
		//modify position
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			y -= GameProperties.CHARACTER_STEP;
			
			if (y + frog.getHeight() <= 0) {
				y = GameProperties.SCREEN_HEIGHT;
			}
			
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			y += GameProperties.CHARACTER_STEP;
			
			if (y >= GameProperties.SCREEN_HEIGHT) {
				y = -1 * frog.getHeight();
			}
			
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			x -= GameProperties.CHARACTER_STEP;	
			
			if (x + frog.getWidth() <= 0) {
				x = GameProperties.SCREEN_WIDTH;
			}			
			
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			x += GameProperties.CHARACTER_STEP;	
			
			if (x >= GameProperties.SCREEN_WIDTH) {
				x = -1 * frog.getWidth();
			}

		} else {
			System.out.println("Invalid operation");
		}
		frog.setX(x);
		frog.setY(y);
		
		//update graphic
		frogLabel.setLocation(frog.getX(), frog.getY());
	}


	@Override
	public void keyReleased(KeyEvent e) {}


	@Override
	public void actionPerformed(ActionEvent e) {
		//distinguish among buttons
		if (e.getSource() == StartButton) {
			for(int i=0;i<cars1.length;i++){
				if (cars1[i].getMoving()) {
					cars1[i].setMoving(false);
				} else {
					cars1[i].startMoving();
				}
			}
			
			for(int i=0;i<logs1.length;i++){
				if (logs1[i].getMoving()) {
					logs1[i].setMoving(false);
				} else {
					logs1[i].startMoving();
				}
			}

			
		}
		
		
	}
}
