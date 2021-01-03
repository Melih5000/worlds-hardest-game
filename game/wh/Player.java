package game.wh;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Player extends Rectangle implements KeyListener {


	private boolean collidingUp = false;
	private boolean collidingDown = false;
	private boolean collidingLeft = false;
	private boolean collidingRight = false;
	private boolean isPressedUp = false;
	private boolean isPressedDown = false;
	private boolean isPressedLeft = false;
	private boolean isPressedRight = false;
	private Level1 level1 = new Level1();


	public Player(int x, int y, int width, int height) {

		setBounds(x,y,width,height);

	}

	public void update(Level1 level1) {

		checkCollisionUp(level1);
		checkCollisionDown(level1);
		checkCollisionLeft(level1);
		checkCollisionRight(level1);

		if(this.isPressedUp && !this.collidingUp)
			this.y --;
		if(this.isPressedDown && !this.collidingDown)
			this.y ++;
		if(this.isPressedLeft && !this.collidingLeft)
			this.x --;
		if(this.isPressedRight && !this.collidingRight)
			this.x ++;

		for(Enemy e : level1.enemies){
			if(this.intersects(e)) {
				this.x = 40;
				this.y = 216;
				for(Coin c : level1.coins){
					c.setCollected(false);
				}
			}
		}

		for(Coin c : level1.coins){
			if(this.intersects(c))
				c.setCollected(true);
		}

	}

	public Rectangle getRelativeVerticalWall(Level1 level1, int x, int y){
		for(Rectangle rec : level1.getWallRecs()){
			if(x >= rec.getX() && x <= rec.getX()+16 && y == rec.getY()){
				return rec;
			}
		}
		return null;
	}

	public Rectangle getRelativeHorizontalWall(Level1 level1, int x, int y){
		for(Rectangle rec : level1.getWallRecs()){
			if(x == rec.getX() && y <= rec.getY()+16 && y >= rec.getY()){
				return rec;
			}
		}
		return null;
	}

	public void checkCollisionUp(Level1 level1){
		if((getRelativeVerticalWall(level1,this.x,this.y-17)) != null ||
				(getRelativeVerticalWall(level1,this.x+16,this.y-17)) != null) {
			this.collidingUp = true;
			return;
		}
		this.collidingUp = false;
	}

	public void checkCollisionDown(Level1 level1){
		if((getRelativeVerticalWall(level1,this.x,this.y+17)) != null ||
				(getRelativeVerticalWall(level1,this.x+16,this.y+17)) != null) {
			this.collidingDown = true;
			return;
		}
		this.collidingDown = false;
	}

	public void checkCollisionLeft(Level1 level1){
		if((getRelativeHorizontalWall(level1,this.x-17,this.y)) != null ||
				(getRelativeHorizontalWall(level1,this.x-17,this.y+16)) != null) {
			this.collidingLeft = true;
			return;
		}
		this.collidingLeft = false;
	}

	public void checkCollisionRight(Level1 level1){
		if((getRelativeHorizontalWall(level1,this.x+17,this.y)) != null ||
				(getRelativeHorizontalWall(level1,this.x+17,this.y+16)) != null){
			this.collidingRight = true;
			return;
		}
		this.collidingRight = false;
	}


	public void drawPlayer(Graphics g) {


		//g.setColor(new Color(0x00000000, true));
		g.setColor(Color.GREEN);
		g.fillRect(this.x, this.y, this.width, this.height);


	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				isPressedRight = true;
				break;
			case KeyEvent.VK_LEFT:
				isPressedLeft = true;
				break;
			case KeyEvent.VK_UP:
				isPressedUp = true;
				break;
			case KeyEvent.VK_DOWN:
				isPressedDown = true;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				isPressedRight = false;
				break;
			case KeyEvent.VK_LEFT:
				isPressedLeft = false;
				break;
			case KeyEvent.VK_UP:
				isPressedUp = false;
				break;
			case KeyEvent.VK_DOWN:
				isPressedDown = false;
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
