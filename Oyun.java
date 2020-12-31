package wh.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Oyun extends JPanel implements ActionListener{
	
	Timer timer = new Timer(10,this);
	ArrayList<Level> levels;
	private Level1 level;
	Player player;
	
	
	public Oyun() {
		
		levels = new ArrayList<Level>();
		setPreferredSize(new Dimension(640,480));
		level = new Level1();
		timer.start();
		player = new Player(32,224,16,16);
		addKeyListener(player);
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		g.drawImage(level.getLevel1(), 0, 0,640,480, null);
		player.drawPlayer(g);
		
	}
	
	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		player.update();
		repaint();
	}
	
	
	
}
