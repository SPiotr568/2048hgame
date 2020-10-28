package game.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.removeKeyListener;

public class Controls implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                System.out.println("up");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("down");
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("left");
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("right");
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    public void bind() {
        addKeyListener(this);
    }

    public void unbind() {
        removeKeyListener(this);
    }
}
