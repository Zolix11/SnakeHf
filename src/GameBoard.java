import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Absztrakt osztály, melyből leszármaznak a játékmódok.
 * Implementálja az Input interfészt, melyet a leszármazott osztályok implementálnak.
 *
 */
abstract class GameBoard implements Input{

    public Snake snake;
    public Apple apple;
    public  ArrayList<Point> wall;
    private boolean running;

    /**
     * A konstruktor létrehoz egy normál singleplayer játékmódot.
     */
    public GameBoard() {
        wall = new ArrayList<>(GameLauncher.WIDTH*GameLauncher.HEIGHT);
        setWall();
        snake = new Snake(generatePoint(),1);
        apple = new Apple(generatePoint());
        running = true;
    }

    /**
     * Kirajzolja a pályán lévő elemeket.
     * @param g a rajzoláshoz szükséges paraméter
     */
    abstract void drawElements(Graphics g);

    /**
     * Kirajzolja a játékos/ok pontjait.
     * @param g a rajzoláshoz szükséges paraméter
     */
    abstract void drawScore(Graphics g);

    /**
     * Kirajzolja, hogy mivel kell irányítani a játékot.
     * @param g a rajzoláshoz szükséges paraméter
     */
    abstract void drawInput(Graphics g);

    /**
     * Az adott játékmód logikáját tartalmazó függvény.
     */
    abstract  void gameLogic();

    /**
     * Terszteli, hogy a kígyó él-e
     */
    abstract void checkSnakeAlive();

    /**
     * Teszteli a kígyónak a fallal való ütközését.
     */
    abstract void checkCollisionWall();

    /**
     * Teszteli a kígyónak a gyümölccsel való ütközését.
     */
    abstract void checkCollisionWithFruit();

    /**
     * A pályán megfelelő helyre generál egy koordinátát.
     * @return koordináta, melyet egy kígyó vagy gyümölcs a konstruktorában megkap-
     */
    public Point generatePoint(){
        Point temp;
        while(true) {
            int x = ThreadLocalRandom.current().nextInt(1, GameLauncher.gameWIDTH - 1);
            int y = ThreadLocalRandom.current().nextInt(1, GameLauncher.gameHEIGHT - 1);
            temp = new Point(x,y);
            if(!wall.contains(temp)){
                if(running){
                    if(!snake.getBody().contains(temp))
                        if(apple.getLocation()!=temp){
                            break;
                        }
                }
                else{
                    break;
                }

            }
        }
        return temp;
    }

    /**
     * Kirajzolja a pálya hátterét, amelyen mozog a kígyó
     * @param g2d  rajzoláshoz szükséges paraméter
     */
    void drawMap(Graphics2D g2d){
        for (int i = 0; i < GameLauncher.gameWIDTH; i++) {
            for (int j = 0; j < GameLauncher.gameHEIGHT; j++) {
                if (i % 2 == j % 2) {
                    g2d.setColor(Color.decode("#59981A"));
                } else {
                    g2d.setColor(Color.decode("#81B622"));
                }
                g2d.fillRect(i * GameLauncher.UNIT_SIZE, j * GameLauncher.UNIT_SIZE, GameLauncher.UNIT_SIZE, GameLauncher.UNIT_SIZE);
            }
        }
        for(Point point: wall){
            g2d.setColor(Color.decode("#7f5539"));
            g2d.fillRect(point.x * GameLauncher.UNIT_SIZE, point.y * GameLauncher.UNIT_SIZE, GameLauncher.UNIT_SIZE, GameLauncher.UNIT_SIZE);
        }
    }

    /**
     * Beállítja a pálya elején a falakat a megfelelő helyre.
     */
    public void setWall(){
        Point temp;
        for(int i=0; i<GameLauncher.gameHEIGHT; i++){
                temp= new Point(0,i);
                wall.add(temp);
                temp= new Point(GameLauncher.gameWIDTH-1,i);
                wall.add(temp);
        }
        for(int j=1; j<GameLauncher.gameWIDTH-1; j++){
                temp= new Point(j,0);
                wall.add(temp);
                temp= new Point(j,GameLauncher.gameHEIGHT-1);
                wall.add(temp);
        }
    }

    /**
     * @param running megfelelő boolean a játék adott részéhez
     */
    public void setRunning(boolean running) {
        this.running = running;
    }


    /**
     * @return vissza adja a running boolean értékét
     */
    public boolean isRunning() {
        return running;
    }

}
