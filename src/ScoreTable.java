import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * A panel, mely megjeleníti a top 10 legtöbb pontot és azok játékosait
 */
public class ScoreTable extends JPanel {
    ArrayList<Player> scores;

    /**
     * Létrehoz egy leaderboard osztály, mely kiolvassa json-t és annak az osztálynak elmenti a arraylistjét, mely tárolja az eredményeket.
     */
    public ScoreTable() {
        scores = new Leaderboard().getLeaderboardlist();
        this.setFont(new Font("CONSOLAS", Font.BOLD, 50));
        this.setVisible(true);
    }

    /**
     * Kirajzolja az eredményeket.
     * @param g az osztály melynek a kirajzoló függvényei hívja meg.
     */
    private void drawScores(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.decode("#f77f00"));
        FontMetrics fontMetrics = g2d.getFontMetrics(g2d.getFont());
        g2d.drawString("Rank", 10,fontMetrics.getHeight());
        g2d.drawString("Nickname", 150,fontMetrics.getHeight());
        g2d.drawString("Score", 600,fontMetrics.getHeight());
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.decode("#264653"));
        g2d.draw(new Line2D.Float(0,fontMetrics.getHeight()+10,800,fontMetrics.getHeight()+10));

        g2d.setColor(Color.decode("#343a40"));
        for (int i = 0; i < 10; i++) {
            g2d.drawString((i + 1)+".", 10, i * fontMetrics.getHeight() + (fontMetrics.getHeight()*2));
            g2d.drawString(scores.get(i).getName(), 150, i * fontMetrics.getHeight() + fontMetrics.getHeight()*2);
            g2d.drawString(String.valueOf(scores.get(i).getScore()), 700-fontMetrics.stringWidth(String.valueOf(scores.get(i).getScore())), i * fontMetrics.getHeight() + fontMetrics.getHeight()*2);

        }

    }

    /**
     * A háttér színét megadja.
     * @param g az osztály melynek a kirajzoló függvényei hívja meg.
     */
    private void drawBackground(Graphics g) {
        g.setColor(Color.decode("#2a9d8f"));
        g.fillRect(0, 0, GameLauncher.WIDTH, GameLauncher.HEIGHT);

    }

    /**
     * Meghívja az osztályhoz tartozó rajzoló függvényeket, és megjeleníti őket.
     * @param g az osztály melynek a kirajzoló függvényei hívja meg.
     */
    @Override
    public void paint(Graphics g) {
        drawBackground(g);
        drawScores(g);


    }
}
