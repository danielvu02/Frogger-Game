import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

//this is the object that as the thread
public class Log extends Object implements Runnable {

	private Boolean visible, moving;
	private Thread tLog;
	private JLabel LogLabel, FrogLabel;
	private Frog frog;
	private JButton StartButton;
	
	public Log() {
		super(0, 0, 100, 100, "log.png");
		this.visible = true;
		this.moving = false;
	}
	
	public void setLogLabel(JLabel temp) {
		this.LogLabel = temp;
	}
	
	public void setFrog(Frog temp) {
		this.frog = temp;
	}

	public void setFrogLabel(JLabel temp) {
		this.FrogLabel = temp;
	}

	public void setStartButton(JButton temp) {
		this.StartButton = temp;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getMoving() {
		return moving;
	}

	public void setMoving(Boolean moving) {
		this.moving = moving;
	}
	
	public void show() {
		this.visible = true;
	}
	
	public void hide() {
		this.visible = true;
	}
	
	public void Display () {
		System.out.println("Log - x,y: " + this.x + "," + this.y);
		System.out.println("width,height: " + this.width + "," + height);
		System.out.println("image: " + this.image);
		//super.Display();
		System.out.println("visible: " + this.visible);
		System.out.println("moving: " + this.moving);
	}
	
	public void startMoving() {
		System.out.println("Move!");
		if (!this.moving) {
			tLog = new Thread(this, "Log Thread");
			tLog.start();
		}
	}

	@Override
	public void run() {
		System.out.println("Log Thread started");
		this.moving = true;
		this.StartButton.setText("Stop");

		this.FrogLabel.setIcon(
				new ImageIcon( getClass().getResource("frog.png") )
				);
		this.LogLabel.setIcon(
				new ImageIcon( getClass().getResource("log.png") )
				);

		while (this.moving) {
			//moving instructions
			
			//get current x
			int currentX = this.x;
			
			//increase x
			currentX += GameProperties.CHARACTER_STEP;
			
			//boundary check right-side
			if (currentX >= GameProperties.SCREEN_WIDTH) {
				currentX = -1 * this.width;
			}
			
			//update x
			//this.x = currentX;
			this.setX(currentX);
			System.out.println("Log X, Y: " + this.x + "," + this.y);
			
			//check for collision
			if ( this.visible ) this.detectCollision();
			
			//update Character2Label
			this.LogLabel.setLocation(this.x, this.y);
			
			//pause
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("End Log Thread");
		this.StartButton.setText("Start");
		
	}
	
	private void detectCollision() {
		if (r.intersects( frog.getRectangle() )) {
			System.out.println("BOOM!");
			this.moving = false;
			this.FrogLabel.setIcon(
					new ImageIcon( getClass().getResource("redFrog.png") )
					);

		}
	}


}