import javax.swing.*;

/**
 * Egyjátékos mód JFrame-je.
 */
public class SinglePlayerFrame extends JFrame {


    /**
     * Létrehozza a framet, a megfelelő beállításokat eszközöli és létrehoz egy egyjátékosmód panelt.
     * @param gl kell a játék állapotának változtatásához.
     */
    public SinglePlayerFrame(GameLauncher gl) {

        this.setTitle("Single Player");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(GameLauncher.WIDTH, GameLauncher.HEIGHT);
        this.setLocationRelativeTo(null);

        this.add(new SinglePlayerPanel(this, gl));
        this.pack();
        this.setVisible(true);

    }
}
