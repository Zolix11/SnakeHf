import java.awt.*;


public class SinglePlayerMode extends GameBoard {

    /**
     * Megfelelő sorrendben végig megy a játék logikáján
     */
    @Override
    void gameLogic() {
        snake.move();
        checkSnakeAlive();
        checkCollisionWall();
        checkCollisionWithFruit();
    }
    public int getScore(){
        return snake.getBody().size();
    }

    /**
     *teszteli ,hogy a snake életben van-e és állít az osztály booleanjén
     */
    @Override
    void checkSnakeAlive() {
        if (!snake.isAlive()) {
            setRunning(false);
        }
    }

    /**
     * Kirajzolja a pálya elemeit
     * @param g rajzoláshoz szkséges osztály
     */
    @Override
    public void drawElements(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawMap(g2d);
        snake.draw(g2d);
        apple.draw(g2d);
        drawInput(g);
        drawScore(g);
    }

    /**
     * Teszteli a fallal való ütközést
     */
    @Override
    void checkCollisionWall() {
        for(Point point : wall){
            if(point.x==snake.getBody().getFirst().x && point.y==snake.getBody().getFirst().y){
                snake.setAlive(false);
            }
        }
    }


    /**
     * Atadja a megfelelő bementet a kígyónak
     * @param e a betú ASCII kódja
     */
    @Override
    public void input(int e) {
        snake.setDirection(e);
    }
    @Override
    void checkCollisionWithFruit() {
        if (snake.getBody().getFirst().x == apple.getLocation().x && snake.getBody().getFirst().y == apple.getLocation().y) {
            apple.addEffect(snake);
            apple= new Apple(generatePoint());
        }
    }


    /**
     * Kirajzolja a játék állását
     * @param g a rajzoláshoz szükséges paraméter
     */
    @Override
    void drawScore(Graphics g) {
        if(g instanceof Graphics2D) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

        if (isRunning()) {
            g2d.setColor(Color.BLUE);
            g2d.setFont(new Font("Consolas", Font.BOLD, 30));
            FontMetrics hossz = g.getFontMetrics(g.getFont());
            g2d.drawString("Score: " + snake.getBody().size(), GameLauncher.WIDTH - hossz.stringWidth("Score: " + snake.getBody().size()), g.getFont().getSize());
        }
        else{
            g2d.setColor(Color.red);
            g2d.setFont(new Font("Consolas",Font.BOLD,30));
            FontMetrics hossz = g.getFontMetrics(g.getFont());
            g2d.drawString("You collieded, the score was: "+ snake.getBody().size(),GameLauncher.WIDTH-hossz.stringWidth("You collieded, the score was: "+ snake.getBody().size()),g.getFont().getSize());
            g2d.drawString("Press any key to get back to menu", (GameLauncher.WIDTH-hossz.stringWidth("Press any key to get back to menu"))/2,GameLauncher.HEIGHT/2);

        }
        }
    }

    /**
     * Kirajzolja a megfelelő játék bemeneteket.
     * @param g a rajzoláshoz szükséges paraméter
     */
    @Override
    void drawInput(Graphics g) {
        g.setFont(new Font("Consolas", Font.BOLD, 30));
        g.drawString("Input: WASD",0,GameLauncher.HEIGHT);
    }
}
