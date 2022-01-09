package Joc.Tiles;

import Joc.Graphics.Assets;

/*! \class public class WallCornerLeft extends Tile
    \brief Abstractizeaza notiunea de dala de tip tree.
 */
public class WallCornerLeft extends Tile
{
    public WallCornerLeft(int id)
    {
        super(Assets.wcleft, id);
    }
    @Override
    public boolean isSolid()
    {
        return true;
    }
}
