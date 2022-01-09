package Joc.Tiles;

import Joc.Graphics.Assets;


public class WallRight extends Tile
{
    public WallRight(int id)
    {
        super(Assets.wright, id);
    }
    @Override
    public boolean isSolid()
    {
        return true;
    }
}
