import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SinglePlayerPanel extends JPanel implements ActionListener,Input{
    boolean running;
    Snake snake;
    Timer timer;
    Fruit fruit;
    SinglePlayerFrame singlePlayerFrame;
    GameLauncher gameLauncher;

    SinglePlayerPanel(SinglePlayerFrame sf,GameLauncher gl) {
        singlePlayerFrame=sf;
        gameLauncher=gl;
        this.setPreferredSize(new Dimension(GameLauncher.WIDTH, GameLauncher.HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter(this));
        running= true;
        snake = new Snake(2);
        fruit= new Fruit();
        timer = new Timer(GameLauncher.DELAY,this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        drawMap(g2d);
        snake.draw(g2d);
        fruit.draw(g2d);
        g2d.setFont(new Font("Consolas",Font.BOLD,30));
        FontMetrics hossz= getFontMetrics(g.getFont());
        if(running){

            g2d.drawString("Score: "+ snake.getBody().size(),GameLauncher.WIDTH-hossz.stringWidth("Score: "+snake.getBody().size()),g2d.getFont().getSize());
        }
        else{
            g2d.drawString("You collieded the score was: "+ snake.getBody().size(),GameLauncher.WIDTH-hossz.stringWidth("You collieded the score was: "+snake.getBody().size()),g2d.getFont().getSize());
            g2d.setColor(Color.red);
            g2d.drawString("Press any key to get back to menu", (GameLauncher.WIDTH-hossz.stringWidth("Press any key to get back to menu"))/2,GameLauncher.HEIGHT/2);
        }
    }
    public void drawMap(Graphics2D g2d){
        for (int i = 0; i < GameLauncher.WIDTH; i++) {
            for (int j = 0; j < GameLauncher.HEIGHT; j++) {
                if (i % 2 == j % 2) {
                    g2d.setColor(Color.decode("#59981A"));
                } else {
                    g2d.setColor(Color.decode("#81B622"));
                }
                g2d.fillRect(i * GameLauncher.UNIT_SIZE, j * GameLauncher.UNIT_SIZE, GameLauncher.UNIT_SIZE, GameLauncher.UNIT_SIZE);
            }
        }
    }
    public void checkCollision(){

        int headX=snake.getBody().getFirst().x;
        int headY=snake.getBody().getFirst().y;
        if(headX<0 || headX>GameLauncher.gameWIDTH || headY<0 || headY> GameLauncher.gameHEIGHT-1){
            running=false;
        }
        for(int i=1; i<snake.getBody().size(); i++){

            if(headX==snake.getBody().get(i).x && headY==snake.getBody().get(i).y){
                running=false;
                break;
            }
        }

    }

    public void checkCollisionWithFood() {
        if (snake.getBody().getFirst().x == fruit.getLocation().x && snake.getBody().getFirst().y == fruit.getLocation().y) {
            snake.setIncrement(true);
            fruit = new Fruit();
            /*
            boolean generate=true;

            while(generate){
                for(int i=0; i<snake.getBody().size(); i++){
                    if(fruit.getLocation()!=snake.getBody().get(i)){
                        generate=false;
                    }
                }
                fruit= new Fruit();
             }*/

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            removeAll();
            snake.move();
            checkCollision();
            checkCollisionWithFood();
            repaint();
        }
        else{
            removeAll();
            repaint();
        }
    }

    @Override
    public void input(int input){
        if(running) {
            snake.setDirection(input);
            System.out.println(input);
        }
        else{
            singlePlayerFrame.dispose();
            gameLauncher.switchstate(GameLauncher.STATES.menu);
        }
    }
}



