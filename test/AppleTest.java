import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class AppleTest {

    Snake snake;
    Apple apple;
    Point point;
    @Before
    public void setUp() throws Exception {
        point= new Point(10,10);
        snake= new Snake(point,1);
        point = new Point(point.x,point.y+1);
        apple=new Apple(point);
    }
    @Test
    public void addEffect() {
        apple.addEffect(snake);
        assertTrue("Apple added effect to snake",snake.isIncrement());
    }
}