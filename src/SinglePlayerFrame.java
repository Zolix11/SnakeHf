import javax.swing.*;
import java.awt.*;

public class SinglePlayerFrame extends JFrame {


    SinglePlayerPanel singlePlayerPanel;
    public SinglePlayerFrame(GameLauncher gl) {

        this.setTitle("Single Player");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(GameLauncher.WIDTH, GameLauncher.HEIGHT);
        this.setLocationRelativeTo(null);


        singlePlayerPanel = new SinglePlayerPanel(this, gl);

        this.add(singlePlayerPanel);
        this.pack();
        this.setVisible(true);

    }
}
