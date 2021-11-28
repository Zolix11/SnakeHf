import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

public class Snake {
    private enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT,
        NONE
    }

    private final LinkedList<Point> body;
    private Direction direction;
    private boolean increment;
    private int ID;

    public Snake(int ID) {
        this.ID=ID;
        Random r = new Random();
        int x = r.nextInt(GameLauncher.gameWIDTH);
        int y = r.nextInt(GameLauncher.gameHEIGHT);
        body = new LinkedList<>();
        body.add(new Point(x, y));
        direction = Direction.NONE;
    }

    public void setDirection(int way) {
        if(ID==1) {
            switch (way) {
                case 'a':
                    if (direction != Direction.RIGHT) {
                        direction = Direction.LEFT;
                    }
                    break;
                case 'w':
                    if (direction != Direction.DOWN) {
                        direction = Direction.UP;
                    }
                    break;
                case 'd':
                    if (direction != Direction.LEFT) {
                        direction = Direction.RIGHT;

                    }
                    break;
                case 's':
                    if (direction != Direction.UP) {
                        direction = Direction.DOWN;
                    }
                    break;
            }
        }
        else{
            switch (way) {
                case KeyEvent.VK_LEFT:
                    if (direction != Direction.RIGHT) {
                        direction = Direction.LEFT;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != Direction.DOWN) {
                        direction = Direction.UP;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != Direction.LEFT) {
                        direction = Direction.RIGHT;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != Direction.UP) {
                        direction = Direction.DOWN;
                    }
                    break;
            }
        }
    }

    public void draw(Graphics2D g2d){
        Rectangle temp = new Rectangle();
        temp.height = GameLauncher.UNIT_SIZE;
        temp.width = GameLauncher.UNIT_SIZE;
        for(int i=0; i!=body.size(); i++) {
            temp.x = (int) body.get(i).getX() * GameLauncher.UNIT_SIZE;
            temp.y = (int) body.get(i).getY() * GameLauncher.UNIT_SIZE;
            if(ID==1){
                g2d.setColor(Color.BLUE);
            }
            else{
                g2d.setColor(Color.YELLOW);
            }
            g2d.fill(temp);
        }

    }
    public void move() {
        Point tail = body.getLast();
        int i = body.size() - 1;
        while (body.getFirst() != body.get(i)) {
            body.set(i, body.get(i - 1));
            i--;
        }

        switch (direction) {

            case UP:
                body.set(0, new Point(body.getFirst().x, body.getFirst().y - 1));
                break;

            case DOWN:
                body.set(0, new Point(body.getFirst().x, body.getFirst().y + 1));
                break;
            case LEFT:

                body.set(0, new Point(body.getFirst().x - 1, body.getFirst().y));
                break;

            case RIGHT:
                body.set(0, new Point(body.getFirst().x + 1, body.getFirst().y));
                break;
            case NONE:
                break;
        }

        if (increment) {
            body.add(tail);
            increment = false;
        }
    }

    public LinkedList<Point> getBody() {
        return body;
    }

    public void setIncrement(boolean increment) {
        this.increment = increment;
    }
}
