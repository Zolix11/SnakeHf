import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class SinglePlayerModeTest {

    SinglePlayerMode singlePlayerMode;
    Point p;
    Snake snake;
    Apple apple;
    @Before
    public void setUp() throws Exception {
        singlePlayerMode= new SinglePlayerMode();
        snake=singlePlayerMode.snake;
        apple=singlePlayerMode.apple;
        p=new Point(10,10);

        snake.setBody(p);
        p= new Point(11,10);
        apple=new Apple(p);
        snake.setDirection('D');
    }

    @Test
    public void getScore() {
        assertEquals("Snake hossz", 1,singlePlayerMode.getScore());
    }

    @Test
    public void checkCollisionWall() {
        singlePlayerMode.checkCollisionWall();
        assertTrue("Didnt collide with wall", snake.isAlive());
        snake.setBody(new Point(0,10));
        singlePlayerMode.checkCollisionWall();
        assertFalse("Collided with wall", snake.isAlive());

    }
}