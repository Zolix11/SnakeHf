import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

 enum Direction {
    UP,
    DOWN,
    RIGHT,
    LEFT,
    NONE
}
/**
 * A kígyó osztálya, melyet a játékos irányít és változtat  a gombok lenyomásaival.
 */
public class Snake {
    private Direction direction;

    private final LinkedList<Point> body;
    private boolean increment;
    private boolean alive;
    private int ID;
    private Effect effect;
    private boolean castEffect;

    /**
     * Konstruktorban beállítódnak a kígyó értékei a megfelelőre, inizializálódik a "feje" és beállítódik az iránya Null-ra mert egyhelyben áll
     * @param location a GameBoard által generált random pont.
     * @param ID egy ID, melyek meglehet különbözetni a kígyókat.
     */
    public Snake(Point location,int ID) {
        this.ID=ID;
        alive=true;
        castEffect=false;
        body = new LinkedList<>();
        body.add(location);
        direction = Direction.NONE;
    }

    /**
     * Megváltoztatja a kígyó irányát.
     * @param way a felhasználó által beadott irány int kódja
     */
    public void setDirection(int way) {
        if(ID==1) {
            switch (way) {
                case 'A':
                    if (direction != Direction.RIGHT) {
                        direction = Direction.LEFT;
                    }
                    break;
                case 'W':
                    if (direction != Direction.DOWN) {
                        direction = Direction.UP;
                    }
                    break;
                case 'D':
                    if (direction != Direction.LEFT) {
                        direction = Direction.RIGHT;

                    }
                    break;
                case 'S':
                    if (direction != Direction.UP) {
                        direction = Direction.DOWN;
                    }
                    break;
                case 'F':
                    if(castEffect) {
                        makeEffect();
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
                case 'M':
                    makeEffect();
                    break;
            }
        }
    }

    /**
     * A kígyó kirajzoltatja magát a pályára.
     * @param g2d z osztály melynek a kirajzoló függvényei hívja meg.
     */
    public void draw(Graphics2D g2d){
        Rectangle temp = new Rectangle();
        temp.height = GameLauncher.UNIT_SIZE;
        temp.width = GameLauncher.UNIT_SIZE;
        for(int i=0; i!=body.size(); i++) {

            temp.x = (int) body.get(i).getX() * GameLauncher.UNIT_SIZE;
            temp.y = (int) body.get(i).getY() * GameLauncher.UNIT_SIZE;
            if(ID==1){
                if(i==0){
                    g2d.setColor(Color.decode("#219ebc"));
                }
                else {
                    g2d.setColor(Color.BLUE);
                }

            }
            else{
                if(i==0){
                    g2d.setColor(Color.decode("#ffb703"));
                }
                else{
                    g2d.setColor(Color.YELLOW);
                }

            }
            g2d.fill(temp);
        }

    }

    /**
     * Irány alapján változtat a snake koordinátáin, és nő a snake, ha incerment boolean igaz állapotú.
     */
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

        collideItself();
    }

    /**
     * Teszteli, hogy a ütközött-e magával a snake.
     */
    public void  collideItself(){
        for(int i=1; i<body.size(); i++){
            if(body.getFirst().x==body.get(i).x && body.getFirst().y==body.get(i).y){
                alive=false;
            }
        }
    }
    public LinkedList<Point> getBody() {
        return body;
    }

    /**
     * @param increment beállítja az incrementet a megfelelő értékre
     */
    public void setIncrement(boolean increment) {
        this.increment = increment;
    }

    /**
     * @return megadja a snake állapotát
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @param alive beállítja a snake állapotát a megfelelőre
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Az effektet felhasználja a snake
     */
    public void makeEffect(){
        if(castEffect) {
            effect.makeEffect(this);
            castEffect=false;
        }


    }

    /**
     * Eltárolja az aktuálisan megkapott effektet
     * @param effect egy megfelelő effect.
     */
    public void addEffect(Effect effect){
        castEffect=true;
        this.effect=effect;
    }

    /**
     * Vissza adja az aktuális irányt
     * @return aktuális irány
     */
    public Direction getDirection() {
        return direction;
    }


    /**
     * Visszaadja hogy nőhet-e a kígyó
     * @return increment
     */
    public boolean isIncrement() {
        return increment;
    }

    public void setBody(Point p){
        body.set(0,p);

    }
}

