package game.wh;

import java.awt.*;

public class Coin extends Rectangle {

    private int x,y,width,height;
    private boolean isCollected = false;

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
    }

    public void drawCoin(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect(x,y,width,height);
    }
}
