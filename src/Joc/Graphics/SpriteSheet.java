package Joc.Graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet
{
    private BufferedImage spriteSheet;
    private int    tileWidth   = 16;
    private int    tileHeight  = 16;

    public SpriteSheet(BufferedImage buffImg)
    {
        this.spriteSheet=buffImg;
    }


    public BufferedImage crop(int x, int y, int width, int height){
        return spriteSheet.getSubimage(x*width, y*height, width, height);
    }

}
