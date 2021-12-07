import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {

    /**
     * Létrehozza azt a framet mely megjeleníti a MenuPanelt.
     * @param gl  játék állapotát ezzel az osztállyal lehet változtatni.
     */
    public MenuFrame(GameLauncher gl){
        this.setTitle("Snake game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(GameLauncher.WIDTH, GameLauncher.HEIGHT);
        this.setLocationRelativeTo(null);
        this.add( new MenuFramePanel(this,gl));
        this.pack();
        this.setVisible(true);
    }
}
