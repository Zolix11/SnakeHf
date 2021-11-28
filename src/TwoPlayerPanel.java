import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TwoPlayerPanel extends JPanel implements ActionListener,Input {

    GameLauncher gameLauncher;
    TwoPlayerFrame twoPlayerFrame;

    boolean running;
    Snake snake1;
    Snake snake2;
    Timer timer;
    Fruit fruit;

    public TwoPlayerPanel(GameLauncher gl, TwoPlayerFrame tw){
        gameLauncher=gl;
        twoPlayerFrame=tw;
        this.setPreferredSize(new Dimension(GameLauncher.WIDTH, GameLauncher.HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter(this));
        running=true;
        snake1 = new Snake(1);
        snake2 = new Snake(2);
        fruit = new Fruit();
        timer = new Timer(GameLauncher.DELAY,this);
        timer.start();
        }

    public void checkCollision(){
        int headX=snake1.getBody().getFirst().x;
        int headY=snake1.getBody().getFirst().y;
        int head2X=snake2.getBody().getFirst().x;
        int head2Y=snake2.getBody().getFirst().y;

        if(headX<0 || headX>GameLauncher.gameWIDTH || headY<0 || headY> GameLauncher.gameHEIGHT-1){
            running=false;
        }
        for(int i=1; i<snake1.getBody().size(); i++){
            if(headX==snake1.getBody().get(i).x && headY==snake1.getBody().get(i).y){
                running=false;
                break;
            }
        }
        if(headX==fruit.getLocation().x && headY==fruit.getLocation().y){
            snake1.setIncrement(true);
            fruit= new Fruit();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            removeAll();
            snake1.move();
            snake2.move();
            checkCollision();
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
            snake1.setDirection(input);
            snake2.setDirection(input);
            System.out.println(input);
        }
        else{
            twoPlayerFrame.dispose();
            gameLauncher.switchstate(GameLauncher.STATES.menu);
        }
    }

}
