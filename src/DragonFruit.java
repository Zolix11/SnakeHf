import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class DragonFruit extends Fruit{

    Consumer<ArrayList<Point>> putWall;

    /**
     * @param location A GameBoard osztály generált Point struktúra lesz a gyümölcs koordinátája
     * @param putWall ezzel a függvénnyel tud a gyümölcs a pályára falakat rakni
     */
    public DragonFruit(Point location, Consumer<ArrayList<Point>> putWall){
        super(location);
        this.putWall=putWall;
    }

    /**
     * A gyümölcs kirajzoló függvénye
     * @param g2d az osztály melynek a kirajzoló függvényei hívja meg.
     */
    @Override
    void draw(Graphics2D g2d) {
        g2d.setColor(Color.magenta);
        g2d.fillOval(getLocation().x* GameLauncher.UNIT_SIZE,getLocation().y* GameLauncher.UNIT_SIZE,GameLauncher.UNIT_SIZE,GameLauncher.UNIT_SIZE);
    }

    /**
     * A kígyónak adja egy Wall effektjét.
     * @param snake Snake oszályú paraméteren változtat az sárkánygyümölcs, hogy kifejtse a hatását a kígyón.
     */
    @Override
    void addEffect(Snake snake) {
            snake.addEffect(new WallEffect(putWall));
    }
}
