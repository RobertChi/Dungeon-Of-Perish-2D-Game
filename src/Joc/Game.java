package Joc;

import Joc.Display.Display;
import Joc.Graphics.Assets;
import Joc.Graphics.GameCamera;
import Joc.Input.KeyManager;
import Joc.Input.MouseManager;
import Joc.States.*;
import Joc.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
    private Display display;
    private int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //Game.States
    public State gameState;
    public State menuState;
    public State helpState;
    public State winState;

    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Camera
    private GameCamera gameCamera;

    //Game.Handler
    private Handler handler;

    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager=new MouseManager();
    }

    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);

        gameState = new GameState(handler);
        menuState= new MenuState(handler);
        helpState= new HelpState(handler);
        winState=new WinState(handler);
        State.setState(menuState);
    }

    private void tick(){
        keyManager.tick();

        if(State.getState() != null)
            State.getState().tick();
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here!

        if(State.getState() != null)
            State.getState().render(g);

        //End Drawing!
        bs.show();
        g.dispose();
    }

    public void run(){

        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        long now;
        long lastTime = System.nanoTime();

        while(running){
            now = System.nanoTime();
            if((now-lastTime)>timePerTick)
            {
                tick();
                render();
                lastTime=now;
            }
        }
        stop();

    }

    public KeyManager getKeyManager(){
        return keyManager;
    }
    public MouseManager getMouseManager(){return mouseManager;}

    public GameCamera getGameCamera(){
        return gameCamera;
    }
    public Graphics getGraphics(){return g;}

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public synchronized void start()
    {
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop()
    {
        if(!running)
            return;
        running = false;
        System.exit(0);
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}