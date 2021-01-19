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

import kuusisto.tinysound.TinySound;


public class Player extends Rectangle implements KeyListener {


	private boolean collidingUp = false;
	private boolean collidingDown = false;
	private boolean collidingLeft = false;
	private boolean collidingRight = false;
	private boolean isPressedUp = false;
	private boolean isPressedDown = false;
	private boolean isPressedLeft = false;
	private boolean isPressedRight = false;
	public boolean minigame1=false, minigame3=false, gameFinished = false;
	private final Level1 level1 = new Level1();
	private final Level2 level2 = new Level2();
	private final Level3 level3 = new Level3();
	private int deathCounter = 0;
	private long stepCounter = 0;
	private boolean onceMng3 = true;
	private final int speed = 1;

	private BufferedImage rR, rL,rU,rD;
    private final SpriteSheet krunR;
	private final SpriteSheet krunL;
	private final SpriteSheet krunU;
	private final SpriteSheet krunD;
    private int currentSprite;
    private int spriteChanger = 0;
    
	public Player(int x, int y, int width, int height) {

		setBounds(x,y,width-2,height-2);

		//Getting player sprite images
		try {
			rR = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/krunR.png")));
			rL = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/krunL.png")));
			rU = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/krunU.png")));
			rD = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/krunD.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Converting sprite images to sprite sheets
		krunR = new SpriteSheet(rR);
		krunL = new SpriteSheet(rL);
		krunU = new SpriteSheet(rU);
		krunD = new SpriteSheet(rD);

	}


	public void update(Level level) {

		//Checking collisions for any direction
		checkCollisionUp(level);
		checkCollisionDown(level);
		checkCollisionLeft(level);
		checkCollisionRight(level);

		//Movement
		if(this.isPressedUp && !this.collidingUp) {
			this.y -= speed;
			stepCounter++;
		}
		if(this.isPressedDown && !this.collidingDown) {
			this.y += speed;
			stepCounter++;
		}
		if(this.isPressedLeft && !this.collidingLeft) {
			this.x -= speed;
			stepCounter++;
		}
		if(this.isPressedRight && !this.collidingRight) {
			this.x += speed;
			stepCounter++;
		}

		//Cheats :)
		//level1.setPassed(true);
		//level2.setPassed(true);
		//level3.setPassed(true);

		//Setting respawn options and minigame coordinates for Level 1
		if(!level1.isPassed) {
			if (new Rectangle(this.x,this.y,16,16).intersects(new Rectangle(496,320,16,1))){
				minigame1 = true;
				isPressedUp=false;
			}

			for (Enemy e : level.enemies) {
				if (this.intersects(e)) {
					this.x = 20;
					this.y = 480;
					for (Coin c : level.coins) {
						c.setCollected(false);
					}
					this.deathCounter++;
					minigame1 = false;
					TinySound.init();
					TinySound.loadSound(ClassLoader.getSystemResource("wh/game/sounds/DeathSound.wav")).play(0.5f);
				}
			}
		}

		//Setting respawn options for Level 2
		else if(!level2.isPassed){
			for (Enemy e : level.enemies) {
				if (this.intersects(e)) {
					this.x = 24;
					this.y = 520;
					for (Coin c : level.coins) {
						c.setCollected(false);
					}
					this.deathCounter++;
					TinySound.init();
					TinySound.loadSound(ClassLoader.getSystemResource("wh/game/sounds/DeathSound.wav")).play(0.5f);
				}
			}
		}

		//Setting respawn options and minigame coordinates for Level 3
		else if(!level3.isPassed){

			if (onceMng3 && new Rectangle(this.x,this.y,16,16).intersects(new Rectangle(368,416,16,16))){
				minigame3 = true;
				onceMng3 =false;
				if(isPressedUp)
					isPressedUp=false;
				if(isPressedDown)
					isPressedDown=false;
				if(isPressedLeft)
					isPressedLeft=false;
				if(isPressedRight)
					isPressedRight=false;

			}


			for (Enemy e : level.enemies) {
				if (this.intersects(e)) {
					this.x = 24;
					this.y = 312;
					for (Coin c : level.coins) {
						c.setCollected(false);
					}
					this.deathCounter++;
					minigame3 = false;
					TinySound.init();
					TinySound.loadSound(ClassLoader.getSystemResource("wh/game/sounds/DeathSound.wav")).play(0.5f);
				}
			}

		}

		//Coin Collection
		for(Coin c : level.coins){
			if(this.intersects(c) && !c.isCollected()) {
				TinySound.init();
                TinySound.loadSound(ClassLoader.getSystemResource("wh/game/sounds/CoinSound.wav")).play(0.1f);
				c.setCollected(true);
			}
		}

		//Spawn points for passing next level

		if(!level1.isPassed && this.x == 960){
			level1.setPassed(true);
			this.x = 24;
			this.y = 520;
		}
		else if(!level2.isPassed && this.x == 960){
			level2.setPassed(true);
			this.x = 24;
			this.y = 312;
		}
		else if(!level3.isPassed && this.x == 960){
			level3.setPassed(true);
		}

		if(level3.isPassed){
			gameFinished = true;
		}

	}

	//Finding any vertical walls next to player
	public Rectangle getRelativeVerticalWall(Level level, int x, int y){
		for(Rectangle rec : level.getWallRecs()){
			if(x >= rec.getX() && x <= rec.getX()+14 && y == rec.getY()){
				return rec;
			}
		}
		for(Rectangle rec : level.getFinishLine()){
			if(x >= rec.getX() && x <= rec.getX()+14 && y == rec.getY()){
				return rec;
			}
		}

		for (Rectangle rec : level.getDoors()) {
			if (x >= rec.getX() && x <= rec.getX() + 14 && y == rec.getY()) {
				return rec;
			}
		}
		return null;
	}

	//Finding any horizontal walls next to player
	public Rectangle getRelativeHorizontalWall(Level level, int x, int y){
		for(Rectangle rec : level.getWallRecs()){
			if(x == rec.getX() && y <= rec.getY()+14 && y >= rec.getY()){
				return rec;
			}
		}
		for(Rectangle rec : level.getFinishLine()){
			if(x == rec.getX() && y <= rec.getY()+14 && y >= rec.getY()){
				return rec;
			}
		}
		for(Rectangle rec : level.getDoors()){
			if(x == rec.getX() && y <= rec.getY()+14 && y >= rec.getY()){
				return rec;
			}
		}
		return null;
	}

	//Check collision for UP
	public void checkCollisionUp(Level level){
		if((getRelativeVerticalWall(level,this.x,this.y-15)) != null ||
				(getRelativeVerticalWall(level,this.x+14,this.y-15)) != null) {
			this.collidingUp = true;
			return;
		}

		this.collidingUp = false;
	}

	//Check collision for DOWN
	public void checkCollisionDown(Level level){
		if((getRelativeVerticalWall(level,this.x,this.y+15)) != null ||
				(getRelativeVerticalWall(level,this.x+14,this.y+15)) != null) {
			this.collidingDown = true;
			return;
		}
		this.collidingDown = false;
	}

	//Check collision for LEFT
	public void checkCollisionLeft(Level level){
		if((getRelativeHorizontalWall(level,this.x-15,this.y)) != null ||
				(getRelativeHorizontalWall(level,this.x-15,this.y+14)) != null) {
			this.collidingLeft = true;
			return;
		}
		this.collidingLeft = false;
	}

	//Check collision for RIGHT
	public void checkCollisionRight(Level level){
		if((getRelativeHorizontalWall(level,this.x+15,this.y)) != null ||
				(getRelativeHorizontalWall(level,this.x+15,this.y+14)) != null){
			this.collidingRight = true;
			return;
		}
		this.collidingRight = false;
	}

	
	public void drawPlayer(Graphics g) {

		// Setting appropriate sprite sheet for movement

		if (!isPressedDown && !isPressedUp && !isPressedRight && !isPressedLeft) {
	        g.drawImage(krunD.getSprite(0, 0, 36, 36), this.x, this.y, 16, 16, null);
		}
		
		if (isPressedRight) {
			if (spriteChanger%15==0) {
	        	currentSprite += 36;
	        	spriteChanger = 0;
			}
	        g.drawImage(krunR.getSprite(currentSprite, 0, 36, 36), this.x, this.y, 16, 16, null);
	        if (currentSprite==108) {
	        	currentSprite = 0;
			}
	        spriteChanger++;
		}
		if (isPressedLeft) {
			if (spriteChanger%15==0) {
				currentSprite += 36;
				spriteChanger = 0;
			}
			g.drawImage(krunL.getSprite(currentSprite, 0, 36, 36), this.x, this.y, 16, 16, null);
			if (currentSprite==108) {
				currentSprite = 36;
			}
			spriteChanger++;
		}
		if (isPressedUp) {
			if (spriteChanger%15==0) {
				currentSprite += 36;
				spriteChanger = 0;
			}
			g.drawImage(krunU.getSprite(currentSprite, 0, 36, 36), this.x, this.y, 16, 16, null);
			if (currentSprite==108) {
				currentSprite = 36;
			}
			spriteChanger++;
		}
		if (isPressedDown) {
			if (spriteChanger%15==0) {
				currentSprite += 36;
				spriteChanger = 0;
			}
			g.drawImage(krunD.getSprite(currentSprite, 0, 36, 36), this.x, this.y, 16, 16, null);
			if (currentSprite==108) {
				currentSprite = 36;
			}
			spriteChanger++;
		}
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT -> isPressedRight = true;
			case KeyEvent.VK_LEFT -> isPressedLeft = true;
			case KeyEvent.VK_UP -> isPressedUp = true;
			case KeyEvent.VK_DOWN -> isPressedDown = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT -> isPressedRight = false;
			case KeyEvent.VK_LEFT -> isPressedLeft = false;
			case KeyEvent.VK_UP -> isPressedUp = false;
			case KeyEvent.VK_DOWN -> isPressedDown = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public int getDeathCounter() {
		return deathCounter;
	}

	public Level1 getLevel1() {
		return level1;
	}

	public Level2 getLevel2() {
		return level2;
	}

	public Level3 getLevel3() {
		return level3;
	}

	public long getStepCounter() {
		return stepCounter;
	}

	public boolean isOnceMng3() {
		return onceMng3;
	}

	public void setOnceMng3(boolean onceMng3) {
		this.onceMng3 = onceMng3;
	}

	public void setDeathCounter(int deathCounter) {
		this.deathCounter = deathCounter;
	}

	public void setStepCounter(long stepCounter) {
		this.stepCounter = stepCounter;
	}
}
