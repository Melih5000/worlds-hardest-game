package wh.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class Enemy extends Rectangle {

    private int x;
    private int y;
    private final int width;
    private final int height;
    private int enemyDir = 1;
    private BufferedImage mushroomR,mushroomL,mushroomUD, ghostR, ghostL, ghostUD;
    private final SpriteSheet sheetR;
    private final SpriteSheet sheetL;
    private final SpriteSheet sheetUD;
    private final SpriteSheet sheetGhostR;
    private final SpriteSheet sheetGhostL;
    private final SpriteSheet sheetGhostUD;
    private int currentSprite;
    private int spriteChanger = 0;
    private boolean h;
    public boolean ghost;
    
    

    public Enemy(int x, int y, int width, int height){
        setBounds(x,y,width-3,height-1);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        //GETTING IMAGES
        try {
            mushroomR = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/MushroomR.png")));
            mushroomL = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/MushroomL.png")));
            mushroomUD = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/MushroomUD.png")));
            ghostR = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/GhostR.png")));
            ghostL = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/GhostL.png")));
            ghostUD = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/GhostUD.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

        //MAKING SPRITE SHEETS
        sheetR = new SpriteSheet(mushroomR);
        sheetL = new SpriteSheet(mushroomL);
        sheetUD = new SpriteSheet(mushroomUD);
        sheetGhostR = new SpriteSheet(ghostR);
        sheetGhostL = new SpriteSheet(ghostL);
        sheetGhostUD = new SpriteSheet(ghostUD);

        h = false;
        ghost = false;
        
    }
    

    public void drawEnemy(Graphics g) {

        //Sprite Painter & Changer for Mushroom
        if (!ghost) {

            //Determine the change speed of sprites
            if (spriteChanger % 10 == 0) {
                currentSprite += 32;
                spriteChanger = 0;
            }

            if (h) {
                if (enemyDir > 0) {
                    g.drawImage(sheetR.getSprite(currentSprite, 0, 32, 32), this.x, this.y, 16, 16, null);

                } else if (enemyDir < 0) {
                    g.drawImage(sheetL.getSprite(currentSprite, 0, 32, 32), this.x, this.y, 16, 16, null);
                }
            } else {

                g.drawImage(sheetUD.getSprite(currentSprite, 0, 32, 32), this.x, this.y, 16, 16, null);

            }


            if (currentSprite == 96) {
                currentSprite = 0;
            }
            spriteChanger++;
        }

        //Sprite Painter & Changer for Ghost
        else if(ghost){

            //Determine the change speed of sprites
            if (spriteChanger % 25 == 0) {
                currentSprite += 48;
                spriteChanger = 0;
            }

            if (h) {
                if (enemyDir > 0) {
                    g.drawImage(sheetGhostR.getSprite(currentSprite, 0, 48, 48), this.x, this.y, 16, 16, null);

                } else if (enemyDir < 0) {
                    g.drawImage(sheetGhostL.getSprite(currentSprite, 0, 48, 48), this.x, this.y, 16, 16, null);
                }
            } else {

                g.drawImage(sheetGhostUD.getSprite(currentSprite, 0, 48, 48), this.x, this.y, 16, 16, null);

            }


            if (currentSprite == 240) {
                currentSprite = 0;
            }
            spriteChanger++;
        }
    }

    public void update(){
        setBounds(this.x, this.y, 13,15);
    }
    
    

    public void enemyHorizontalMove(int min, int max){

        // By making h = true -> determine which sprite sheet will be used in draw method
        h = true;

        this.x += enemyDir;
        if(this.x >= max)
            enemyDir = -enemyDir;
        if(this.x <= min)
            enemyDir = -enemyDir;
    }

    public void enemyVerticalMove(int min, int max){

        this.y += enemyDir;
        if(this.y >= max)
            enemyDir = -enemyDir;
        if(this.y <= min)
            enemyDir = -enemyDir;
    }


}
