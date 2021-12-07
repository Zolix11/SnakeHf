import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * A gombnyomások beolvásáért felelősosztály.
 */
public class MyKeyAdapter extends KeyAdapter{

    Input actualPanel;

    /**
     * @param ap egy osztály input metódusa
     */
    public MyKeyAdapter(Input ap){
        actualPanel=ap;

    }

    /**
     * Meghívja az elmentett osztály input metódusát és paraméterként beadja a lenyomott gombkódját.
     * @param e a lenyomot gomb eventje
     */
    @Override
    public void keyPressed(KeyEvent e){
        actualPanel.input(e.getKeyCode());
    }
}