import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Jpanel melyben megjelennek az egyszemélyes játékmód legjobb 10 eredménye, továbbá egy gombtalálható rajta a visszalépéshez.
 */
public class LeaderBoardPanel extends JPanel implements ActionListener {

    private LeaderBoardFrame leaderBoardFrame;
    private final GameLauncher gameLauncher;
    private JButton backButton;
    private JLabel label;
    private JPanel scoreTable;

    /**
     * A Jpanel, mely megjelenik a frameben, található benne egy label, egy gomb és egy újabb panelt a ScoreTable-t, amelyen kirajzolódnak az eredmények.
     * @param lf frame, mely megjeleníti a panelt.
     * @param gl a játék állapotát ezzel az osztállyal lehet változtatni.
     */
    public LeaderBoardPanel(LeaderBoardFrame lf, GameLauncher gl){
        gameLauncher=gl;
        leaderBoardFrame=lf;
        this.setBorder(new EmptyBorder(10, 200, 0, 200));
        this.setPreferredSize(new Dimension(GameLauncher.WIDTH, GameLauncher.HEIGHT));
        this.setBackground(Color.decode("#264653"));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        label = new JLabel("Top 10 scores");
        label.setFont((new Font("CONSOLAS", Font.BOLD, 50)));
        label.setForeground(Color.decode("#e9c46a"));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(label);

        scoreTable = new ScoreTable();
        scoreTable.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(scoreTable);

        this.add(Box.createRigidArea(new Dimension(0, 10)));
        backButton= new JButton("Return to menu");
        backButton.setFont(new Font("Calibri", Font.PLAIN, 30));
        backButton.setBackground(new Color(0x2dce98));
        backButton.setUI( new StyledButtonUI());
        backButton.addActionListener(this);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(backButton);
        this.setFocusable(true);
        this.setVisible(true);
    }

    /**
     * Ha a gomb lenyomódik akkor átállítja a játék állapotát a menüre és ezt a framet disposolja.
     * @param e event, mely meghívódik ha változás történik
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backButton){
            leaderBoardFrame.dispose();
            gameLauncher.switchstate(GameLauncher.STATES.menu);
        }
    }
}
