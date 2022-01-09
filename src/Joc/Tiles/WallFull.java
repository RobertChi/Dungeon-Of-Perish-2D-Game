package Joc.Tiles;

import Joc.Graphics.Assets;

public class WallFull extends Tile{
    public WallFull (int id){super(Assets.wfull,id);}
    @Override
    public boolean isSolid()
    {
        return true;
    }
}
