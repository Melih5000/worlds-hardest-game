package game.wh;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage sheet;

    public SpriteSheet (BufferedImage sheet) {
        this.sheet = sheet;
    }

    public BufferedImage getSprite(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }
}
