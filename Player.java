package wh.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class Player extends Rectangle implements KeyListener {
	
	
	private double speedX,speedY;
	private BufferedImage player;
	
	public Player(double x, double y, int width, int height) {
		setBounds((int)x,(int)y,width,height);
		speedX = 0;
		speedY = 0;
		
	}
	
	public void update() {
		this.x += speedX;
		this.y += speedY;
	}
	
	public void drawPlayer(Graphics g) {
		
		
		//g.setColor(new Color(0x00000000, true));
		g.setColor(Color.MAGENTA);
		g.fillRect(this.x, this.y, this.width, this.height);
		
		
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(double speedY) {
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
	
	
}
