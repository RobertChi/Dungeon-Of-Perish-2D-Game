package Joc.Tiles;

import Joc.Graphics.Assets;

public class WallCornerToRight extends Tile{
    public WallCornerToRight(int id){super(Assets.wctright,id);}
    @Override
    public boolean isSolid()
    {
        return true;
    }
}
