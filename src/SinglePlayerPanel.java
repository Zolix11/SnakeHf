import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A panel, melyben megjelenik a játék
 */
public class SinglePlayerPanel extends JPanel implements ActionListener,Input{

    private final SinglePlayerFrame singlePlayerFrame;
    private final GameLauncher gameLauncher;
    private final SinglePlayerMode singlePlayerMode;
    Player player;
    Leaderboard leaderboard;

    /**
     * Beállítja a megfelelő kinézetet és kér a felhasználózól egy játékosnevet, továbbá inicializálja a gameloopot reprezenzáló timert, mely időnként eventet hív.
     * @param sf frame, mely megjeleníti a panelt.
     * @param gl a játék állapotát ezzel az osztállyal lehet változtatni.
     */
    SinglePlayerPanel(SinglePlayerFrame sf,GameLauncher gl) {
        singlePlayerFrame=sf;
        gameLauncher=gl;
        leaderboard= new Leaderboard();
        this.setPreferredSize(new Dimension(GameLauncher.WIDTH, GameLauncher.HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter(this));

        String nickname = JOptionPane.showInputDialog("Please add a nickname");
        player = new Player(nickname,0);
        singlePlayerMode = new SinglePlayerMode();
        Timer timer = new Timer(GameLauncher.DELAY, this);
        timer.start();
    }

    /**
     * Kirajzolja a legjobb eredményét az eddig játéknak.
     * @param g az osztály melynek a kirajzoló függvényei hívja meg.
     */
    private void drawBestScore(Graphics g){
        g.setColor(Color.YELLOW);
        g.drawString("Best score: "+leaderboard.getLeaderboardlist().get(0).getName()+" "+leaderboard.getLeaderboardlist().get(0).getScore()
                ,0,25);
    }

    /**
     * Kirajzolja a panelra a játék elemeit.
     * @param g az osztály melynek a kirajzoló függvényei hívja meg.
     */
    @Override
    public void paint(Graphics g) {
        singlePlayerMode.drawElements(g);
        drawBestScore(g);

    }

    /**
     * A timer által hívott időközönként meghívódik a gameloop, teszteli, hogy még kell-e a játékot működtetni.
     * @param e vent, mely meghívódik ha változás történik
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(singlePlayerMode.isRunning()) {
            removeAll();
            singlePlayerMode.gameLogic();
            repaint();
        }
        else{
            removeAll();
            repaint();
        }
    }

    /**
     * A gomb lenyomását az adott játékmódnak adja be, ha játék végetér beállítja a menti az eredményeket és visszatér a játék a menübe egy gomb nyomás után.
     * @param input a felhasználó által beadott gomb kódja int-ben.
     */
    @Override
    public void input(int input){
        if(singlePlayerMode.isRunning()) {
            singlePlayerMode.input(input);

        }
        else{
            player.setScore(singlePlayerMode.getScore());
            leaderboard.updateLeaderboard(player);
            leaderboard.writeOutLeaderboard();
            singlePlayerFrame.dispose();
            gameLauncher.switchstate(GameLauncher.STATES.menu);
        }
    }
}



