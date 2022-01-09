package Joc.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    //STATIC STUFF HERE

    public static Tile[] tiles = new Tile[256];
    public static Tile blank            = new BlankTile(0);
    public static Tile floorTile        = new FloorTile(1);
    public static Tile wallTile         = new WallTile(2);
    public static Tile wallright        = new WallRight(3);
    public static Tile wallleft         = new WallLeft(4);
    public static Tile wallcornerright  = new WallCornerRight(5);
    public static Tile wallcornerleft   = new WallCornerLeft(6);
    public static Tile wallctoright     = new WallCornerToRight(8);
    public static Tile wallctoleft      = new WallCornerToLeft(7);
    public static Tile walldouble       = new WallDouble(9);
    public static Tile wallfull         = new WallFull(10);
    public static Tile wallback         = new WallBack(11);
    public static Tile walldtleft       = new WDTLeft(13);
    public static Tile walldtright      = new WDTRight(12);

    //CLASS

    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick(){ }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean isSolid(){
        return false;
    }

    public int getId(){
        return id;
    }

}