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
    Image image;
    MenuFramePanel(MenuFrame mf, GameLauncher gl){
        gameLauncher=gl;
        menuFrame=mf;
        image = new ImageIcon("Snake.png").getImage();
        this.setPreferredSize(new Dimension(GameLauncher.WIDTH, GameLauncher.HEIGHT));
        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(200, 10, 0, 10));
        welcome = new JLabel("Snake Game");
        welcome.setFont(new Font("Calibri", Font.PLAIN, 60));
        welcome.setBackground(Color.red);
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(welcome);
        this.add(Box.createRigidArea(new Dimension(0,100)));
        singlePlayer = new JButton("Single Player");
        singlePlayer.setFont(new Font("Calibri", Font.PLAIN, 50));
        singlePlayer.setBackground(new Color(0x2dce98));
        singlePlayer.setUI(new StyledButtonUI());
        singlePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);

        singlePlayer.addActionListener(this);
        this.add(singlePlayer);

        this.add(Box.createRigidArea(new Dimension(0,20)));

        twoPlayer = new JButton("Two player");
        twoPlayer.setFont(new Font("Calibri", Font.PLAIN, 50));
        twoPlayer.setBackground(new Color(0x2dce98));
        twoPlayer.setUI(new StyledButtonUI());
        twoPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);

        twoPlayer.addActionListener(this);
        this.add(twoPlayer);

        this.add(Box.createRigidArea(new Dimension(0,20)));

        leaderBoard = new JButton("Leaderboard");
        leaderBoard.setFont(new Font("Calibri", Font.PLAIN, 50));
        leaderBoard.setBackground(new Color(0x2dce98));
        leaderBoard.setUI(new StyledButtonUI());
        leaderBoard.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(leaderBoard);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==singlePlayer){
            gameLauncher.switchstate(GameLauncher.STATES.singleplayer);
            menuFrame.dispose();
        }
        else if(e.getSource()==twoPlayer){
            gameLauncher.switchstate(GameLauncher.STATES.twoplayer);
            menuFrame.dispose();
        }
        else{

        }
    }
}
