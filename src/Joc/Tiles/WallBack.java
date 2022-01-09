package Joc.Tiles;

import Joc.Graphics.Assets;

public class WallBack extends Tile{
    public WallBack (int id){super(Assets.wback,id);}
    @Override
    public boolean isSolid()
    {
        return true;
    }
}
