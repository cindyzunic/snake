package a1;

import java.util.ArrayList;

/**
 * Board interface
 */
public interface IBoard {
    int getScore();
    void setScore(int score);
    Cell getFood();
    ArrayList<Item> getPowerUps();
    void moveSnake();
    void changeDir(Constants.Direction direction);
    Cell[] getOccupied();
    int getSnakeLength();
}
