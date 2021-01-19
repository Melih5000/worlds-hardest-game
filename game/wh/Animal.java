package game.wh;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Animal extends Rectangle {

    private double x;
    private double y;
    private final double width;
    private final double height;
    private final double spawnX, spawnY;
    private double animalDirX = 0.1;
    private double animalDirY = 0.1;
    private final String animalType;
    private BufferedImage bR,bL,wolfIR,wolfIL,gDeer,bIR,gDeerL;
    private final SpriteSheet bearR;
    private final SpriteSheet bearL;
    private final SpriteSheet bearIdleR;
    private final SpriteSheet wolfIdleR;
    private final SpriteSheet wolfIdleL;
    private final SpriteSheet grazingDeer;
    private final SpriteSheet grazingDeerL;
    private int currentSprite;
    private int spriteChanger = 0;

    public Animal(int x, int y, int width, int height, String type){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        spawnX = x;
        spawnY = y;
        animalType = type;

        //Getting sprite images
        try {
            bR = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/bearR.png")));
            bL = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/bearL.png")));
            wolfIR = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/wolfIdleR.png")));
            wolfIL = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/wolfIdleL.png")));
            gDeer = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/grazingDeer.png")));
            gDeerL = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/grazingDeerLeft.png")));
            bIR = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/beerIdleR.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Converting images to sprite sheets
        bearR = new SpriteSheet(bR);
        bearL = new SpriteSheet(bL);
        wolfIdleR = new SpriteSheet(wolfIR);
        wolfIdleL = new SpriteSheet(wolfIL);
        grazingDeer = new SpriteSheet(gDeer);
        grazingDeerL = new SpriteSheet(gDeerL);
        bearIdleR = new SpriteSheet(bIR);
    }

    public void drawAnimal(Graphics g){

        //Bear Sprites (Right, Left, Up-Down)
        if(animalType.equals("Bear")) {

            //Determine the change speed of sprites
            if (spriteChanger % 35 == 0) {
                currentSprite += 32;
                spriteChanger = 0;
            }
            if (animalDirX > 0) {
                g.drawImage(bearR.getSprite(currentSprite, 0, 32, 32), (int) this.x, (int) this.y, 27, 27, null);

            } else if (animalDirX < 0) {
                g.drawImage(bearL.getSprite(currentSprite, 0, 32, 32), (int) this.x, (int) this.y, 27, 27, null);
            }

            if(this.x == spawnX && this.y == spawnY){
                g.drawImage(bearIdleR.getSprite(currentSprite, 0, 32, 32), (int) this.x, (int) this.y,27,27,null);
            }

            if (currentSprite == 96) {
                currentSprite = 0;
            }
            spriteChanger++;
        }

        //Right-facing Dog Sprites
        if(animalType.equals("DogR")){

            //Determine the change speed of sprites
            if (spriteChanger % 13 == 0) {
                currentSprite += 32;
                spriteChanger = 0;
            }
            g.drawImage(wolfIdleR.getSprite(currentSprite, 0, 32, 32), (int) this.x, (int) this.y, 24, 24, null);
            if (currentSprite == 96) {
                currentSprite = 0;
            }
            spriteChanger++;
        }

        //Left-facing Dog Sprites
        if(animalType.equals("DogL")){

            //Determine the change speed of sprites
            if (spriteChanger % 13 == 0) {
                currentSprite += 32;
                spriteChanger = 0;
            }
            g.drawImage(wolfIdleL.getSprite(currentSprite, 0, 32, 32), (int) this.x, (int) this.y, 24, 24, null);
            if (currentSprite == 96) {
                currentSprite = 0;
            }
            spriteChanger++;
        }

        //Right-facing grazing deer sprites
        if(animalType.equals("grazingDeerR")) {

            //Determine the change speed of sprites
            if (spriteChanger % 5 == 0) {
                currentSprite += 256;
                spriteChanger = 0;
            }

            g.drawImage(grazingDeer.getSprite(currentSprite, 0, 256, 256), (int) this.x, (int) this.y, 48, 48, null);

            if (currentSprite == 20992) {
                currentSprite = 0;
            }
            spriteChanger++;
        }

        //Left-facing grazing deer sprites
        if(animalType.equals("grazingDeerL")) {

            //Determine the change speed of sprites
            if (spriteChanger % 5 == 0) {
                currentSprite += 256;
                spriteChanger = 0;
            }

            g.drawImage(grazingDeerL.getSprite(currentSprite, 0, 256, 256), (int) this.x, (int) this.y, 48, 48, null);

            if (currentSprite == 20992) {
                currentSprite = 0;
            }
            spriteChanger++;
        }
    }

    // Animal Movement
    // If we want to move the animal on just one axis we make the other axis min max difference 0

    public void animalMove(int minX, int maxX, int minY, int maxY){

        if(maxX - minX != 0){
            this.x += animalDirX;
            if(this.x >= maxX)
                animalDirX = -animalDirX;
            if(this.x <= minX)
                animalDirX = -animalDirX;
        }
        if(maxY - minY != 0){
            this.y += animalDirY;
            if(this.y >= maxY)
                animalDirY = -animalDirY;
            if(this.y <= minY)
                animalDirY = -animalDirY;
        }

    }

}
