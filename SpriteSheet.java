package wh.game;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private final BufferedImage sheet;
	
	public SpriteSheet (BufferedImage sheet) {
		this.sheet = sheet;
	}

	//Determine which part should be taken from sprite image
	public BufferedImage getSprite(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
}
