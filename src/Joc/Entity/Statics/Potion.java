package Joc.Entity.Statics;

import Joc.Graphics.Animation;
import Joc.Graphics.Assets;
import Joc.Handler;

import java.awt.*;

public class Potion extends StaticEntity{
    public Animation potion;
    public boolean intersected =false;

    public Potion(Handler handler, float x, float y)
    {
        super(handler, x,y, 64, 64);
        potion=new Animation(130, Assets.potion);
        this.health=100;
    }

    public void die(){this.active=false;};

    @Override
    public void tick() {
        potion.tick();
        interacted();
    }

    public void interacted() {
        Rectangle ar = new Rectangle();
        ar.width = this.width*3;
        ar.height = this.height*3;
        ar.x = (int) this.getX()+this.getWidth()/2-ar.width/2;
        ar.y = (int) this.getY()+this.getHeight()/2-ar.height/2;

        if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(ar)) {
            intersected =true;
        }else{
            intersected=false;
        }

        if(intersected){
            if(handler.getKeyManager().interact){
                this.die();
                if(handler.getWorld().getEntityManager().getPlayer().getCurrentHealth()<3)
                    handler.getWorld().getEntityManager().getPlayer().plusHealth((1));
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(potion.getCurrentFrame(),(int)(x-handler.getGameCamera().getxOffset())+16,(int)(y-handler.getGameCamera().getyOffset())+16,32,32,null);
    }

    @Override
    public boolean isMonster() {
        return false;
    }
}
