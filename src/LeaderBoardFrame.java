import javax.swing.*;

/**
 * A leaderboardot megjelenító osztály.
 */
public class LeaderBoardFrame extends JFrame{

    /**
     * A konstruktorban beállítódnak a megfelelő frame beállítások, és létrejön a panelmelyben meglesznek jelenítve az eredmények.
     * @param gl kell a játék állapotának változtatásához.
     */
    public LeaderBoardFrame(GameLauncher gl){

        this.setTitle("Leaderboard");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(GameLauncher.WIDTH, GameLauncher.HEIGHT);
        this.setLocationRelativeTo(null);
        this.add(new LeaderBoardPanel(this,gl));
        this.pack();
        this.setVisible(true);
    }


}
