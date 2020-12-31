package wh.game;

import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class OyunEkrani extends JFrame {

	public OyunEkrani(String title) throws HeadlessException{
		super(title);
		
	}
	public static void main(String[] args) {
		
	OyunEkrani ekran = new OyunEkrani("World's Hardest Game: Javavengers Edition");
	ekran.setResizable(false);
	ekran.setFocusable(false);

	ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	Oyun oyun = new Oyun();
	
	oyun.requestFocus();
	
	
	oyun.setFocusable(true);
	oyun.setFocusTraversalKeysEnabled(false);
	
	ekran.add(oyun);
	ekran.pack();
	
	ekran.setLocationRelativeTo(null);
	ekran.setVisible(true);
	
	}

}