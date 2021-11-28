import javax.swing.*;
import java.awt.*;

public class TwoPlayerFrame extends JFrame {


    TwoPlayerPanel twoPlayerPanel;
    public TwoPlayerFrame(GameLauncher gl) {

        this.setTitle("Two Player");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(GameLauncher.WIDTH, GameLauncher.HEIGHT);
        this.setLocationRelativeTo(null);
        twoPlayerPanel = new TwoPlayerPanel(gl, this);

        this.add(twoPlayerPanel);
        this.pack();
        this.setVisible(true);

    }
}
