import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class SnakeTest {

    Snake snake;
    Point point;

    @Before
    public void setUp() throws Exception {
        point= new Point(10,10);
        snake = new Snake(point,1);
    }

    @Test
    public void setDirection() {
        assertEquals("None kezdés",Direction.NONE,snake.getDirection());
        snake.setDirection('A');
        assertEquals("Direction left",Direction.LEFT,snake.getDirection());
        snake.setDirection('S');
        snake.setDirection('D');
        assertEquals("Set Direction Right",Direction.RIGHT,snake.getDirection());
    }

    @Test
    public void move() {
        assertEquals("Kezdeti hely",point,snake.getBody().getFirst());
        snake.setDirection('D');
        snake.move();
        point = new Point(point.x+1, point.y);
        assertEquals("Eggyel jobbra ment",point,snake.getBody().getFirst());
        snake.setDirection('S');
        point = new Point(point.x, point.y+1);
        snake.move();
        assertEquals("Eggyel lejebb ment",point,snake.getBody().getFirst());
    }

    @Test
    public void setIncrement() {

        assertFalse("Növekedés tesztelése", snake.isIncrement());
        snake.setIncrement(true);
        assertTrue("Növekedés tesztelése", snake.isIncrement());

        assertEquals("Snake hossza konstruktor híváskor",1,snake.getBody().size());
        snake.move();
        snake.setIncrement(true);
        assertEquals("Snake hossza increment igazra állítva és egyet mozgatva",2,snake.getBody().size());
        snake.setIncrement(true);
        snake.move();
        snake.setIncrement(true);
        snake.move();
        snake.setIncrement(true);
        snake.move();
        assertEquals("snake move, incerement true",5,snake.getBody().size());

    }

    @Test
    public void collideItself(){
       snake.setDirection('A');
       snake.setIncrement(true);
       snake.move();
       snake.setIncrement(true);
       snake.move();
       snake.collideItself();
       assertTrue("Snake collide_1", snake.isAlive());
       snake.setDirection('W');
       snake.setIncrement(true);
       snake.move();
       snake.setDirection('D');
       snake.setIncrement(true);
       snake.move();
       snake.setDirection('S');
       snake.move();
       snake.collideItself();
       assertFalse("Snake collide_2", snake.isAlive());

    }
}