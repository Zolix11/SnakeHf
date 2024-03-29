import javax.swing.*;

/**
 * A kétjátékos mód frameje.
 */
public class TwoPlayerFrame extends JFrame {


    /**
     * Létrehozza a framet, a megfelelő beállításokat eszközöli és létrehoz egy kétjátékos panelt.
     * @param gl kell a játék állapotának változtatásához.
     */
    public TwoPlayerFrame(GameLauncher gl) {

        this.setTitle("Two Player");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(GameLauncher.WIDTH, GameLauncher.HEIGHT);
        this.setLocationRelativeTo(null);

        this.add(new TwoPlayerPanel(gl, this));
        this.pack();
        this.setVisible(true);

    }
}
