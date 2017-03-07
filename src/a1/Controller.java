package a1;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller class, logic for the game
 */
public class Controller implements IController {
    private static Game game;
    private static IBoard board;
    private static Timer repaintTimer;
    private static Timer updateTimer;

    private boolean playing = false;

    private int speed = Constants.DEFAULT_SPEED;
    private IView view;


    public Controller(IBoard board) {
        this.board = board;
    }

    public void setView(IView view) { this.view = view; }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getSpeed() {
        return speed;
    }

    public void setDefaults(int fps, int speed) {
        view.setFps(fps);
        setSpeed(speed);
    }

    public void startGame() {
        playing = true;
        repaintTimerStart();
        updateTimerStart();
    }

    private void setSpeed(int speed) {
        this.speed = speed;
    }

    public void endGame() {
        repaintTimer.cancel();
        updateTimer.cancel();
    }
    public void changeDir(Constants.Direction direction) {
        board.changeDir(direction);
    }

    public void togglePlay() {
        if(playing) {
            repaintTimer.cancel();
            updateTimer.cancel();
            playing = false;
            view.pause();
        } else {
            startGame();
        }
    }

    public void restart() {
        repaintTimer.cancel();
        updateTimer.cancel();
        board = new Board();
        view.restart(board);
        startGame();
        togglePlay();
    }

    private void repaintTimerStart() {
        repaintTimer = new Timer();
        repaintTimer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        view.repaint();
                    }
                },
                0,
                1000/view.getFps()
        );
    }

    private void updateTimerStart() {
        updateTimer = new Timer();
        updateTimer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        board.moveSnake();
                    }
                },
                0,
                1000/(speed * 4)
        );
    }
}
