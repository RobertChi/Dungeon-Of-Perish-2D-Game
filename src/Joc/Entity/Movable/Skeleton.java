package Joc.Entity.Movable;

import Joc.Entity.Statics.Coin;
import Joc.Entity.Statics.Potion;
import Joc.Graphics.Animation;
import Joc.Graphics.Assets;
import Joc.Handler;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Skeleton extends Creature{

    private Animation skdeath, skidle,skdw,skuw,sklw,skrw;

    private long lastAttackTimer, attackCooldown=2000, attackTimer=attackCooldown;


    public Skeleton(Handler handler, float x, float y)
    {
        super(handler,x,y,DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
        bounds.x=15;
        bounds.y=25;
        bounds.width=38;
        bounds.height=40;
        speed=2.0f;

        skdeath=new Animation(130,Assets.skeletondie);
        skidle=new Animation(130,Assets.skeletonidle);
        skdw=new Animation(130,Assets.skeletondw);
        skuw=new Animation(130,Assets.skeletonuw);
        sklw=new Animation(130,Assets.skeletonlw);
        skrw=new Animation(130,Assets.skeletonrw);
    }


    @Override
    public void tick() {
        skdeath.tick(); skidle.tick();  skrw.tick();    sklw.tick();    skdw.tick();    skuw.tick();

        direction();
        move();

        checkAttacks();
    }

    private void direction()
    {
        xMove=0;
        yMove=0;

        if(handler.getWorld().getEntityManager().getPlayer().isActive()==false)
            return;

        int xP=(int)handler.getWorld().getEntityManager().getPlayer().getX();
        int yP=(int)handler.getWorld().getEntityManager().getPlayer().getY();


        if(xP>=this.getX()) {
            if(yP>=this.getY())
                if((int)(xP-this.getX())/64>3 || (int)(yP-this.getY())/64>3) {
                    return;
                }
            if(yP<=this.getY())
                if((int)(xP-this.getX())/64>3 || (int)(this.getY()-yP)/64>3){
                    return;
                }
        }
        if(this.getX()>=xP){
            if(yP>=this.getY())
                if((int)(this.getX()-xP)/64>3 || (int)(yP-this.getY())/64>3) {
                    return;
                }
            if(yP<=this.getY())
                if((int)(this.getX()-xP)/64>3 || (int)(this.getY()-yP)/64>3){
                    return;
                }
        }


        if(xP<this.getX())
            xMove= -speed;
        if(xP>this.getX())
            xMove= speed;
        if(yP<this.getY())
            yMove= -speed;
        if(yP>this.getY())
            yMove=speed;
    }

    private void checkAttacks()
    {
        attackTimer+= System.currentTimeMillis()-lastAttackTimer;
        lastAttackTimer=System.currentTimeMillis();
        if(attackTimer<attackCooldown)
            return;

        Rectangle ar=new Rectangle();
        ar.width=120;
        ar.height=120;
        ar.x=(int)this.getX()+this.width/2-ar.width/2;
        ar.y=(int)this.getY()+this.height/2-ar.height/2;




        attackTimer=0;
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0,0).intersects(ar) && handler.getWorld().getEntityManager().getPlayer().isActive()){
            handler.getWorld().getEntityManager().getPlayer().hurt(1);
            return;
            }
    }


    @Override
    public void die()
    {
        x=this.getX();
        y=this.getY();
        handler.getWorld().getEntityManager().getPlayer().score+=2;
        double chance=Math.random();
        if( chance <= .35)
            handler.getWorld().getEntityManager().addEntity(new Coin(handler,x,y));
        if( chance >.35 && chance<=50 )
            handler.getWorld().getEntityManager().addEntity(new Potion(handler,x,y));
    }

    @Override
    public boolean isMonster(){return true;}

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int)(x-handler.getGameCamera().getxOffset()-15), (int)(y-handler.getGameCamera().getyOffset()-20), width, height, null);
        //Color c=new Color(0,0,1,.5f);
        //g.setColor(c);
       //g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
         //       (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
         //      bounds.width, bounds.height);
    }

    public BufferedImage getCurrentAnimationFrame()
    {
        if (xMove < 0) {
            return sklw.getCurrentFrame();
        } else if (xMove > 0) {
            return skrw.getCurrentFrame();
        } else if (yMove < 0) {
            return skuw.getCurrentFrame();
        } else if (yMove > 0) {
            return skdw.getCurrentFrame();
        } else
            return skidle.getCurrentFrame();
    }
}

