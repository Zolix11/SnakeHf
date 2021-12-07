import java.awt.*;

public class Apple extends Fruit{

    /**
     * Meghívja a Fruit osztály konstruktorát.
     * @param location a GameBoard osztály által generált Point struktúra lesz az adott gyümölcs koordinátája
     */
    public Apple(Point location) {
        super(location);
    }

    /**
     * A gyümölcs kirajzoló függvénye
     * @param g2d az osztály melynek a kirajzoló függvényei hívja meg.
     */
    @Override
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.RED);
        g2d.fillOval(getLocation().x* GameLauncher.UNIT_SIZE,getLocation().y* GameLauncher.UNIT_SIZE,GameLauncher.UNIT_SIZE,GameLauncher.UNIT_SIZE);
    }

    /**
     * Igazra állítja a kígyó növekedési értékét.
     * @param snake Snake oszályú paraméteren változtat az alma, hogy kifejtse a hatását a kígyón.
     */
    @Override
    void addEffect(Snake snake) {
        snake.setIncrement(true);
    }


}
