package game.main;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controls implements EventHandler<KeyEvent> {

    //@Override
    public void keyReleased(KeyEvent e) {}
    //@Override
    public void keyTyped(KeyEvent e) {}

    /*public void bind() {
        addKeyListener(this);
    }

    public void unbind() {
        removeKeyListener(this);
    }*/

    @Override
    public void handle(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        switch (keyCode) {
            case UP:
                System.out.println("up");
                break;
            case DOWN:
                System.out.println("down");
                break;
            case LEFT:
                System.out.println("left");
                break;
            case RIGHT:
                System.out.println("right");
                break;
            default:
                break;
        }
    }
}
