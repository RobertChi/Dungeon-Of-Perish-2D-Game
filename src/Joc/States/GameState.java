package Joc.States;

import Joc.Graphics.Assets;
import Joc.Handler;
import Joc.Maps.World;
import java.awt.*;

public class GameState extends State {
    private World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler);
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();
        if(handler.getKeyManager().esc)
            State.setState(handler.getGame().menuState);
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        if(world.isPlayerdead())
        {
            g.setFont(Assets.font84);
            g.setColor(Color.white);
            g.drawString("You Lost!",350,250);
            g.setFont(Assets.font64);
            Color c=new Color(.8f,.4f,0f,.70f);
            g.setColor(c);
            g.drawImage(Assets.button,261,670,437,104,null);
            g.drawString("Restart",390,740);
            g.drawImage(Assets.button,261,800,437,104,null);
            g.drawString("Quit",420,870);
            }
        }
}

