package Joc.Tiles;

import Joc.Graphics.Assets;

public class WDTRight extends Tile{
        public WDTRight(int id){super(Assets.wdtright,id);}
        @Override
        public boolean isSolid() {return true;}
}
