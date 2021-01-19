package wh.game;

import kuusisto.tinysound.TinySound;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;


public class Game extends JPanel implements ActionListener{
	
	Timer timer = new Timer(10,this);
	private final Level1 level1;
	private final Level2 level2;
	private final Level3 level3;
	private ArrayList<Level> levels;
	Player player = new Player(20,480,16,16);
	Font customFont, customFont2, customFont3;
	JButton pause, ok, leave, backToMenu;
	boolean isPaused = false, goToMenu = false, continueGame = false;
	boolean minigameOn = false, gameFinished = false;
	BufferedImage win;
	private Image menuIcon, menuHover, menuClick;
	private long startTime, stopTime;
	ArrayList<Long> times = new ArrayList<>();


	
	public Game() {

		//Screen Size
		setPreferredSize(new Dimension(960,640));

		//Creating level objects and put them into Level ArrayList
		levels = new ArrayList<>();
		level1 = new Level1();
		levels.add(level1);
		level2 = new Level2();
		levels.add(level2);
		level3 = new Level3();
		levels.add(level3);

		timer.start();
		addKeyListener(player);

		// Generating customFonts and getting images
		try {

			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/wh/game/resource/fonts/8bit.ttf")).deriveFont(12f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);

			customFont2 = Font.createFont(Font.TRUETYPE_FONT, new File("src/wh/game/resource/fonts/8bit.ttf")).deriveFont(36f);
			GraphicsEnvironment gee = GraphicsEnvironment.getLocalGraphicsEnvironment();
			gee.registerFont(customFont2);

			customFont3 = Font.createFont(Font.TRUETYPE_FONT, new File("src/wh/game/resource/fonts/8bit.ttf")).deriveFont(24f);
			GraphicsEnvironment geeE = GraphicsEnvironment.getLocalGraphicsEnvironment();
			geeE.registerFont(customFont3);

			win = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/win.png")));

			menuIcon = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/buttons/menuNormal.png")));
			menuHover = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/buttons/menuHover.png")));
			menuClick = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/buttons/menuClick.png")));

		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}

		// Pause Button
		pause = new JButton();
		pause.setBounds(0,0,78,32);
		pause.setOpaque(false);
		pause.setContentAreaFilled(false);
		pause.setBorderPainted(false);
		pause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				isPaused = true;
			}
		});
		pause.setFocusable(false);

		// OKAY Button
		ok = new JButton();
		ok.setBounds(400,330,159,50);
		ok.setOpaque(false);
		ok.setContentAreaFilled(false);
		ok.setBorderPainted(false);
		ok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				isPaused = false;
				continueGame = true;
			}
		});
		ok.setFocusable(false);

		// Leave Button
		leave = new JButton();
		leave.setBounds(380,400,195,50);
		leave.setOpaque(false);
		leave.setContentAreaFilled(false);
		leave.setBorderPainted(false);
		leave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				goToMenu = true;
			}
		});
		leave.setFocusable(false);

		// Back To Menu Button
		backToMenu = new JButton();
		backToMenu.setBounds(414,515,135,70);
		backToMenu.setIcon(new ImageIcon(menuIcon));
		backToMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				goToMenu = true;
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				backToMenu.setIcon(new ImageIcon(menuHover));
				TinySound.init();
				TinySound.loadSound(ClassLoader.getSystemResource("wh/game/sounds/hoverButton.wav")).play();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				backToMenu.setIcon(new ImageIcon(menuIcon));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				backToMenu.setIcon(new ImageIcon(menuClick));
				TinySound.init();
				TinySound.loadSound(ClassLoader.getSystemResource("wh/game/sounds/clickButton.wav")).play();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				backToMenu.setIcon(new ImageIcon(menuIcon));
			}
		});
		backToMenu.setOpaque(false);
		backToMenu.setContentAreaFilled(false);
		backToMenu.setBorderPainted(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);


		// Level 1 Background & Walls & Movements
		if(!player.getLevel1().isPassed) {

			g.drawImage(level1.getLevel(), 0, 0, 960, 640, null);

			level1.graphicCaller(g);
			player.drawPlayer(g);
			level1.levelMove();

			if (player.minigame1){
				minigameOn = true;
			}
			else
				minigameOn = false;

		}

		// Level 2 Background & Walls & Movements
		else if(!player.getLevel2().isPassed) {

			g.drawImage(level2.getLevel(), 0, 0, 960, 640, null);

			level2.graphicCaller(g);
			player.drawPlayer(g);
			level2.levelMove();

		}

		// Level 3 Background & Walls & Movements
		else if(!player.getLevel3().isPassed){

			if(player.isOnceMng3()){
				g.drawImage(level3.levelKapili, 0, 0, 960, 640, null);
			}

			if (player.minigame3){
				minigameOn = true;
			}

			if(!player.isOnceMng3()){
				level3.unlockDoor();
				g.drawImage(level3.levelKapisiz, 0, 0, 960, 640, null);
			}

			level3.graphicCaller(g);
			player.drawPlayer(g);
			level3.levelMove();

		}

		// Finish Method
		if(player.gameFinished){
			gameFinished = true;
		}


		// Indicator of button & counter
		if(!gameFinished) {

			//DEATH COUNTER
			g.setColor(new Color(30, 30, 30, 200));
			g.fillRect(0, 608, 120, 32);
			g.setColor(new Color(170, 170, 170, 255));
			g.setFont(customFont);
			if (player.getDeathCounter() != 0)
				g.drawString("DEATHS  " + player.getDeathCounter(), 8, 629);
			else
				g.drawString("DEATHS  O", 8, 629);

			//PAUSE
			g.setColor(new Color(30, 30, 30, 200));
			g.fillRect(0, 0, 78, 32);
			g.setColor(new Color(170, 170, 170, 255));
			g.setFont(customFont);
			g.drawString("PAUSE", 8, 21);

			//Pause Screen
			if (isPaused) {
				g.setColor(new Color(30, 30, 30, 225));
				g.fillRect(240, 160, 480, 320);
				g.setColor(new Color(170, 170, 170, 255));
				g.setFont(customFont2);
				g.drawString("YOU CANNOT", 310, 250);
				g.drawString(" STOP TIME", 310, 290);
				g.drawString("OKAY", 410, 370);
				g.drawString("LEAVE", 390, 440);
				//BUTTON
				g.drawRect(400, 330, 159, 50);
				g.drawRect(380, 400, 195, 50);

			}
		}

		// GAME END SCREEN
		else{
			g.drawImage(win,0,0,null);
			g.setColor(new Color(58,39,20));
			g.setFont(customFont3);
			g.drawString(""+player.getDeathCounter(),456,290);
			g.drawString(""+(timeCalculator()/60)+" m "+(timeCalculator()%60)+" s",395,365);
			g.drawString(""+player.getStepCounter(),425,447);
		}

	}



	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		//Checking player collision and deaths for each level & level properties that must be checked
		if(!player.getLevel1().isPassed) {
			player.update(level1);
			level1.update();
		}
		else if(!player.getLevel2().isPassed){
			player.update(level2);
			level2.update();
		}
		else if(!player.getLevel3().isPassed){
			player.update(level3);
			level3.update();
		}
		repaint();

	}

	public long timeCalculator(){
		long time=0;
		for (Long t:times) {
			time += t;
		}
		time += stopTime-startTime;
		time /= 1000000000;
		return time;
	}

	// Restarting coins after game finished
	public void coinRestart(){
		for (Level l:levels) {
			l.refreshFinishLine();
			for (Coin c:l.coins) {
				c.setCollected(false);
			}
		}
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public void setStopTime(long stopTime) {
		this.stopTime = stopTime;
	}

	public long getStartTime() {
		return startTime;
	}

	public ArrayList<Long> getTimes() {
		return times;
	}
}
