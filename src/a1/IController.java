package a1;

/**
 * Controller interface
 */
public interface IController {
    int getSpeed();
    void setView(IView view);
    void setGame(Game game);
    void startGame();
    void setDefaults(int fps, int speed);
    void changeDir(Constants.Direction direction);
    void endGame();
    void togglePlay();
    void restart();
}
