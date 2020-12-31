package wh.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class Level1 extends Level{
	
	private BufferedImage level1;
	
	public Level1() {
		try {
			level1 = ImageIO.read(new FileImageInputStream(new File("adsýz.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getLevel1() {
		return level1;
	}
	
	
}
