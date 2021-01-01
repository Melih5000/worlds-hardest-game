package wh.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Rectangle implements KeyListener {
	
	
	private int speedX;
	private int speedY;
	private int lastX, lastY;
	private BufferedImage player;



	public Player(int x, int y, int width, int height) {

		setBounds((int)x,(int)y,width,height);
		speedX = 0;
		speedY = 0;
		
	}
	
	public void update(ArrayList<Rectangle> r) {

		boolean intersectedBlock = false;

		for (int i = 0; i < r.size(); i++) {
			Rectangle rec = r.get(i);

			if (rec.intersects(this)) {
				intersectedBlock = true;
				break;
			}
		}

		if (intersectedBlock) {
			this.x = lastX;
			this.y = lastY;
			this.x += 0;
			this.y += 0;

		} else {
			lastX = this.x;
			lastY = this.y;
			this.x += speedX;
			this.y += speedY;
		}

	}
	
	public void drawPlayer(Graphics g) {
		
		
		//g.setColor(new Color(0x00000000, true));
		g.setColor(Color.MAGENTA);
		g.fillRect(this.x, this.y, this.width, this.height);
		
		
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
			lastX = (int)this.getX();
			break;
		case KeyEvent.VK_LEFT:
			this.setSpeedX(0);
			lastX = (int)this.getX();
			break;
		case KeyEvent.VK_UP:
			this.setSpeedY(0);
			lastY = (int)this.getY();
			break;
		case KeyEvent.VK_DOWN:
			this.setSpeedY(0);
			lastY = (int)this.getY();
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
