import java.awt.*;
import java.util.Random;

public class Fruit {
    private Point location;

    public Fruit() {
        Random r = new Random();
        location= new Point();
        int x = r.nextInt(GameLauncher.gameWIDTH);
        int y = r.nextInt(GameLauncher.gameHEIGHT);
        location.x= x;
        location.y= y;
    }

    public void draw(Graphics2D g2d){

        Rectangle temp = new Rectangle();
        temp.height = GameLauncher.UNIT_SIZE;
        temp.width = GameLauncher.UNIT_SIZE;
        temp.x=location.x* GameLauncher.UNIT_SIZE;
        temp.y=location.y* GameLauncher.UNIT_SIZE;
        g2d.setColor(Color.RED);
        g2d.fill(temp);

    }
    public Point getLocation() {
        return location;
    }

}
