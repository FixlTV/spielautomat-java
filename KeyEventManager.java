import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class KeyEventManager implements KeyListener {

    private Spielautomat spiel;

    KeyEventManager(Spielautomat start) {
        spiel = start;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println(e);
        switch(KeyEvent.getKeyText(e.getKeyCode())) {
            case "Space": 
                try {
                    spiel.spiele();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}