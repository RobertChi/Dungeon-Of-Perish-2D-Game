package Joc.Entity.Movable;

import java.awt.*;
import java.awt.image.BufferedImage;

import Joc.Entity.Entity;
import Joc.Graphics.Animation;
import Joc.Handler;
import Joc.Graphics.Assets;


public class Player extends Creature
{
    private Animation walkd,walku,walkl,walkr;
    private Animation idle, attacku, attackd, attackl, attackr, hurt, die;
    private long lastAttackTimer, attackCooldown=800, attackTimer=attackCooldown;

    public Animation heartplus, heartminus;
    public int score=0;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y,DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
        bounds.x = 29;
        bounds.y = 38;
        bounds.width = 38;
        bounds.height = 40;
        this.setHealth(3);
        this.speed=3f;


        walkd=new Animation(130,Assets.pdownw);
        walku=new Animation(130,Assets.pupw);
        walkl=new Animation(130,Assets.pleftw);
        walkr=new Animation(130,Assets.prightw);
        idle =new Animation(130,Assets.pdowni);
        attackd=new Animation(133,Assets.pdowna);
        attacku=new Animation(133,Assets.pupa);
        attackl=new Animation(133,Assets.plefta);
        attackr=new Animation(133,Assets.prighta);
        hurt=new Animation(130,Assets.pdownh);
        die=new Animation(130,Assets.pdownd);


        heartplus=new Animation(150, Assets.heartplus);
        heartminus=new Animation(200,Assets.heartminus);

    }

    @Override
    public void tick() {
        //Animations
        walkd.tick();   walku.tick();   walkl.tick();   walkr.tick();   idle.tick();    heartplus.tick();   heartminus.tick();
        attackd.tick(); attacku.tick(); attackl.tick(); attackr.tick(); die.tick();     hurt.tick();


        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);


        //Attack
        checkAttacks();
    }



    private void getInput(){
        xMove = 0;
        yMove = 0;


        if(handler.getKeyManager().up==true)
            yMove = -speed;
        if(handler.getKeyManager().down==true)
            yMove = speed;
        if(handler.getKeyManager().left==true)
            xMove = -speed;
        if(handler.getKeyManager().right==true)
            xMove = speed;
    }

    @Override
    public void die()
    {
        if(score>=200){
            this.health=3;
            active=true;
            score-=200;
        }
    }

    @Override
    public void hurt(int amt) {
        health -= amt;
        if (health <= 0) {
            active = false;
            die();
        }
    }

    public int getCurrentHealth(){return health;}
    public void plusHealth(int x){health+=x;}

    private void checkAttacks()
    {
        attackTimer+= System.currentTimeMillis()-lastAttackTimer;
        lastAttackTimer=System.currentTimeMillis();
        if(attackTimer<attackCooldown)
            return;

        Rectangle cb=getCollisionBounds(0f,0f);
        Rectangle ar=new Rectangle();
        int arSize=20;
        ar.width=arSize;
        ar.height=arSize;

        if(handler.getKeyManager().attack && handler.getKeyManager().up){
            ar.x=cb.x+cb.width/2-arSize/2;
            ar.y=cb.y-arSize;
        } else if(handler.getKeyManager().attack && handler.getKeyManager().down) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        }else if(handler.getKeyManager().attack && handler.getKeyManager().left) {
            ar.x = cb.x -arSize;
            ar.y = cb.y +cb.height/2-arSize/2;
        }else if(handler.getKeyManager().attack && handler.getKeyManager().right) {
            ar.x = cb.x +cb.width;
            ar.y = cb.y +cb.height/2-arSize/2;
        }else{
            return;
        }

        attackTimer=0;

        for(Entity e: handler.getWorld().getEntityManager().getEntities())
        {
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0,0).intersects(ar)){
                e.hurt(1);
                return;
            }
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int)(x-handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
        //Color c=new Color(1,0,0,.5f);
        //g.setColor(c);
		//g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
		//		(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
		//		bounds.width, bounds.height);




        g.setColor(Color.white);
        g.setFont(Assets.fontscore40);
        g.drawString("SCORE    "+score,700,60);
        if (this.health==3){
            g.drawImage(Assets.heartfull, 0, 20, 64, 64, null);
            g.drawImage(Assets.heartfull,64,20,64,64,null);
            g.drawImage(Assets.heartfull,128,20,64,64,null);
        }else if(this.health==2){
            g.drawImage(Assets.heartfull, 0, 20, 64, 64, null);
            g.drawImage(Assets.heartfull,64,20,64,64,null);
            g.drawImage(Assets.heartempty,128,20,64,64,null);
        }else if(this.health==1){
            g.drawImage(Assets.heartfull, 0, 20, 64, 64, null);
            g.drawImage(Assets.heartempty,64,20,64,64,null);
            g.drawImage(Assets.heartempty,128,20,64,64,null);
        }


            if(!handler.getWorld().checkMonsters()){
                g.setFont(Assets.font64);
                Color c=new Color(.8f,.4f,0f,.70f);
                g.setColor(c);
                if(handler.getWorld().getCurrentlvl()==1){
                    g.drawImage(Assets.button,261,800,437,104,null);
                    g.drawString("Next Level",350,870);
                }
                if(handler.getWorld().getCurrentlvl()==2){
                    g.drawImage(Assets.button,261,50,437,104,null);
                    g.drawString("Finish",410,120);
                }
        }
    }

    public BufferedImage getCurrentAnimationFrame()
    {
            if (xMove < 0) {
                if (handler.getKeyManager().attack)
                    return attackl.getCurrentFrame();
                return walkl.getCurrentFrame();
            } else if (xMove > 0) {
                if (handler.getKeyManager().attack)
                    return attackr.getCurrentFrame();
                return walkr.getCurrentFrame();
            } else if (yMove < 0) {
                if (handler.getKeyManager().attack)
                    return attacku.getCurrentFrame();
                return walku.getCurrentFrame();
            } else if (yMove > 0) {
                if (handler.getKeyManager().attack)
                    return attackd.getCurrentFrame();
                return walkd.getCurrentFrame();
            } else
                return idle.getCurrentFrame();
    }

    @Override
    public boolean isMonster() {
        return false;
    }
}

