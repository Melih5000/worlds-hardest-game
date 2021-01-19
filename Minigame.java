package wh.game;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Minigame extends JPanel implements ActionListener, KeyListener {

    private int playerX=0;
    private final int playerY=590;
    Timer timer = new Timer(10,this);
    private BufferedImage kirby, mushroom, bg, knife;
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Fire> fires = new ArrayList<>();
    private Set<Integer> setOfPressedKeys = new HashSet<>();
    public boolean isPassed=false;

    public Minigame(){

        //Screen Size
        setPreferredSize(new Dimension(960,640));

        timer.start();

        //Getting images
        try {
            bg = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/minigame/background.png")));
            kirby = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/minigame/kirby.png")));
            mushroom = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/minigame/mushroom.png")));
            knife = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/minigame/knife.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addKeyListener(this);

        //Enemies
        Enemy enemy1 = new Enemy(0, 13);
        Enemy enemy2 = new Enemy(731, 61);
        Enemy enemy3 = new Enemy(310, 109);

        //Adding enemies to Enemy ArrayList
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);
    }

    public boolean isFinished(){
        int counter = 0;
        for (Enemy enemy:enemies) {
            if(enemy.isDead){
                counter++;
            }
        }
        return counter == 3;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //Background Image
        g.drawImage(bg,0,0, null);

        //Enemy images
        for (Enemy enemy : enemies) {
            if(!enemy.isDead) {
                g.drawImage(mushroom, enemy.x, enemy.y, 32, 32, null);
            }
        }

        //Player image
        g.drawImage(kirby,playerX,playerY, 50 ,44,null);

        //Fire image
        for(Fire f : fires){
            g.drawImage(knife, f.x, f.y, 30, 30,  null);
        }

        //Remove Fire
        for (int i=0 ; i<fires.size(); i++){
            if (fires.get(i).y < 0){
                fires.remove(i);
            }
        }

        //Enemy-Fire Collision
        for (Fire f: fires) {
            for (Enemy enemy: enemies) {
                if(!enemy.isDead) {
                    if (new Rectangle(f.x + 10, f.y, 10, 30).intersects(new Rectangle(enemy.x + 7, enemy.y + 6, 20, 26))) {
                        fires.remove(f);
                        enemy.isDead = true;
                    }
                }
            }
        }

        if (isFinished()){
            isPassed=true;
        }
    }

    public void refresh(){
        for (Enemy enemy:enemies) {
            enemy.isDead=false;
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_SPACE)  // if LEFT/RIGHT/SPACE pressed.
            setOfPressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        setOfPressedKeys.remove(e.getKeyCode());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(setOfPressedKeys.size()>=1){
            for(Integer key : setOfPressedKeys){
                int playerDir = 6;
                switch (key) {
                    case KeyEvent.VK_LEFT:
                        if( playerX <= 0)
                            playerX = (0);
                        else{
                            playerX = ( playerX - playerDir);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(playerX >= 910)
                            playerX = (910);
                        else{
                            playerX = ( playerX + playerDir);
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        if (fires.size() == 0)
                            fires.add(new Fire(playerX + 10, playerY - 10));
                        else if (fires.get(fires.size()-1).y - playerY <= -150)
                            fires.add(new Fire(playerX + 10, playerY - 10));
                        break;
                    default:
                        break;
                }
            }
        }

        // Enemy Movement
        for (Enemy enemy: enemies ) {
            enemy.x += enemy.enemyDir;
            if(enemy.x >= 930)
                enemy.enemyDir = -enemy.enemyDir;
            if(enemy.x <= 0)
                enemy.enemyDir = -enemy.enemyDir;
        }

        // Fire Movement
        for (Fire f:fires) {
            int fireY = 4;
            f.y -= fireY;
        }

        repaint();
    }

    public Set<Integer> getSetOfPressedKeys() {
        return setOfPressedKeys;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    static class Enemy {
        int x, y;
        int enemyDir=10;
        boolean isDead=false;

        public Enemy(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Fire {
        int x,y;

        public Fire(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}