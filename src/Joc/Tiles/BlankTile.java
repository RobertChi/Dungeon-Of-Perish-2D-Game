package Joc.Tiles;

import Joc.Graphics.Assets;

public class BlankTile extends Tile{
    public BlankTile(int id)
    {
        super(Assets.blank, id);
    }
    @Override
    public boolean isSolid()
    {
        return true;
    }
}
