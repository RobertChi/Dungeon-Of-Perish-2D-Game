package Joc.Tiles;

import Joc.Graphics.Assets;

public class WallDouble extends Tile{
    public WallDouble (int id) {super(Assets.wdouble,id);}
    @Override
    public boolean isSolid()
    {
        return true;
    }
}
