package Joc.Tiles;
import Joc.Graphics.Assets;

public class WallCornerRight extends Tile{
    public  WallCornerRight(int id){super(Assets.wcright,id);}
    @Override
    public boolean isSolid()
    {
        return true;
    }
}
