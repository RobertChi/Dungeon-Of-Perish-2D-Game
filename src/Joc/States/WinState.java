package Joc.States;

import Joc.Graphics.Assets;
import Joc.Handler;

import java.awt.*;

public class WinState extends State{
    public WinState(Handler handler){
        super(handler);
    }

    @Override
    public void tick() {
        if((handler.getMouseManager().isleftPressed() && handler.getMouseManager().getMouseX()>=350 && handler.getMouseManager().getMouseX()<=600
                && handler.getMouseManager().getMouseY()>=670 && handler.getMouseManager().getMouseY()<=740)) {
            handler.getGame().stop();
        }
        handler.getMouseManager().reset();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.background,0,0,960,960,null);
        g.drawImage(Assets.scroll,168,100,624,800,null);

        g.drawImage(Assets.button,261,650,437,104,null);
        g.setFont(Assets.font84);
        g.setColor(Color.black);
        g.drawString("Congratulation", 260,300);

        g.setFont(Assets.font64);
        Color c=new Color(.8f,.4f,0f,.70f);
        g.setColor(c);
        g.drawString("Quit",420,720);
    }

}
