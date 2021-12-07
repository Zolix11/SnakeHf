/**
 * A fő osztály melyben irányítja, hogy mely része jelenik meg a játéknak.
 */
public class GameLauncher {


    public static final int UNIT_SIZE = 40;
    public static final int gameWIDTH=30;
    public static final int gameHEIGHT=20;
    public static final int WIDTH = gameWIDTH*UNIT_SIZE;
    public static final int HEIGHT = gameHEIGHT*UNIT_SIZE;
    public static final int DELAY = 200;

    /**
     * A játék lehetséges állapotai
     */
    public enum STATES{
        menu,
        singleplayer,
        twoplayer,
        leaderboard
    }


    /**
     * A konstruktorban meghívja a MenuFrame osztályt.
     */
    public GameLauncher(){
        new MenuFrame(this);
    }


    /**
     * Változtatja a játék állapotát
     * @param state egy adott osztályállapota a paraméter
     */
    public void switchstate(STATES state){
        switch (state){
            case menu:
                new MenuFrame(this);
                break;
            case singleplayer:
                new SinglePlayerFrame(this);
                break;
            case twoplayer:
                new TwoPlayerFrame(this);
                break;
            case leaderboard:
                new LeaderBoardFrame(this);
                break;
        }
    }
}
