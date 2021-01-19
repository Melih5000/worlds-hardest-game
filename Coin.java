package wh.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class Coin extends Rectangle {

    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private boolean isCollected = false;
    
    private BufferedImage coing;
    private final SpriteSheet sheet;
    private int currentSprite;
    private int spriteChanger = 0;
    
    


    public Coin(int x, int y, int width, int height){
        setBounds(x,y,width,height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        //Getting sprite image
        try {
        	coing = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/Coin.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

        //Converting image to sprite sheet
        sheet = new SpriteSheet(coing);
        
        
    }

    public void drawCoin(Graphics g){

        //Determine the change speed of sprites
        if (spriteChanger%7==0) {
        	currentSprite += 32;
        	spriteChanger = 0;
		}
        g.drawImage(sheet.getSprite(currentSprite, 0, 32, 32), this.x, this.y, 16, 16, null);
        if (currentSprite==224) {
        	currentSprite = 32;
		}
        spriteChanger++;
    }
    
    
    
    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public boolean isCollected() {
        return isCollected;
    }
}
