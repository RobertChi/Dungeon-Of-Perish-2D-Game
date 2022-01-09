package Joc.Maps;

import Joc.Entity.Entity;
import Joc.Entity.EntityManager;
import Joc.Entity.Movable.Creature;
import Joc.Entity.Movable.Player;
import Joc.Entity.Movable.Skeleton;
import Joc.Entity.Movable.Zombie;
import Joc.Entity.Statics.Coin;
import Joc.Entity.Statics.GoldenChest;
import Joc.Entity.Statics.Potion;
import Joc.Entity.Statics.SilverChest;
import Joc.Handler;
import Joc.States.State;
import Joc.Tiles.Tile;
import Joc.Utils.Utils;

import java.awt.*;

public class World {
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    private int currentlvl;
    private int tempscore;
    private boolean playerdead=false;
    private EntityManager entityManager;

    public World(Handler handler){
        this.handler = handler;
        Lvl1();
    }

    public void tick(){
        entityManager.tick();
        if(!checkMonsters()) {
            if(currentlvl==1) {
                tempscore=entityManager.getPlayer().score;
                if((handler.getMouseManager().isleftPressed() && handler.getMouseManager().getMouseX()>=350 && handler.getMouseManager().getMouseX()<=600
                        && handler.getMouseManager().getMouseY()>=800 && handler.getMouseManager().getMouseY()<=870)) {
                    Lvl2();
                }
            }
            if(currentlvl==2){
                tempscore=entityManager.getPlayer().score;
                if((handler.getMouseManager().isleftPressed() && handler.getMouseManager().getMouseX()>=350 && handler.getMouseManager().getMouseX()<=600
                        && handler.getMouseManager().getMouseY()>=50 && handler.getMouseManager().getMouseY()<=120)) {
                    State.setState(handler.getGame().winState);
                }
            }
            handler.getMouseManager().reset();
        }
        if(!entityManager.getPlayer().isActive()){
            playerdead=true;
            if((handler.getMouseManager().isleftPressed() && handler.getMouseManager().getMouseX()>=350 && handler.getMouseManager().getMouseX()<=600
                    && handler.getMouseManager().getMouseY()>=800 && handler.getMouseManager().getMouseY()<=870)) {
                handler.getGame().stop();
            }
            if((handler.getMouseManager().isleftPressed() && handler.getMouseManager().getMouseX()>=350 && handler.getMouseManager().getMouseX()<=600
                    && handler.getMouseManager().getMouseY()>=670 && handler.getMouseManager().getMouseY()<=740)) {
                playerdead=false;
                Lvl1();
            }
        }
    }

