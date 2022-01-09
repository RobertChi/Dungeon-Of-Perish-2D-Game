package Joc.Entity.Statics;

import Joc.Graphics.Animation;
import Joc.Graphics.Assets;
import Joc.Handler;
import Joc.Tiles.Tile;

import java.awt.*;

public class Coin extends StaticEntity{

    private Animation coin;

    public Coin(Handler handler, float x, float y)
    {
        super(handler, x,y, 64, 64);
        coin=new Animation(130,Assets.coin);
        this.health=100;
    }
    public void die(){this.active=false;};

    @Override
    public void tick() {

        coin.tick();
        collected();
    }

    public void collected() {
        Rectangle ar = new Rectangle();
        ar.width = this.width;
        ar.height = this.height;
        ar.x = (int) this.getX();
        ar.y = (int) this.getY();

        if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(ar)) {
                this.die();
                handler.getWorld().getEntityManager().getPlayer().score+=1;
        }
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(coin.getCurrentFrame(),(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),64,64,null);
    }

    @Override
    public boolean isSolid(){return false;}

    @Override
    public boolean isMonster() {
        return false;
    }
}
