package game.wh;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class Coin extends Rectangle {

    private int x,y,width,height;
    private boolean isCollected = false;

    private BufferedImage coing;
    private SpriteSheet sheet;
    private int currentSprite;
    private int spriteChanger = 0;

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public Coin(int x, int y, int width, int height){
        setBounds(x,y,width,height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        try {
            coing = ImageIO.read(new FileImageInputStream(new File("resource/images/Coin.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = new SpriteSheet(coing);
    }

    public void drawCoin(Graphics g){

        g.setColor(new Color(0x00000000, true));
        g.fillRect(x,y,width,height);

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
}