    public void render(Graphics g){
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for(int y = yStart;y < yEnd;y++){
            for(int x = xStart;x < xEnd;x++){
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        entityManager.render(g);

    }

    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.blank;

        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
            return Tile.floorTile;
        return t;
    }

    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for(int y = 0;y < height;y++){
            for(int x = 0;x < width;x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public int getCurrentlvl() {
        return currentlvl;
    }

    public void Lvl1()
    {
        loadWorld("res/maps/lvl1");

        currentlvl=1;
        entityManager=new EntityManager(handler, new Player(handler,100,100));

        //CHESTS
        entityManager.addEntity(new GoldenChest(handler,11*64,10*64));
        entityManager.addEntity(new GoldenChest(handler,23*64,16*64));
        entityManager.addEntity(new GoldenChest(handler,29*64,16*64));
        entityManager.addEntity(new SilverChest(handler,5*64,6*64));
        entityManager.addEntity(new SilverChest(handler,5*64,18*64));
        entityManager.addEntity(new SilverChest(handler,5*64,22*64));
        entityManager.addEntity(new SilverChest(handler,17*64,18*64));
        entityManager.addEntity(new SilverChest(handler,23*64,24*64));
        entityManager.addEntity(new SilverChest(handler,31*64,28*64));
        entityManager.addEntity(new SilverChest(handler,29*64,10*64));
        //POTIONS
        entityManager.addEntity(new Potion(handler,3*64,15*64));
        entityManager.addEntity(new Potion(handler,5*64,9*64));
        entityManager.addEntity(new Potion(handler,9*64,9*64));
        entityManager.addEntity(new Potion(handler,19*64,8*64));
        entityManager.addEntity(new Potion(handler,23*64,10*64));
        entityManager.addEntity(new Potion(handler,27*64,17*64));
        entityManager.addEntity(new Potion(handler,19*64,20*64));
        entityManager.addEntity(new Potion(handler,12*64,22*64));
        entityManager.addEntity(new Potion(handler,3*64,28*64));
        entityManager.addEntity(new Potion(handler,22*64,26*64));
        //COINS
        entityManager.addEntity(new Coin(handler,3*64,2*64));
        entityManager.addEntity(new Coin(handler,3*64,3*64));
        entityManager.addEntity(new Coin(handler,3*64,4*64));
        entityManager.addEntity(new Coin(handler,3*64,5*64));
        entityManager.addEntity(new Coin(handler,3*64,6*64));
        entityManager.addEntity(new Coin(handler,3*64,18*64));
        entityManager.addEntity(new Coin(handler,3*64,19*64));
        entityManager.addEntity(new Coin(handler,3*64,20*64));
        entityManager.addEntity(new Coin(handler,4*64,20*64));
        entityManager.addEntity(new Coin(handler,5*64,20*64));
        entityManager.addEntity(new Coin(handler,19*64,10*64));
        entityManager.addEntity(new Coin(handler,12*64,5*64));
        entityManager.addEntity(new Coin(handler,13*64,5*64));
        entityManager.addEntity(new Coin(handler,15*64,5*64));
        entityManager.addEntity(new Coin(handler,16*64,5*64));
        entityManager.addEntity(new Coin(handler,7*64,10*64));
        entityManager.addEntity(new Coin(handler,7*64,11*64));
        entityManager.addEntity(new Coin(handler,7*64,13*64));
        entityManager.addEntity(new Coin(handler,7*64,15*64));
        entityManager.addEntity(new Coin(handler,7*64,16*64));
        entityManager.addEntity(new Coin(handler,11*64,12*64));
        entityManager.addEntity(new Coin(handler,11*64,13*64));
        entityManager.addEntity(new Coin(handler,11*64,14*64));
        entityManager.addEntity(new Coin(handler,11*64,15*64));
        entityManager.addEntity(new Coin(handler,21*64,11*64));
        entityManager.addEntity(new Coin(handler,21*64,12*64));
        entityManager.addEntity(new Coin(handler,21*64,13*64));
        entityManager.addEntity(new Coin(handler,3*64,23*64));
        entityManager.addEntity(new Coin(handler,3*64,24*64));
        entityManager.addEntity(new Coin(handler,3*64,25*64));
        entityManager.addEntity(new Coin(handler,12*64,24*64));
        entityManager.addEntity(new Coin(handler,12*64,26*64));
        entityManager.addEntity(new Coin(handler,10*64,25*64));
        entityManager.addEntity(new Coin(handler,11*64,25*64));
        entityManager.addEntity(new Coin(handler,13*64,25*64));
        entityManager.addEntity(new Coin(handler,14*64,25*64));
        entityManager.addEntity(new Coin(handler,19*64,27*64));
        entityManager.addEntity(new Coin(handler,19*64,28*64));
        entityManager.addEntity(new Coin(handler,19*64,20*64));
        entityManager.addEntity(new Coin(handler,24*64,19*64));
        entityManager.addEntity(new Coin(handler,24*64,20*64));
        entityManager.addEntity(new Coin(handler,24*64,21*64));
        entityManager.addEntity(new Coin(handler,29*64,19*64));
        entityManager.addEntity(new Coin(handler,29*64,20*64));
        entityManager.addEntity(new Coin(handler,29*64,21*64));
        entityManager.addEntity(new Coin(handler,29*64,22*64));
        entityManager.addEntity(new Coin(handler,31*64,19*64));
        entityManager.addEntity(new Coin(handler,31*64,20*64));
        entityManager.addEntity(new Coin(handler,31*64,21*64));
        entityManager.addEntity(new Coin(handler,31*64,22*64));
        entityManager.addEntity(new Coin(handler,23*64,13*64));
        entityManager.addEntity(new Coin(handler,23*64,14*64));
        entityManager.addEntity(new Coin(handler,23*64,15*64));
        entityManager.addEntity(new Coin(handler,23*64,6*64));
        entityManager.addEntity(new Coin(handler,24*64,6*64));
        entityManager.addEntity(new Coin(handler,25*64,6*64));
        //SKELETONS
        entityManager.addEntity(new Skeleton(handler,3*64,12*64));
        entityManager.addEntity(new Skeleton(handler,9*64,20*64));
        /*
        entityManager.addEntity(new Skeleton(handler,7*64,12*64));
        entityManager.addEntity(new Skeleton(handler,7*64,14*64));
        entityManager.addEntity(new Skeleton(handler,11*64,5*64));
        entityManager.addEntity(new Skeleton(handler,14*64,5*64));
        entityManager.addEntity(new Skeleton(handler,17*64,5*64));
        entityManager.addEntity(new Skeleton(handler,19*64,16*64));
        entityManager.addEntity(new Skeleton(handler,9*64,25*64));
        entityManager.addEntity(new Skeleton(handler,12*64,25*64));
        entityManager.addEntity(new Skeleton(handler,15*64,25*64));
        entityManager.addEntity(new Skeleton(handler,4*64,28*64));
        entityManager.addEntity(new Skeleton(handler,26*64,24*64));
        entityManager.addEntity(new Skeleton(handler,23*64,20*64));
        entityManager.addEntity(new Skeleton(handler,25*64,20*64));
        entityManager.addEntity(new Skeleton(handler,30*64,26*64));
        entityManager.addEntity(new Skeleton(handler,30*64,8*64));
        entityManager.addEntity(new Skeleton(handler,24*64,5*64));
        */



        entityManager.getPlayer().setX(spawnX*64);
        entityManager.getPlayer().setY(spawnY*64);
    }

    public void Lvl2()
    {
        loadWorld("res/maps/lvl2.txt");
        currentlvl=2;
        entityManager=new EntityManager(handler, new Player(handler,6*64,2*64));

        //CHESTS
        entityManager.addEntity(new GoldenChest(handler,19*64,3*64));
        entityManager.addEntity(new GoldenChest(handler,6*64,17*64));
        entityManager.addEntity(new GoldenChest(handler,21*64,15*64));
        entityManager.addEntity(new GoldenChest(handler,45*64,34*64));
        entityManager.addEntity(new GoldenChest(handler,15*64,31*64));
        entityManager.addEntity(new SilverChest(handler,3*64,2*64));
        entityManager.addEntity(new SilverChest(handler,1*64,24*64));
        entityManager.addEntity(new SilverChest(handler,16*64,31*64));
        entityManager.addEntity(new SilverChest(handler,22*64,28*64));
        entityManager.addEntity(new SilverChest(handler,38*64,8*64));
        entityManager.addEntity(new SilverChest(handler,31*64,5*64));
        //POTIONS
        entityManager.addEntity(new Potion(handler,11*64,12*64));
        entityManager.addEntity(new Potion(handler,20*64,6*64));
        entityManager.addEntity(new Potion(handler,24*64,16*64));
        entityManager.addEntity(new Potion(handler,11*64,23*64));
        entityManager.addEntity(new Potion(handler,11*64,25*64));
        entityManager.addEntity(new Potion(handler,5*64,27*64));
        entityManager.addEntity(new Potion(handler,12*64,36*64));
        entityManager.addEntity(new Potion(handler,25*64,23*64));
        entityManager.addEntity(new Potion(handler,29*64,27*64));
        entityManager.addEntity(new Potion(handler,30*64,32*64));
        entityManager.addEntity(new Potion(handler,39*64,31*64));
        entityManager.addEntity(new Potion(handler,44*64,16*64));
        entityManager.addEntity(new Potion(handler,41*64,10*64));
        entityManager.addEntity(new Potion(handler,41*64,13*64));
        //COINS
        entityManager.addEntity(new Coin(handler,7*64,7*64));
        entityManager.addEntity(new Coin(handler,6*64,7*64));
        entityManager.addEntity(new Coin(handler,5*64,7*64));
        entityManager.addEntity(new Coin(handler,5*64,13*64));
        entityManager.addEntity(new Coin(handler,6*64,13*64));
        entityManager.addEntity(new Coin(handler,12*64,9*64));
        entityManager.addEntity(new Coin(handler,16*64,9*64));
        entityManager.addEntity(new Coin(handler,17*64,12*64));
        entityManager.addEntity(new Coin(handler,18*64,12*64));
        entityManager.addEntity(new Coin(handler,19*64,12*64));
        entityManager.addEntity(new Coin(handler,23*64,1*64));
        entityManager.addEntity(new Coin(handler,24*64,1*64));
        entityManager.addEntity(new Coin(handler,37*64,4*64));
        entityManager.addEntity(new Coin(handler,38*64,4*64));
        entityManager.addEntity(new Coin(handler,39*64,4*64));
        entityManager.addEntity(new Coin(handler,40*64,4*64));
        entityManager.addEntity(new Coin(handler,44*64,8*64));
        entityManager.addEntity(new Coin(handler,44*64,9*64));
        entityManager.addEntity(new Coin(handler,44*64,10*64));
        entityManager.addEntity(new Coin(handler,44*64,11*64));
        entityManager.addEntity(new Coin(handler,37*64,18*64));
        entityManager.addEntity(new Coin(handler,38*64,18*64));
        entityManager.addEntity(new Coin(handler,24*64,10*64));
        entityManager.addEntity(new Coin(handler,25*64,10*64));
        entityManager.addEntity(new Coin(handler,26*64,10*64));
        entityManager.addEntity(new Coin(handler,27*64,10*64));
        entityManager.addEntity(new Coin(handler,28*64,10*64));
        entityManager.addEntity(new Coin(handler,39*64,10*64));
        entityManager.addEntity(new Coin(handler,40*64,10*64));
        entityManager.addEntity(new Coin(handler,41*64,11*64));
        entityManager.addEntity(new Coin(handler,41*64,12*64));
        entityManager.addEntity(new Coin(handler,40*64,13*64));
        entityManager.addEntity(new Coin(handler,39*64,13*64));
        entityManager.addEntity(new Coin(handler,39*64,25*64));
        entityManager.addEntity(new Coin(handler,45*64,30*64));
        entityManager.addEntity(new Coin(handler,45*64,31*64));
        entityManager.addEntity(new Coin(handler,45*64,32*64));
        entityManager.addEntity(new Coin(handler,45*64,33*64));
        entityManager.addEntity(new Coin(handler,35*64,36*64));
        entityManager.addEntity(new Coin(handler,35*64,36*64));
        entityManager.addEntity(new Coin(handler,36*64,36*64));
        entityManager.addEntity(new Coin(handler,37*64,36*64));
        entityManager.addEntity(new Coin(handler,38*64,36*64));
        entityManager.addEntity(new Coin(handler,39*64,36*64));
        entityManager.addEntity(new Coin(handler,40*64,36*64));
        entityManager.addEntity(new Coin(handler,41*64,36*64));
        entityManager.addEntity(new Coin(handler,42*64,36*64));
        entityManager.addEntity(new Coin(handler,21*64,32*64));
        entityManager.addEntity(new Coin(handler,21*64,34*64));
        entityManager.addEntity(new Coin(handler,21*64,36*64));
        entityManager.addEntity(new Coin(handler,22*64,38*64));
        entityManager.addEntity(new Coin(handler,24*64,38*64));
        entityManager.addEntity(new Coin(handler,26*64,38*64));
        entityManager.addEntity(new Coin(handler,28*64,38*64));
        entityManager.addEntity(new Coin(handler,30*64,38*64));
        entityManager.addEntity(new Coin(handler,35*64,25*64));
        entityManager.addEntity(new Coin(handler,35*64,26*64));
        entityManager.addEntity(new Coin(handler,35*64,27*64));
        entityManager.addEntity(new Coin(handler,35*64,28*64));
        entityManager.addEntity(new Coin(handler,22*64,20*64));
        entityManager.addEntity(new Coin(handler,24*64,20*64));
        entityManager.addEntity(new Coin(handler,26*64,20*64));
        entityManager.addEntity(new Coin(handler,28*64,20*64));
        entityManager.addEntity(new Coin(handler,14*64,16*64));
        entityManager.addEntity(new Coin(handler,16*64,16*64));
        entityManager.addEntity(new Coin(handler,18*64,16*64));
        entityManager.addEntity(new Coin(handler,1*64,28*64));
        entityManager.addEntity(new Coin(handler,3*64,28*64));
        entityManager.addEntity(new Coin(handler,6*64,28*64));
        entityManager.addEntity(new Coin(handler,8*64,28*64));
        entityManager.addEntity(new Coin(handler,4*64,36*64));
        entityManager.addEntity(new Coin(handler,5*64,36*64));
        entityManager.addEntity(new Coin(handler,14*64,31*64));
        entityManager.addEntity(new Coin(handler,17*64,31*64));
        entityManager.addEntity(new Coin(handler,14*64,33*64));
        entityManager.addEntity(new Coin(handler,17*64,33*64));
        entityManager.addEntity(new Coin(handler,14*64,35*64));
        entityManager.addEntity(new Coin(handler,17*64,35*64));
        //ZOMBIES
        entityManager.addEntity(new Zombie(handler,9*64,11*64));
        entityManager.addEntity(new Zombie(handler,14*64,4*64));
        /*
        entityManager.addEntity(new Zombie(handler,14*64,7*64));
        entityManager.addEntity(new Zombie(handler,27*64,5*64));
        entityManager.addEntity(new Zombie(handler,26*64,12*64));
        entityManager.addEntity(new Zombie(handler,31*64,2*64));
        entityManager.addEntity(new Zombie(handler,33*64,10*64));
        entityManager.addEntity(new Zombie(handler,35*64,10*64));
        entityManager.addEntity(new Zombie(handler,37*64,10*64));
        entityManager.addEntity(new Zombie(handler,33*64,18*64));
        entityManager.addEntity(new Zombie(handler,41*64,19*64));
        entityManager.addEntity(new Zombie(handler,41*64,21*64));
        entityManager.addEntity(new Zombie(handler,41*64,28*64));
        entityManager.addEntity(new Zombie(handler,40*64,34*64));
        entityManager.addEntity(new Zombie(handler,37*64,34*64));
        entityManager.addEntity(new Zombie(handler,24*64,35*64));
        entityManager.addEntity(new Zombie(handler,26*64,35*64));
        entityManager.addEntity(new Zombie(handler,28*64,35*64));
        entityManager.addEntity(new Zombie(handler,33*64,27*64));
        entityManager.addEntity(new Zombie(handler,25*64,21*64));
        entityManager.addEntity(new Zombie(handler,25*64,25*64));
        entityManager.addEntity(new Zombie(handler,16*64,17*64));
        entityManager.addEntity(new Zombie(handler,3*64,18*64));
        entityManager.addEntity(new Zombie(handler,6*64,18*64));
        entityManager.addEntity(new Zombie(handler,9*64,18*64));
        entityManager.addEntity(new Zombie(handler,5*64,32*64));
        entityManager.addEntity(new Zombie(handler,8*64,32*64));
        entityManager.addEntity(new Zombie(handler,2*64,32*64));
        entityManager.addEntity(new Zombie(handler,15*64,33*64));
        entityManager.addEntity(new Zombie(handler,16*64,33*64));
        */

        entityManager.getPlayer().score=tempscore;
        entityManager.getPlayer().setX(spawnX*64);
        entityManager.getPlayer().setY(spawnY*64);
    }

    public boolean checkMonsters(){
        for(Entity e: handler.getWorld().getEntityManager().getEntities()){
            if(e.isMonster())
                return true;
        }
        return false;
    }

    public boolean isPlayerdead() {
        return playerdead;
    }
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
