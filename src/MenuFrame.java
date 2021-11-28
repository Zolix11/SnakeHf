import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {

    MenuFramePanel menuFramePanel;
    MenuFrame(GameLauncher gl){
        //Frame Settings
        this.setTitle("Snake game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(GameLauncher.WIDTH, GameLauncher.HEIGHT);
        this.setBackground(new Color(35, 176, 73));
        this.setLocationRelativeTo(null);
        menuFramePanel = new MenuFramePanel(this,gl);

        this.add(menuFramePanel);
        this.pack();
        this.setVisible(true);
    }
}
