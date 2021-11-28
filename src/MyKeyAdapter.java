import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter extends KeyAdapter{

    Input actualPanel;
    public MyKeyAdapter(Input ap){
        actualPanel=ap;

    }
    @Override
    public void keyPressed(KeyEvent e){
        actualPanel.input(e.getKeyCode());
    }
}