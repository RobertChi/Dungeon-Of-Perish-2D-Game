package Joc.Tiles;

import Joc.Graphics.Assets;
public class WallTile extends Tile {
    public WallTile(int id) { super(Assets.w1, id); }
    @Override
    public boolean isSolid()
    {
        return true;
    }
}
