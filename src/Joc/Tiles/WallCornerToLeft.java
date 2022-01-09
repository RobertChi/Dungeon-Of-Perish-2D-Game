package Joc.Tiles;
import Joc.Graphics.Assets;

public class WallCornerToLeft extends Tile{
    public WallCornerToLeft (int id){super(Assets.wctleft,id);}
    @Override
    public boolean isSolid()
    {
        return true;
    }
}
