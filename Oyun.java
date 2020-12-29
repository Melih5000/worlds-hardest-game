import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Oyun extends JPanel implements KeyListener,ActionListener{
	
	Timer timer = new Timer(5,this);
	
	private BufferedImage image1,image2,image3,image4,image5,image6,image7,image8,image9,image10,image11,image12,image13,
	image14,image15;
	private int playerX=32;
	private int playerY=224;
	private int change=10;
	
	
	public Oyun() {
		
		try {
			image1 = ImageIO.read(new FileImageInputStream(new File("background2.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			image2 = ImageIO.read(new FileImageInputStream(new File("duz.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			image3 = ImageIO.read(new FileImageInputStream(new File("floor1.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			image4 = ImageIO.read(new FileImageInputStream(new File("golgeduz.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			image5 = ImageIO.read(new FileImageInputStream(new File("kose.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			image6 = ImageIO.read(new FileImageInputStream(new File("lkose1.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			image7 = ImageIO.read(new FileImageInputStream(new File("lkose2.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			image8 = ImageIO.read(new FileImageInputStream(new File("saggolge.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			image9 = ImageIO.read(new FileImageInputStream(new File("sagkapi.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			image10 = ImageIO.read(new FileImageInputStream(new File("solgolge.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			image11 = ImageIO.read(new FileImageInputStream(new File("solkapi.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			image12 = ImageIO.read(new FileImageInputStream(new File("solkose.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			image13 = ImageIO.read(new FileImageInputStream(new File("t.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			image14 = ImageIO.read(new FileImageInputStream(new File("yant.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			image15 = ImageIO.read(new FileImageInputStream(new File("player.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		timer.start();
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		//Background
		for(int i=0; i<960;i+=32) {
			for(int j=0; j<640;j+=32) {
				g.drawImage(image1, i, j, image1.getWidth()*2,image1.getHeight()*2,this);
			}
		}
		
		//Floor
		for(int i=32; i<225;i+=32) {
			for(int j=192; j<=256;j+=32) {
				g.drawImage(image3, i, j, image3.getWidth()*2,image3.getHeight()*2,this);
			}
		}
		for(int i=160; i<485;i+=32) {
			for(int j=32; j<165;j+=32) {
				g.drawImage(image3, i, j, image3.getWidth()*2,image3.getHeight()*2,this);
			}
		}
		for(int i=352; i<485;i+=32) {
			for(int j=192; j<385;j+=32) {
				g.drawImage(image3, i, j, image3.getWidth()*2,image3.getHeight()*2,this);
			}
		}
		for(int i=352; i<740;i+=32) {
			for(int j=416; j<580;j+=32) {
				g.drawImage(image3, i, j, image3.getWidth()*2,image3.getHeight()*2,this);
			}
		}
		for(int i=608; i<740;i+=32) {
			for(int j=224; j<385;j+=32) {
				g.drawImage(image3, i, j, image3.getWidth()*2,image3.getHeight()*2,this);
			}
		}
		for(int i=768; i<=896;i+=32) {
			for(int j=224; j<325;j+=32) {
				g.drawImage(image3, i, j, image3.getWidth()*2,image3.getHeight()*2,this);
			}
		}
		
		//Walls
		for(int j=160; j<=256;j+=32) {
			g.drawImage(image10, 0, j, image3.getWidth()*2,image3.getHeight()*2,this);
		}
		
		g.drawImage(image5, 0, 288, image3.getWidth()*2,image3.getHeight()*2,this);
		
		for(int j=32; j<=128;j+=32) {
			g.drawImage(image2, j, 160, image3.getWidth()*2,image3.getHeight()*2,this);
		}
		for(int j=32; j<=224;j+=32) {
			g.drawImage(image4, j, 288, image3.getWidth()*2,image3.getHeight()*2,this);
		}
		for(int j=0; j<=128;j+=32) {
			g.drawImage(image10, 128, j, image3.getWidth()*2,image3.getHeight()*2,this);
		}
		for(int j=160; j<=480;j+=32) {
			g.drawImage(image2, j, 0, image3.getWidth()*2,image3.getHeight()*2,this);
		}
		for(int j=0; j<=352;j+=32) {
			g.drawImage(image8, 512, j, image3.getWidth()*2,image3.getHeight()*2,this);
		}
		for(int j=512; j<=576;j+=32) {
			g.drawImage(image2, j, 384, image3.getWidth()*2,image3.getHeight()*2,this);
		}
		
		g.drawImage(image12, 256, 288, image3.getWidth()*2,image3.getHeight()*2,this);
		
		g.drawImage(image8, 256, 256, image3.getWidth()*2,image3.getHeight()*2,this);
		g.drawImage(image8, 256, 224, image3.getWidth()*2,image3.getHeight()*2,this);
		
		g.drawImage(image6, 256, 192, image3.getWidth()*2,image3.getHeight()*2,this);
		g.drawImage(image4, 288, 192, image3.getWidth()*2,image3.getHeight()*2,this);
		g.drawImage(image7, 320, 192, image3.getWidth()*2,image3.getHeight()*2,this);
		
		for(int j=224; j<=576;j+=32) {
			g.drawImage(image10, 320, j, image3.getWidth()*2,image3.getHeight()*2,this);
		}
		
		g.drawImage(image5, 320, 608, image3.getWidth()*2,image3.getHeight()*2,this);
		
		for(int j=352; j<=736;j+=32) {
			g.drawImage(image4, j, 608, image3.getWidth()*2,image3.getHeight()*2,this);
		}
		
		g.drawImage(image12, 768, 608, image3.getWidth()*2,image3.getHeight()*2,this);
		
		for(int j=576; j>=384;j-=32) {
			g.drawImage(image8, 768, j, image3.getWidth()*2,image3.getHeight()*2,this);
		}
		
		g.drawImage(image6, 768, 352, image3.getWidth()*2,image3.getHeight()*2,this);
		
		for(int j=800; j<=896;j+=32) {
			g.drawImage(image4, j, 352, image3.getWidth()*2,image3.getHeight()*2,this);
		}
		
		g.drawImage(image12, 928, 352, image3.getWidth()*2,image3.getHeight()*2,this);
		
		for(int j=320; j>=192;j-=32) {
			g.drawImage(image8, 928, j, image3.getWidth()*2,image3.getHeight()*2,this);
		}
		
		g.drawImage(image2, 896, 192, image3.getWidth()*2,image3.getHeight()*2,this);
		
		g.drawImage(image9, 864, 192, image3.getWidth()*2,image3.getHeight()*2,this);
		g.drawImage(image11, 832, 192, image3.getWidth()*2,image3.getHeight()*2,this);
		
		for(int j=800; j>=608;j-=32) {
			g.drawImage(image2, j, 192, image3.getWidth()*2,image3.getHeight()*2,this);
		}
		for(int j=192; j<=352;j+=32) {
			g.drawImage(image10, 576, j, image3.getWidth()*2,image3.getHeight()*2,this);
		}
		
		g.drawImage(image13, 800, 192, image3.getWidth()*2,image3.getHeight()*2,this);
		g.drawImage(image13, 896, 192, image3.getWidth()*2,image3.getHeight()*2,this);
		g.drawImage(image13, 320, 0, image3.getWidth()*2,image3.getHeight()*2,this);
		
		g.drawImage(image14, 352, 320, image3.getWidth()*2,image3.getHeight()*2,this);
		g.drawImage(image14, 352, 480, image3.getWidth()*2,image3.getHeight()*2,this);
		
		g.drawImage(image15, playerX, playerY, image15.getWidth(),image15.getHeight(),this);
	}
	
	


	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int c = e.getKeyCode();
		
		if(c == KeyEvent.VK_LEFT) {
			if(playerX <= 0) {
				playerX = 0;
			}
			else
				playerX -= change;
		}
		if(c == KeyEvent.VK_RIGHT) {
			if(playerX >= 970) {
				playerX = 970;
			}
			else
				playerX += change;
		}
		if(c == KeyEvent.VK_UP) {
			if(playerY <= 0) {
				playerY = 0;
			}
			else
				playerY -= change;
		}
		if(c == KeyEvent.VK_DOWN) {
			if(playerY >= 675) {
				playerY = 675;
			}
			else
				playerY += change;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}
	
	
	
}
