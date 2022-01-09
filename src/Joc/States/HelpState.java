package Joc.States;

import Joc.Graphics.Assets;
import Joc.Handler;

import java.awt.*;

public class HelpState extends State{

    public HelpState(Handler handler)
    {
        super(handler);
    }

    @Override
    public void tick()
    {
        if((handler.getMouseManager().isleftPressed() && handler.getMouseManager().getMouseX()>=350 && handler.getMouseManager().getMouseX()<=600
                && handler.getMouseManager().getMouseY()>=670 && handler.getMouseManager().getMouseY()<=740)||handler.getKeyManager().esc) {
            State.setState(handler.getGame().menuState);
        }

        handler.getMouseManager().reset();
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(Assets.background,0,0,960,960,null);
        g.drawImage(Assets.scroll,168,100,624,800,null);
        g.drawImage(Assets.button,261,650,437,104,null);


        g.setFont(Assets.font64);
        g.setColor(Color.black);
        g.drawString("Controls",380,340);
        g.drawString("Movement      wasd",250,420);
        g.drawString("Attack            q",250,470);
        g.drawString("Interact          e",250,520);
        Color c=new Color(.8f,.4f,0f,.70f);
        g.setColor(c);
        g.drawString("Back",420,720);

    }


}
