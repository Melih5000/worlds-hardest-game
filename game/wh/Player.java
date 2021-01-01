
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Player extends Rectangle implements KeyListener, ActionListener {
	
	Timer timer = new Timer(1,this::actionPerformed);

	private int speedX;
	private int speedY;
	private int lastX, lastY;
	private int tempX,tempY;
	private BufferedImage player;
	private ArrayList<Integer> lastXPoints;
	private ArrayList<Integer> lastYPoints;



	public Player(int x, int y, int width, int height) {

		setBounds((int)x,(int)y,width,height);
		speedX = 0;
		speedY = 0;
		lastXPoints = new ArrayList<Integer>();
		lastYPoints = new ArrayList<Integer>();
		timer.start();
	}
	
	public void update(ArrayList<Rectangle> r) {

		boolean intersectedBlock = false;
		Rectangle rec=null;

		for (int i = 0; i < r.size(); i++) {
			rec = r.get(i);

			if (rec.intersects(this)) {
				intersectedBlock = true;
				break;
			}
		}

		if (intersectedBlock) {
			Rectangle r1 = new Rectangle(0,0,16,16);

			for (int i = 0; i<lastXPoints.size(); i++) {

				for(int j = 0; j<lastYPoints.size(); j++){

					r1 = new Rectangle(lastXPoints.get(i),lastYPoints.get(j),16,16);

					if(!r1.intersects(rec)){
						this.y = lastYPoints.get(j);

					}

				}

				if(!r1.intersects(rec)){
					this.x = lastXPoints.get(i);
				}
			}
			/*this.x = lastX;
			this.y = lastY;
			this.x += 0;
			this.y += 0;*/

		} else {

			this.x += speedX;
			//tempX = this.x;
			//lastXPoints.add(tempX);

			this.y += speedY;
			//tempY = this.y;
			//lastYPoints.add(tempY);

		}

	}
	
	public void drawPlayer(Graphics g) {
		
		
		//g.setColor(new Color(0x00000000, true));
		g.setColor(Color.GREEN);
		g.fillRect(this.x, this.y, this.width, this.height);
		
		
	}

	public void lastPosition(){
		tempX = this.x;
		lastXPoints.add(tempX);

		tempY = this.y;
		lastYPoints.add(tempY);
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			this.setSpeedX(1);
			break;
		case KeyEvent.VK_LEFT:
			this.setSpeedX(-1);
			break;
		case KeyEvent.VK_UP:
			this.setSpeedY(-1);
			break;
		case KeyEvent.VK_DOWN:
			this.setSpeedY(1);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			this.setSpeedX(0);
			break;
		case KeyEvent.VK_LEFT:
			this.setSpeedX(0);
			break;
		case KeyEvent.VK_UP:
			this.setSpeedY(0);
			break;
		case KeyEvent.VK_DOWN:
			this.setSpeedY(0);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		this.lastPosition();
	}
}
