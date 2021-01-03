package game.wh;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends Rectangle {

    private int x,y,width,height;
    private int enemyDir = 1;


    public Enemy(int x, int y, int width, int height){
        setBounds(x,y,width,height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void drawEnemy(Graphics g){

        g.setColor(Color.magenta);
        g.fillRect(x,y,width,height);

    }

    public void update(){
        setBounds(this.x, this.y, 16,16);
    }

    public void enemyHorizontalMove(int min, int max){
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
