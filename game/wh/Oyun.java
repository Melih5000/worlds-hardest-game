package game.wh;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Oyun extends JPanel implements ActionListener{

	Timer timer = new Timer(10,this);
	private Level1 level1;
	Player player = new Player(40,216,16,16);


	public Oyun() {

		setPreferredSize(new Dimension(640,480));
		level1 = new Level1();
		timer.start();
		addKeyListener(player);

	}


	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		g.drawImage(level1.getLevel1(), 0, 0,640,480, null);
		level1.grapCaller(g);
		player.drawPlayer(g);
		level1.levelMove(80,128);



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
		level1.enemyUpdate();
		repaint();

	}



}
