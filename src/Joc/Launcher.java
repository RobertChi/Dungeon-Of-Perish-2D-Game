package Joc;

public class Launcher {
    public static void main(String[] args)
    {
        Game game = new Game("Dungeon Of Perish", 15*64, 15*64);
        game.start();
    }
}
