public class GameLauncher {


    public static final int UNIT_SIZE = 40;
    public static final int gameWIDTH=30;
    public static final int gameHEIGHT=20;
    public static final int WIDTH = gameWIDTH*UNIT_SIZE;
    public static final int HEIGHT = gameHEIGHT*UNIT_SIZE;
    public static final int DELAY = 150;

    public enum STATES{
        menu,
        singleplayer,
        twoplayer,
        leaderboard
    }


    public GameLauncher(){
        new MenuFrame(this);
    }


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
                break;
        }
    }
}
