package Joc.Graphics;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.BufferOverflowException;

public class Assets
{
    private static final int w = 16, h = 16;
    public static Font font84, font64, fontscore40, fonstscore15;

    public static BufferedImage f1,w1,wleft,wright,blank,wback,wcleft,wcright,wctright,wctleft,wdouble,wfull,wdtleft,wdtright;
    public static BufferedImage hatch;
    public static BufferedImage background, button,scroll;
    public static BufferedImage heartfull,heartempty;
    public static BufferedImage[] heartplus,heartminus;
    public static BufferedImage[] pdownw,pdowni,pdowna,pdownd,pdownh;
    public static BufferedImage[] pupw,pupa;
    public static BufferedImage[] pleftw,plefta;
    public static BufferedImage[] prightw,prighta;
    public static BufferedImage[] silverchest, goldenchest;
    public static BufferedImage[] potion;
    public static BufferedImage[] skeletondw, skeletonuw,skeletonlw,skeletonrw,skeletondie,skeletonidle;
    public static BufferedImage[] zombielw,zombierw,zombiedw,zombieuw,zombieidle;
    public static BufferedImage[] coin;


    public static void init() {


        fontscore40=FontLoader.loadFont("res/font/ARCADECLASSIC.TTF",40);
        fonstscore15 =FontLoader.loadFont("res/font/ARCADECLASSIC.TTF",15);
        font84=FontLoader.loadFont("res/font/robus.otf",84);
        font64 =FontLoader.loadFont("res/font/robus.otf",64);
        background=ImageLoader.LoadImage("/textures/menu.jpg");
        button=ImageLoader.LoadImage("/textures/buton.png");
        scroll=ImageLoader.LoadImage("/textures/paper.png");

        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Tiles.png"));
        blank=sheet.crop(0,0,w,h);
        f1=sheet.crop(1,0,w,h);
        w1=sheet.crop(0,1,w,h);
        wback=sheet.crop(1,1,w,h);
        wleft=sheet.crop(0,2,w,h);
        wright=sheet.crop(1,2,w,h);
        wdtleft=sheet.crop(0,3,w,h);
        wdtright=sheet.crop(1,3,w,h);
        wdouble=sheet.crop(0,4,w,h);
        wfull=sheet.crop(1,4,w,h);
        wctleft=sheet.crop(0,5,w,h);
        wctright=sheet.crop(1,5,w,h);
        wcleft=sheet.crop(0,6,w,h);
        wcright=sheet.crop(1,6,w,h);

        sheet=new SpriteSheet(ImageLoader.LoadImage("/textures/chestandhatch.png"));
        silverchest=new BufferedImage[3];
        silverchest[0]=sheet.crop(0,0,16,16);
        silverchest[1]=sheet.crop(1,0,16,16);
        silverchest[2]=sheet.crop(2,0,16,16);
        goldenchest=new BufferedImage[3];
        goldenchest[0]=sheet.crop(0,1,16,16);
        goldenchest[1]=sheet.crop(1,1,16,16);
        goldenchest[2]=sheet.crop(2,1,16,16);
        hatch=sheet.crop(4,1,16,16);

        sheet=new SpriteSheet(ImageLoader.LoadImage("/textures/potion.png"));
        potion=new BufferedImage[8];
        potion[0]=sheet.crop(0,0,16,16);
        potion[1]=sheet.crop(1,0,16,16);
        potion[2]=sheet.crop(2,0,16,16);
        potion[3]=sheet.crop(0,1,16,16);
        potion[4]=sheet.crop(1,1,16,16);
        potion[5]=sheet.crop(2,1,16,16);
        potion[6]=sheet.crop(0,2,16,16);
        potion[7]=sheet.crop(1,2,16,16);

        sheet=new SpriteSheet(ImageLoader.LoadImage("/textures/viata.png"));
        heartempty=sheet.crop(3,1,16,16);
        heartfull=sheet.crop(0,1,16,16);
        heartplus=new BufferedImage[6];
        heartplus[0]=sheet.crop(0,0,16,16);
        heartplus[1]=sheet.crop(1,0,16,16);
        heartplus[2]=sheet.crop(2,0,16,16);
        heartplus[3]=sheet.crop(3,0,16,16);
        heartplus[4]=sheet.crop(4,0,16,16);
        heartplus[5]=sheet.crop(5,0,16,16);
        heartminus=new BufferedImage[4];
        heartminus[0]=sheet.crop(0,1,16,16);
        heartminus[1]=sheet.crop(1,1,16,16);
        heartminus[2]=sheet.crop(2,1,16,16);
        heartminus[3]=sheet.crop(3,1,16,16);



        sheet=new SpriteSheet(ImageLoader.LoadImage("/textures/coin.png"));
        coin=new BufferedImage[8];
        coin[0]=sheet.crop(0,0,32,32);
        coin[1]=sheet.crop(1,0,32,32);
        coin[2]=sheet.crop(2,0,32,32);
        coin[3]=sheet.crop(3,0,32,32);
        coin[4]=sheet.crop(4,0,32,32);
        coin[5]=sheet.crop(5,0,32,32);
        coin[6]=sheet.crop(6,0,32,32);
        coin[7]=sheet.crop(7,0,32,32);

        //Anim player
        SpriteSheet hero=new SpriteSheet(ImageLoader.LoadImage("/Character/Down/WarriorDownWalk.png"));
        pdownw=new BufferedImage[8];
        pdownw[0]=hero.crop(0,0,48,48);
        pdownw[1]=hero.crop(1,0,48,48);
        pdownw[2]=hero.crop(2,0,48,48);
        pdownw[3]=hero.crop(3,0,48,48);
        pdownw[4]=hero.crop(4,0,48,48);
        pdownw[5]=hero.crop(5,0,48,48);
        pdownw[6]=hero.crop(6,0,48,48);
        pdownw[7]=hero.crop(7,0,48,48);
        hero=new SpriteSheet(ImageLoader.LoadImage("/Character/Down/WarriorDownIdle.png"));
        pdowni=new BufferedImage[5];
        pdowni[0]=hero.crop(0,0,48,48);
        pdowni[1]=hero.crop(1,0,48,48);
        pdowni[2]=hero.crop(2,0,48,48);
        pdowni[3]=hero.crop(3,0,48,48);
        pdowni[4]=hero.crop(4,0,48,48);
        hero=new SpriteSheet(ImageLoader.LoadImage("/Character/Down/WarriorDownAttack01.png"));
        pdowna=new BufferedImage[6];
        pdowna[0]=hero.crop(0,0,48,48);
        pdowna[1]=hero.crop(1,0,48,48);
        pdowna[2]=hero.crop(2,0,48,48);
        pdowna[3]=hero.crop(3,0,48,48);
        pdowna[4]=hero.crop(4,0,48,48);
        pdowna[5]=hero.crop(5,0,48,48);
        hero=new SpriteSheet(ImageLoader.LoadImage("/Character/Down/WarriorDownDeath.png"));
        pdownd=new BufferedImage[5];
        pdownd[0]=hero.crop(0,0,48,48);
        pdownd[1]=hero.crop(1,0,48,48);
        pdownd[2]=hero.crop(2,0,48,48);
        pdownd[3]=hero.crop(3,0,48,48);
        pdownd[4]=hero.crop(4,0,48,48);
        hero=new SpriteSheet(ImageLoader.LoadImage("/Character/Down/WarriorDownHurt.png"));
        pdownh=new BufferedImage[4];
        pdownh[0]=hero.crop(0,0,48,48);
        pdownh[1]=hero.crop(0,0,48,48);
        pdownh[2]=hero.crop(0,0,48,48);
        pdownh[3]=hero.crop(0,0,48,48);


        hero=new SpriteSheet(ImageLoader.LoadImage("/Character/Up/WarriorUpWalk.png"));
        pupw=new BufferedImage[8];
        pupw[0]=hero.crop(0,0,48,48);
        pupw[1]=hero.crop(1,0,48,48);
        pupw[2]=hero.crop(2,0,48,48);
        pupw[3]=hero.crop(3,0,48,48);
        pupw[4]=hero.crop(4,0,48,48);
        pupw[5]=hero.crop(5,0,48,48);
        pupw[6]=hero.crop(6,0,48,48);
        pupw[7]=hero.crop(7,0,48,48);
        hero=new SpriteSheet(ImageLoader.LoadImage("/Character/Up/WarriorUpAttack01.png"));
        pupa=new BufferedImage[6];
        pupa[0]=hero.crop(0,0,48,48);
        pupa[1]=hero.crop(1,0,48,48);
        pupa[2]=hero.crop(2,0,48,48);
        pupa[3]=hero.crop(3,0,48,48);
        pupa[4]=hero.crop(4,0,48,48);
        pupa[5]=hero.crop(5,0,48,48);


        hero=new SpriteSheet(ImageLoader.LoadImage("/Character/Left/WarriorLeftWalk.png"));
        pleftw=new BufferedImage[8];
        pleftw[0]=hero.crop(0,0,48,48);
        pleftw[1]=hero.crop(1,0,48,48);
        pleftw[2]=hero.crop(2,0,48,48);
        pleftw[3]=hero.crop(3,0,48,48);
        pleftw[4]=hero.crop(4,0,48,48);
        pleftw[5]=hero.crop(5,0,48,48);
        pleftw[6]=hero.crop(6,0,48,48);
        pleftw[7]=hero.crop(7,0,48,48);
        hero=new SpriteSheet(ImageLoader.LoadImage("/Character/Left/WarriorLeftAttack01.png"));
        plefta=new BufferedImage[6];
        plefta[0]=hero.crop(0,0,48,48);
        plefta[1]=hero.crop(1,0,48,48);
        plefta[2]=hero.crop(2,0,48,48);
        plefta[3]=hero.crop(3,0,48,48);
        plefta[4]=hero.crop(4,0,48,48);
        plefta[5]=hero.crop(5,0,48,48);


        hero=new SpriteSheet(ImageLoader.LoadImage("/Character/Right/WarriorRightWalk.png"));
        prightw=new BufferedImage[8];
        prightw[0]=hero.crop(0,0,48,48);
        prightw[1]=hero.crop(1,0,48,48);
        prightw[2]=hero.crop(2,0,48,48);
        prightw[3]=hero.crop(3,0,48,48);
        prightw[4]=hero.crop(4,0,48,48);
        prightw[5]=hero.crop(5,0,48,48);
        prightw[6]=hero.crop(6,0,48,48);
        prightw[7]=hero.crop(7,0,48,48);
        hero=new SpriteSheet(ImageLoader.LoadImage("/Character/Right/WarriorRightAttack01.png"));
        prighta=new BufferedImage[6];
        prighta[0]=hero.crop(0,0,48,48);
        prighta[1]=hero.crop(1,0,48,48);
        prighta[2]=hero.crop(2,0,48,48);
        prighta[3]=hero.crop(3,0,48,48);
        prighta[4]=hero.crop(4,0,48,48);
        prighta[5]=hero.crop(5,0,48,48);


        SpriteSheet skelet=new SpriteSheet(ImageLoader.LoadImage("/Mobs/Skeleton/Down/SkeletonDownWalk.png"));
        skeletondw=new BufferedImage[6];
        skeletondw[0]=skelet.crop(0,0,48,48);
        skeletondw[1]=skelet.crop(1,0,48,48);
        skeletondw[2]=skelet.crop(2,0,48,48);
        skeletondw[3]=skelet.crop(3,0,48,48);
        skeletondw[4]=skelet.crop(4,0,48,48);
        skeletondw[5]=skelet.crop(5,0,48,48);

        skelet=new SpriteSheet(ImageLoader.LoadImage(("/Mobs/Skeleton/Up/SkeletonUpWalk.png")));
        skeletonuw=new BufferedImage[6];
        skeletonuw[0]=skelet.crop(0,0,48,48);
        skeletonuw[1]=skelet.crop(1,0,48,48);
        skeletonuw[2]=skelet.crop(2,0,48,48);
        skeletonuw[3]=skelet.crop(3,0,48,48);
        skeletonuw[4]=skelet.crop(4,0,48,48);
        skeletonuw[5]=skelet.crop(5,0,48,48);

        skelet=new SpriteSheet(ImageLoader.LoadImage("/Mobs/Skeleton/Left/SkeletonLeftWalk.png"));
        skeletonlw=new BufferedImage[6];
        skeletonlw[0]=skelet.crop(0,0,48,48);
        skeletonlw[1]=skelet.crop(1,0,48,48);
        skeletonlw[2]=skelet.crop(2,0,48,48);
        skeletonlw[3]=skelet.crop(3,0,48,48);
        skeletonlw[4]=skelet.crop(4,0,48,48);
        skeletonlw[5]=skelet.crop(5,0,48,48);

        skelet=new SpriteSheet(ImageLoader.LoadImage("/Mobs/Skeleton/Right/SkeletonRightWalk.png"));
        skeletonrw=new BufferedImage[6];
        skeletonrw[0]=skelet.crop(0,0,48,48);
        skeletonrw[1]=skelet.crop(1,0,48,48);
        skeletonrw[2]=skelet.crop(2,0,48,48);
        skeletonrw[3]=skelet.crop(3,0,48,48);
        skeletonrw[4]=skelet.crop(4,0,48,48);
        skeletonrw[5]=skelet.crop(5,0,48,48);

        skelet=new SpriteSheet(ImageLoader.LoadImage("/Mobs/Skeleton/Down/SkeletonDownDeath.png"));
        skeletondie=new BufferedImage[8];
        skeletondie[0]=skelet.crop(0,0,48,48);
        skeletondie[1]=skelet.crop(1,0,48,48);
        skeletondie[2]=skelet.crop(2,0,48,48);
        skeletondie[3]=skelet.crop(3,0,48,48);
        skeletondie[4]=skelet.crop(4,0,48,48);
        skeletondie[5]=skelet.crop(5,0,48,48);
        skeletondie[6]=skelet.crop(6,0,48,48);
        skeletondie[7]=skelet.crop(7,0,48,48);

        skelet=new SpriteSheet(ImageLoader.LoadImage("/Mobs/Skeleton/Down/SkeletonDownIdle.png"));
        skeletonidle=new BufferedImage[6];
        skeletonidle[0]=skelet.crop(0,0,48,48);
        skeletonidle[1]=skelet.crop(1,0,48,48);
        skeletonidle[2]=skelet.crop(2,0,48,48);
        skeletonidle[3]=skelet.crop(3,0,48,48);
        skeletonidle[4]=skelet.crop(4,0,48,48);
        skeletonidle[5]=skelet.crop(5,0,48,48);


        SpriteSheet zombie=new SpriteSheet(ImageLoader.LoadImage("/Mobs/Zombie/Down/ZombieIdle.png"));
        zombieidle=new BufferedImage[5];
        zombieidle[0]=zombie.crop(0,0,48,48);
        zombieidle[1]=zombie.crop(1,0,48,48);
        zombieidle[2]=zombie.crop(2,0,48,48);
        zombieidle[3]=zombie.crop(3,0,48,48);
        zombieidle[4]=zombie.crop(4,0,48,48);

        zombie=new SpriteSheet(ImageLoader.LoadImage("/Mobs/Zombie/Down/ZombieWalkDown.png"));
        zombiedw=new BufferedImage[6];
        zombiedw[0]=zombie.crop(0,0,48,48);
        zombiedw[1]=zombie.crop(1,0,48,48);
        zombiedw[2]=zombie.crop(2,0,48,48);
        zombiedw[3]=zombie.crop(3,0,48,48);
        zombiedw[4]=zombie.crop(4,0,48,48);
        zombiedw[5]=zombie.crop(5,0,48,48);

        zombie=new SpriteSheet(ImageLoader.LoadImage("/Mobs/Zombie/Left/ZombieWalkLeft.png"));
        zombielw=new BufferedImage[6];
        zombielw[0]=zombie.crop(0,0,48,48);
        zombielw[1]=zombie.crop(1,0,48,48);
        zombielw[2]=zombie.crop(2,0,48,48);
        zombielw[3]=zombie.crop(3,0,48,48);
        zombielw[4]=zombie.crop(4,0,48,48);
        zombielw[5]=zombie.crop(5,0,48,48);

        zombie=new SpriteSheet(ImageLoader.LoadImage("/Mobs/Zombie/Right/ZombieWalkRight.png"));
        zombierw=new BufferedImage[6];
        zombierw[0]=zombie.crop(0,0,48,48);
        zombierw[1]=zombie.crop(1,0,48,48);
        zombierw[2]=zombie.crop(2,0,48,48);
        zombierw[3]=zombie.crop(3,0,48,48);
        zombierw[4]=zombie.crop(4,0,48,48);
        zombierw[5]=zombie.crop(5,0,48,48);

        zombie=new SpriteSheet(ImageLoader.LoadImage("/Mobs/Zombie/Up/ZombieWalkUp.png"));
        zombieuw=new BufferedImage[6];
        zombieuw[0]=zombie.crop(0,0,48,48);
        zombieuw[1]=zombie.crop(1,0,48,48);
        zombieuw[2]=zombie.crop(2,0,48,48);
        zombieuw[3]=zombie.crop(3,0,48,48);
        zombieuw[4]=zombie.crop(4,0,48,48);
        zombieuw[5]=zombie.crop(5,0,48,48);
    }
}
