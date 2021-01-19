package wh.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Level extends Rectangle {

    protected BufferedImage level;

    protected ArrayList<Rectangle> wallRecs;
    protected ArrayList<Rectangle> finishLine;
    protected ArrayList<Rectangle> doors;
    protected ArrayList<Enemy> enemies;
    protected ArrayList<Coin> coins;
    protected ArrayList<Animal> animals;

    protected int wallCreate = 0;
    protected int [][] walls;

    protected boolean isPassed;

    public Level(){
        wallRecs = new ArrayList<>();
        finishLine = new ArrayList<>();
        doors = new ArrayList<>();
        enemies = new ArrayList<>();
        coins = new ArrayList<>();
        animals = new ArrayList<>();
        isPassed = false;
    }

    // Drawing wall rectangles just for once because of performance and other game objects
    public void graphicCaller(Graphics g){
        if(wallCreate == 0) {
            drawWalls(g);
            wallCreate++;
        }
        for(Coin c : coins){
            if(!c.isCollected())
                c.drawCoin(g);
        }
        for(Enemy e : enemies){
            e.drawEnemy(g);
        }
        for(Animal a : animals){
            a.drawAnimal(g);
        }
    }

    public void drawWalls(Graphics g){

        /*
        Drawing rectangles with controlling the array which is initialized in each level.
        1 -> Standard Walls
        2 -> Finish Line
        3 -> Door
        4,5,6,7 -> Large Rocks
         */
        for(int i=0 ; i < walls.length ; i++){
            for(int j=0; j < walls[i].length ; j++){
                if(walls[i][j] == 1){
                    Rectangle r = new Rectangle(j*16,i*16,16,16);
                    wallRecs.add(r);
                }
                else if(walls[i][j] == 2){
                    Rectangle r = new Rectangle(j*16,i*16,16,16);
                    finishLine.add(r);
                }
                else if(walls[i][j] == 3){
                    Rectangle r = new Rectangle(j*16,i*16,16,16);
                    doors.add(r);
                }

                //Rocks

                else if(walls[i][j] == 4){
                    Rectangle r = new Rectangle(j*16+4,i*16+2,16,16);
                    wallRecs.add(r);
                }
                else if(walls[i][j] == 5){
                    Rectangle r = new Rectangle(j*16-5,i*16+2,16,16);
                    wallRecs.add(r);
                }
                else if(walls[i][j] == 6){
                    Rectangle r = new Rectangle(j*16+4,i*16-5,16,16);
                    wallRecs.add(r);
                }
                else if(walls[i][j] == 7){
                    Rectangle r = new Rectangle(j*16-5,i*16-5,16,16);
                    wallRecs.add(r);
                }

            }
        }
    }

    public void unlockDoor(){
        doors.clear();
    }


    public void levelMove(){

    }

    public void update(){
        for(Enemy e : enemies){
            e.update();
        }
        canPass();
    }

    public void canPass(){
        int coinCount = 0;
        for(Coin c : coins){
            if(c.isCollected())
                coinCount++;
        }
        if(coinCount == coins.size()){
            finishLine.clear();
        }
    }

    public void refreshFinishLine(){
        for(int i=0 ; i < walls.length ; i++) {
            for (int j = 0; j < walls[i].length; j++) {
                if(walls[i][j] == 2){
                    Rectangle r = new Rectangle(j*16,i*16,16,16);
                    finishLine.add(r);
                }
            }
        }
    }

    public ArrayList<Rectangle> getWallRecs() {
        return wallRecs;
    }

    public ArrayList<Rectangle> getFinishLine() {
        return finishLine;
    }

    public ArrayList<Rectangle> getDoors() {
        return doors;
    }

    public BufferedImage getLevel() {
        return level;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }
}
