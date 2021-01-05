package game.wh;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Oyun extends JPanel implements ActionListener{

	Timer timer = new Timer(10,this);
	private Level1 level1;
	Player player = new Player(20,480,16,16);
	int levelCounter = 1;
	Font customFont;


	public Oyun() {

		setPreferredSize(new Dimension(960,640));
		level1 = new Level1();
		timer.start();
		addKeyListener(player);
		try {

			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resource/fonts/8bit.ttf")).deriveFont(12f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);
		} catch (IOException e) {
			e.printStackTrace();
		} catch(FontFormatException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		if(!level1.isPassed()) {
			if (levelCounter == 1) {
				g.drawImage(level1.getLevel1(), 0, 0, 960, 640, null);
			}

			level1.grapCaller(g);
			player.drawPlayer(g);
			level1.levelMove();
		}
		g.setColor(new Color(30, 30, 30, 200));
		g.fillRect(0,608,120,32);
		g.setColor(new Color(170,170,170,255));
		g.setFont(customFont);
		if(player.getDeathCounter() != 0)
			g.drawString("DEATHS  " + player.getDeathCounter(),8,629);
		else
			g.drawString("DEATHS  O",8,629);

	}

	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		player.update(level1);
		level1.update();
		repaint();

	}



}
