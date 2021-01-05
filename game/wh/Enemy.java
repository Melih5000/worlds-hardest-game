package game.wh;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class Enemy extends Rectangle {

    private int x,y,width,height;
    private int enemyDir = 1;
    //SPRITE VARIABLES
    private BufferedImage mushroomR,mushroomL,mushroomUD;
    private SpriteSheet sheetR, sheetL, sheetUD;
    private int currentSprite;
    private int spriteChanger = 0;
    private boolean h,v;


    public Enemy(int x, int y, int width, int height){
        setBounds(x,y,width-3,height-1);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        try {
            mushroomR = ImageIO.read(new FileImageInputStream(new File("resource/images/MushroomR.png")));
            mushroomL = ImageIO.read(new FileImageInputStream(new File("resource/images/MushroomL.png")));
            mushroomUD = ImageIO.read(new FileImageInputStream(new File("resource/images/MushroomUD.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheetR = new SpriteSheet(mushroomR);
        sheetL = new SpriteSheet(mushroomL);
        sheetUD = new SpriteSheet(mushroomUD);

        h = false;
        v = false;
    }


    public void drawEnemy(Graphics g){

        //g.setColor(Color.magenta);
        g.setColor(new Color(0x00000000, true));
        g.fillRect(x,y,width,height);

        //Sprite Painter & Changer
        if (spriteChanger%10==0) {
            currentSprite += 32;
            spriteChanger = 0;
        }

        if (h) {
            if (enemyDir > 0) {
                g.drawImage(sheetR.getSprite(currentSprite, 0, 32, 32), this.x, this.y, 16, 16, null);

            }
            else if (enemyDir < 0) {
                g.drawImage(sheetL.getSprite(currentSprite, 0, 32, 32), this.x, this.y, 16, 16, null);
            }
        }

        if (v) {

            g.drawImage(sheetUD.getSprite(currentSprite, 0, 32, 32), this.x, this.y, 16, 16, null);

        }

        if (currentSprite==96) {
            currentSprite = 0;
        }
        spriteChanger++;
    }

    public void update(){
        setBounds(this.x, this.y, 13,15);
    }

    public void enemyHorizontalMove(int min, int max){
        h = true;
        this.x += enemyDir;
        if(this.x >= max)
            enemyDir = -enemyDir;
        if(this.x <= min)
            enemyDir = -enemyDir;
    }

    public void enemyVerticalMove(int min, int max){
        v = true;
        this.y += enemyDir;
        if(this.y >= max)
            enemyDir = -enemyDir;
        if(this.y <= min)
            enemyDir = -enemyDir;
    }


}
