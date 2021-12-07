import java.awt.*;
import java.util.ArrayList;

/**
 * A kétjátékos mód osztálya
 */
public class TwoPlayerMode extends GameBoard {

    Snake snake2;
    DragonFruit dragonFruit;

    /**
     * Az örökölt konstruktoron kívól létrehoz, még egy snaket és egy dragonfruitot.
     */
    public TwoPlayerMode() {
        snake2 = new Snake(generatePoint(),2);
        dragonFruit= new DragonFruit(generatePoint(),this::putWall);
    }

    /**
     * Kirajzolja a pályán lévő elemeket
     * @param g a rajzoláshoz szükséges paraméter
     */
    @Override
    void drawElements(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawMap(g2d);
        snake.draw(g2d);
        snake2.draw(g2d);
        apple.draw(g2d);
        dragonFruit.draw(g2d);
        drawInput(g);
        drawScore(g);
    }

    /**
     * Kirajzolja a játék állását
     * @param g a rajzoláshoz szükséges paraméter
     */
    @Override
    void drawScore(Graphics g) {
        g.setFont(new Font("Consolas", Font.BOLD, 30));
        FontMetrics fontLenght = g.getFontMetrics(g.getFont());
        g.setColor(Color.BLUE);
        g.drawString("Score: " + snake.getBody().size(), 0, g.getFont().getSize());
        g.setColor(Color.YELLOW);
        g.drawString("Score: " + snake2.getBody().size(), GameLauncher.WIDTH - fontLenght.stringWidth("Score: " + snake2.getBody().size()), g.getFont().getSize());

        if (!isRunning()) {
            if (!snake.isAlive() && !snake2.isAlive()) {
                g.setColor(Color.WHITE);
                g.drawString("Draw", GameLauncher.WIDTH - fontLenght.stringWidth("Draw"), g.getFont().getSize() + GameLauncher.HEIGHT / 2);
            } else if (!snake2.isAlive()) {
                g.setColor(Color.BLUE);
                g.drawString("Blue snake won!", GameLauncher.WIDTH - fontLenght.stringWidth("Blue snake won!"), g.getFont().getSize() + GameLauncher.HEIGHT / 2);
            } else {

                g.setColor(Color.YELLOW);
                g.drawString("Yellow snake won!", GameLauncher.WIDTH - fontLenght.stringWidth("Yellow snake won!"), g.getFont().getSize() + GameLauncher.HEIGHT / 2);
            }
            g.drawString("Press any key to get back to menu", (GameLauncher.WIDTH - fontLenght.stringWidth("Press any key to get back to menu")) / 2, GameLauncher.HEIGHT / 2);

        }
    }

    /**
     * Kirajzolja a megfelelő játék bemeneteket.
     * @param g a rajzoláshoz szükséges paraméter
     */
    @Override
    void drawInput(Graphics g) {
        g.setFont(new Font("Consolas", Font.BOLD, 30));
        FontMetrics fontLenght = g.getFontMetrics(g.getFont());
        g.setColor(Color.BLUE);
        g.drawString("Input:WASD special move button:f",0,GameLauncher.HEIGHT-fontLenght.getHeight()/4);
        g.setColor(Color.RED);
        g.drawString("Input: Arrows special move button:m",(GameLauncher.WIDTH - fontLenght.stringWidth("Input: Arrows special move button:m")),GameLauncher.HEIGHT-fontLenght.getHeight()/4);

    }

    /**
     * A játék logikáját leíró függvények hívása.
     */
    @Override
    void gameLogic() {
        snake.move();
        snake2.move();
        checkCollisionWithFruit();
        checkCollisionWall();
        checkSnakeCollisionWithSnake();
        checkSnakeAlive();

    }

    /**
     * Megnézi, hogy a pályán lévő snakek élnek-e
     */
    @Override
    void checkSnakeAlive() {
        if (!snake.isAlive()) {

            setRunning(false);
        }
        if (!snake2.isAlive()) {
            setRunning(false);
        }
    }

    /**
     * Teszteli, hogy snakek nem-e ütköztek a fallal.
     */
    @Override
    void checkCollisionWall() {
        for (Point point : wall) {
            if (point.x == snake.getBody().getFirst().x && point.y == snake.getBody().getFirst().y) {
                snake.setAlive(false);
            }
            if (point.x == snake2.getBody().getFirst().x && point.y == snake2.getBody().getFirst().y) {
                snake2.setAlive(false);
            }
        }
    }

    /**
     * Teszteli, hogy a kígyók nem-e ütköztek egymással.
     */
    void checkSnakeCollisionWithSnake() {
        for (int i = 0; i < snake2.getBody().size(); i++) {
            if (snake.getBody().getFirst().x == snake2.getBody().get(i).x && snake.getBody().getFirst().y == snake2.getBody().get(i).y)
                snake.setAlive(false);
        }
        for (int i = 0; i < snake.getBody().size(); i++) {
            if (snake2.getBody().getFirst().x == snake.getBody().get(i).x && snake2.getBody().getFirst().y == snake.getBody().get(i).y)
                snake2.setAlive(false);
        }

    }

    /**
     * Teszteli, hogy a kígyók nem-e ütköztek egymással
     */
    @Override
    void checkCollisionWithFruit() {
        if (snake.getBody().getFirst().x == apple.getLocation().x && snake.getBody().getFirst().y == apple.getLocation().y) {
            snake.setIncrement(true);
            apple.addEffect(snake);
            apple = new Apple(generatePoint());
        }
        if (snake2.getBody().getFirst().x == apple.getLocation().x && snake2.getBody().getFirst().y == apple.getLocation().y) {
            snake2.setIncrement(true);
            apple.addEffect(snake2);
            apple = new Apple(generatePoint());
        }
        if (snake2.getBody().getFirst().x == dragonFruit.getLocation().x && snake2.getBody().getFirst().y == dragonFruit.getLocation().y) {

            dragonFruit.addEffect(snake2);
            dragonFruit = new DragonFruit(generatePoint(),this::putWall);
        }
        if (snake.getBody().getFirst().x == dragonFruit.getLocation().x && snake.getBody().getFirst().y == dragonFruit.getLocation().y) {

            dragonFruit.addEffect(snake);
            dragonFruit = new DragonFruit(generatePoint(),this::putWall);
        }

    }

    /**
     * Kiegészíti a pályán lévő falakat a a paraméterben kapott arraylisttel
     * @param walls a pályán lévő falak
     */
    public void putWall(ArrayList<Point> walls){
        this.wall.addAll(walls);
    }

    /**
     * @param e a gomb kódja
     */
    @Override
    public void input(int e) {
        snake.setDirection(e);
        snake2.setDirection(e);

    }
}
