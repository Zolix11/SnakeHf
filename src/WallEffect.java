import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Speciális effekct melyet egy sárkánygyümölcs generál
 */
public class WallEffect extends Effect{
    Consumer<ArrayList<Point>> putWall;

    /**
     * @param putWall egy függvény pointer
     */
    public WallEffect( Consumer<ArrayList<Point>> putWall){
        this.putWall = putWall;
    }

    /**
     * @param snake mely alapján kirajzolódik a kígyó hosszúságú fal.
     */
    @Override
    void makeEffect(Snake snake) {
        ArrayList<Point> temp = new ArrayList<>(snake.getBody());
        putWall.accept(temp);
    }
}
