package a1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by cindy on 2016-05-19.
 */
public class KeyboardInput implements KeyListener {

    private IController controller;

    private final int UP = 38;
    private final int DOWN = 40;
    private final int LEFT = 37;
    private final int RIGHT = 39;

    private final int W = 87;
    private final int S = 83;
    private final int A = 65;
    private final int D = 68;

    private final int P = 80;
    private final int R = 82;

    public void setController(IController controller) {
        this.controller = controller;
    }

    public void keyTyped(KeyEvent e) {
        //unused
    }

    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case UP: {}
            case W: {
                controller.changeDir(Constants.Direction.UP);
                break;
            }
            case DOWN: {}
            case S: {
                controller.changeDir(Constants.Direction.DOWN);
                break;
            }
            case LEFT: {}
            case A: {
                controller.changeDir(Constants.Direction.LEFT);
                break;
            }
            case RIGHT: {}
            case D: {
                controller.changeDir(Constants.Direction.RIGHT);
                break;
            }
            case P: {
                controller.togglePlay();
                break;
            }
            case R: {
                controller.restart();
                break;
            }
            default: {
                break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        //unused
    }
}
