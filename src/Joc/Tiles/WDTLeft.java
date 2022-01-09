package Joc.Tiles;

import Joc.Graphics.Assets;

public class WDTLeft extends Tile {
        public WDTLeft(int id){super(Assets.wdtleft,id);}
        @Override
        public boolean isSolid() {return true;}
}
