import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A panel, melyben megjelenik a játék
 */
public class TwoPlayerPanel extends JPanel implements ActionListener,Input {

    GameLauncher gameLauncher;
    TwoPlayerFrame twoPlayerFrame;
    Timer timer;
    TwoPlayerMode twoPlayerMode;

    /**
     * Beállítja a megfelelő kinézetet és kér a felhasználózól egy játékosnevet, továbbá inicializálja a gameloopot reprezenzáló timert, mely időnként eventet hív.
     * @param gl a játék állapotát ezzel az osztállyal lehet változtatni.
     * @param tw a játék állapotát ezzel az osztállyal lehet változtatni.
     */
    public TwoPlayerPanel(GameLauncher gl, TwoPlayerFrame tw){
        gameLauncher=gl;
        twoPlayerFrame=tw;
        twoPlayerMode =new TwoPlayerMode();
        this.setPreferredSize(new Dimension(GameLauncher.WIDTH, GameLauncher.HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter(this));

        timer = new Timer(GameLauncher.DELAY,this);
        timer.start();
        }

    /**
     * Kirajzolja a panelra a játék elemeit.
     * @param g az osztály melynek a kirajzoló függvényei hívja meg.
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        twoPlayerMode.drawElements(g2d);


    }
    /**
     * A timer által hívott időközönként meghívódik a gameloop, teszteli, hogy még kell-e a játékot működtetni.
     * @param e vent, mely meghívódik ha változás történik
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(twoPlayerMode.isRunning()) {
            removeAll();
            twoPlayerMode.gameLogic();
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
        if(twoPlayerMode.isRunning()) {
            twoPlayerMode.input(input);
        }
        else{
            twoPlayerFrame.dispose();
            gameLauncher.switchstate(GameLauncher.STATES.menu);
        }
    }

}
