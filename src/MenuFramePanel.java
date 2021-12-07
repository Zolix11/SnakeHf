import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFramePanel extends JPanel implements ActionListener {


    GameLauncher gameLauncher;
    MenuFrame menuFrame;
    JButton singlePlayer, twoPlayer, leaderBoard;
    JLabel welcome;

    /**
     * Megjeleníti a menüt rajta 3 gombbal és egy üdvözlő szöveggel.
     * @param mf kell a frame dispozolásához.
     * @param gl kell a játék állapotának változtatásához.
     */
    MenuFramePanel(MenuFrame mf, GameLauncher gl) {
        gameLauncher = gl;
        menuFrame = mf;
        this.setPreferredSize(new Dimension(GameLauncher.WIDTH, GameLauncher.HEIGHT));
        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(200, 10, 0, 10));
        welcome = new JLabel("Snake Game");
        welcome.setFont(new Font("Calibri", Font.PLAIN, 60));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(welcome);

        singlePlayer = new JButton("Single Player");
        singlePlayer.setFont(new Font("Calibri", Font.PLAIN, 50));
        singlePlayer.setBackground(new Color(0x2dce98));
        singlePlayer.setUI(new StyledButtonUI());
        singlePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);

        singlePlayer.addActionListener(this);
        this.add(singlePlayer);

        this.add(Box.createRigidArea(new Dimension(0, 20)));

        twoPlayer = new JButton("Two player");
        twoPlayer.setFont(new Font("Calibri", Font.PLAIN, 50));
        twoPlayer.setBackground(new Color(0x2dce98));
        twoPlayer.setUI(new StyledButtonUI());
        twoPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);

        twoPlayer.addActionListener(this);
        this.add(twoPlayer);

        this.add(Box.createRigidArea(new Dimension(0, 20)));
        leaderBoard = new JButton("Leaderboard");
        leaderBoard.setFont(new Font("Calibri", Font.PLAIN, 50));
        leaderBoard.setBackground(new Color(0x2dce98));
        leaderBoard.setUI(new StyledButtonUI());
        leaderBoard.setAlignmentX(Component.CENTER_ALIGNMENT);

        leaderBoard.addActionListener(this);
        this.add(leaderBoard);

    }


    /**
     * Ha a gomb lenyomódik akkor átállítja a játék állapotát és a framet disposolja.
     * @param e event, mely meghívódik ha változás történik.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == singlePlayer) {
            gameLauncher.switchstate(GameLauncher.STATES.singleplayer);
            menuFrame.dispose();
        } else if (e.getSource() == twoPlayer) {
            gameLauncher.switchstate(GameLauncher.STATES.twoplayer);
            menuFrame.dispose();
        } else if (e.getSource() == leaderBoard) {
            gameLauncher.switchstate(GameLauncher.STATES.leaderboard);
            menuFrame.dispose();
        }

    }

}
