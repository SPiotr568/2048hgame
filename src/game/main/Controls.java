package game.main;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controls implements EventHandler<KeyEvent> {
    private Game game;

    public Controls(Game game) {
        this.game = game;
    }

    @Override
    public void handle(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        switch (keyCode) {
            case UP:
                System.out.println("up");
                game.moveUP();
                break;
            case DOWN:
                System.out.println("down");
                game.moveDOWN();
                break;
            case LEFT:
                System.out.println("left");
                game.moveLEFT();
                break;
            case RIGHT:
                System.out.println("right");
                game.moveRIGHT();
                break;
            default:
                break;
        }
    }
}
