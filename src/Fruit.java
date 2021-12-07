import java.awt.*;

/**
 *Absztrakt osztály, melyből leszármaznak a játékban megjelenő gyümölcsök.
 */
abstract class Fruit {
    private final Point location;

    /**
     * @param location a GameBoard osztály által generált Point struktúra lesz az adott gyümölcs koordinátája
     */
    public Fruit(Point location) {
        this.location=location;
    }

    /**
     A gyümölcs kirajzoló függvénye
     * @param g2d az osztály melynek a kirajzoló függvényei hívja meg.
     */
    abstract void draw(Graphics2D g2d);

    /**
     * @param snake a gyümölcs osztály egy effecktet a paraméterben adott kígyónak.
     */
    abstract void addEffect(Snake snake);

    /**
     * @return Visszaadja az adott gyümölcs Point struktúrában tárolt a koordinátáját, helyét.
     */
    public Point getLocation() {
        return location;
    }

    }

