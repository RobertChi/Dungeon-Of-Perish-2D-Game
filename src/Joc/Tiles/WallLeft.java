package Joc.Tiles;

import Joc.Graphics.Assets;

public class WallLeft extends Tile
{
    public WallLeft(int id)
    {
        super(Assets.wleft, id);
    }
    @Override
    public boolean isSolid()
    {
        return true;
    }

}
