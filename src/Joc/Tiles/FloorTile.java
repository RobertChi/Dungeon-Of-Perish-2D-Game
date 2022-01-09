package Joc.Tiles;
import Joc.Graphics.Assets;

public class FloorTile extends Tile
{
    public FloorTile(int id)
    {
        super(Assets.f1, id);
    }
    @Override
    public boolean isSolid()
    {
        return false;
    }
}
