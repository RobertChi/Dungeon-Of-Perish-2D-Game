package Joc.Entity.Statics;

import Joc.Graphics.Assets;
import Joc.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SilverChest extends StaticEntity{
    public BufferedImage[] chest;
    private BufferedImage currentimage;
    private boolean intersected=false;
    private int interacted=0;
    long time=0;

    public SilverChest(Handler handler, float x, float y)
    {
        super(handler, x,y, 64, 64);

        chest=new BufferedImage[3];
        chest[0]=Assets.silverchest[0];
        chest[1]=Assets.silverchest[1];
        chest[2]=Assets.silverchest[2];
        currentimage=chest[0];
        this.health=100;
    }

    @Override
    public boolean isMonster() {
        return false;
    }
    public void die(){};

    @Override
    public void tick() {
        interacted();
    }

    private void interacted() {
        Rectangle ar = new Rectangle();
        ar.width = this.width*3;
        ar.height = this.height*3;
        ar.x = (int) this.getX()+this.getWidth()/2-ar.width/2;
        ar.y = (int) this.getY()+this.getHeight()/2-ar.height/2;

        if(interacted==2){return;}

        if(interacted==1 && System.currentTimeMillis()-time>800){
            if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(ar)) {
                intersected =true;
            }else{
                intersected=false;
            }
            if(intersected){
                if(handler.getKeyManager().interact){
                    interacted=2;
                    currentimage=chest[2];
                    handler.getWorld().getEntityManager().getPlayer().score+=10;
                }
            }
            return;
        }

        if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(ar)) {
            intersected =true;
        }else{
            intersected=false;
        }

        if(intersected){
            if(handler.getKeyManager().interact){
                interacted=1;
                currentimage=chest[1];
                time=System.currentTimeMillis();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(currentimage,(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),64,64,null);

    }

    @Override
    public boolean isSolid(){return true;}
}
