package wh.game;

import kuusisto.tinysound.TinySound;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MainMenu extends JPanel implements ActionListener {

    private BufferedImage menu;
    private Image playIcon, playHover, playClick, quitIcon, quitHover, quitClick, creditsIcon,
            howToPlayIcon, howToPlayHover, howToPlayClick, creditsHover, creditsClick, musicIcon, musicOff;
    JButton play, quit, credits, howToPlay, music;
    boolean playButton, creditsBool, howToPlayBool, musicOn;
    int musicButtonCounter;


    public MainMenu(){

        creditsBool = false;
        howToPlayBool = false;
        musicOn = true;
        musicButtonCounter = 0;

        //SCREEN SIZE
        setPreferredSize(new Dimension(960,640));

        //CLOSE MENU
        playButton =false;

        //BACKGROUND & ICONS
        try {
            menu = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/WHG.png")));

            playIcon = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/buttons/playNormal.png")));
            playHover = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/buttons/playHover.png")));
            playClick = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/buttons/playClick.png")));

            quitIcon = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/buttons/quitNormal.png")));
            quitHover = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/buttons/quitHover.png")));
            quitClick = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/buttons/quitClick.png")));

            creditsIcon = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/buttons/creditsNormal.png")));
            creditsHover = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/buttons/creditsHover.png")));
            creditsClick = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/buttons/creditsClick.png")));

            howToPlayIcon = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/buttons/howtoplayNormal.png")));
            howToPlayHover = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/buttons/howtoplayHover.png")));
            howToPlayClick = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/buttons/howtoplayClick.png")));

            musicIcon = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/buttons/musicNormal.png")));
            musicOff = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/buttons/musicOff.png")));


        } catch (IOException e) {
            e.printStackTrace();
        }

        //BUTTONS

        //Play button
        play = new JButton();
        play.setBounds(130,375,135,70);
        play.setIcon(new ImageIcon(playIcon));
        play.addActionListener(e -> playButtonPressed());
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                play.setIcon(new ImageIcon(playHover));
                TinySound.init();
                TinySound.loadSound(ClassLoader.getSystemResource("wh/game/sounds/hoverButton.wav")).play(0.5f);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                play.setIcon(new ImageIcon(playIcon));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                play.setIcon(new ImageIcon(playClick));
                TinySound.init();
                TinySound.loadSound(ClassLoader.getSystemResource("wh/game/sounds/clickButton.wav")).play(0.5f);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                play.setIcon(new ImageIcon(playIcon));
            }
        } );


        //Credits button
        credits = new JButton();
        credits.setBounds(470 ,375,135,70);
        credits.setIcon(new ImageIcon(creditsIcon));
        credits.addActionListener(e -> creditsButtonPressed());
        credits.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                credits.setIcon(new ImageIcon(creditsHover));
                TinySound.init();
                TinySound.loadSound(ClassLoader.getSystemResource("wh/game/sounds/hoverButton.wav")).play(0.5f);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                credits.setIcon(new ImageIcon(creditsIcon));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                credits.setIcon(new ImageIcon(creditsClick));
                TinySound.init();
                TinySound.loadSound(ClassLoader.getSystemResource("wh/game/sounds/clickButton.wav")).play(0.5f);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                credits.setIcon(new ImageIcon(creditsIcon));
            }
        } );

        //HowToPlay button
        howToPlay = new JButton();
        howToPlay.setBounds(300 ,375,135,70);
        howToPlay.setIcon(new ImageIcon(howToPlayIcon));
        howToPlay.addActionListener(e -> howToPlayPressed());
        howToPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                howToPlay.setIcon(new ImageIcon(howToPlayHover));
                TinySound.init();
                TinySound.loadSound(ClassLoader.getSystemResource("wh/game/sounds/hoverButton.wav")).play(0.5f);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                howToPlay.setIcon(new ImageIcon(howToPlayIcon));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                howToPlay.setIcon(new ImageIcon(howToPlayClick));
                TinySound.init();
                TinySound.loadSound(ClassLoader.getSystemResource("wh/game/sounds/clickButton.wav")).play(0.5f);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                howToPlay.setIcon(new ImageIcon(howToPlayIcon));
            }
        } );

        //Quit button
        quit = new JButton();
        quit.setBounds(640 ,375,135,70);
        quit.setIcon(new ImageIcon(quitIcon));
        quit.addActionListener(e -> quitButtonPressed());
        quit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                quit.setIcon(new ImageIcon(quitHover));
                TinySound.init();
                TinySound.loadSound(ClassLoader.getSystemResource("wh/game/sounds/hoverButton.wav")).play(0.5f);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                quit.setIcon(new ImageIcon(quitIcon));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                quit.setIcon(new ImageIcon(quitClick));
                TinySound.init();
                TinySound.loadSound(ClassLoader.getSystemResource("wh/game/sounds/clickButton.wav")).play(0.5f);
            }
        } );

        //Music button
        music = new JButton();
        music.setBounds(903,5,52,56);
        music.setIcon(new ImageIcon(musicIcon));
        music.addActionListener(e -> musicButtonCounter++);
        music.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if(musicButtonCounter % 2 == 0) {
                    music.setIcon(new ImageIcon(musicIcon));
                }
                else if(musicButtonCounter % 2 == 1) {
                    music.setIcon(new ImageIcon(musicOff));
                }

            }
        } );

        //Button transparent
        play.setOpaque(false);
        play.setContentAreaFilled(false);
        play.setBorderPainted(false);
        quit.setOpaque(false);
        quit.setContentAreaFilled(false);
        quit.setBorderPainted(false);
        credits.setOpaque(false);
        credits.setContentAreaFilled(false);
        credits.setBorderPainted(false);
        howToPlay.setOpaque(false);
        howToPlay.setContentAreaFilled(false);
        howToPlay.setBorderPainted(false);
        music.setOpaque(false);
        music.setContentAreaFilled(false);
        music.setBorderPainted(false);
    }

    public void playButtonPressed(){
        playButton =true;
    }

    public void creditsButtonPressed(){
        creditsBool = true;
    }

    public void howToPlayPressed(){
        howToPlayBool = true;
    }

    public void quitButtonPressed(){
        System.exit(0);
    }


    @Override
    public void paint(Graphics g) {
        g.drawImage(menu, 0, 0, 960, 640, null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
