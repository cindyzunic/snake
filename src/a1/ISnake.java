package a1;

import java.util.ArrayList;

/**
 * Snake Interface
 */
public interface ISnake {

    int getLength(); // return snake length
    ArrayList<Cell> getOccupied(); // return array of cells occupied by snake
    Cell move(); // return new position of snake's head
    void change(Constants.Direction newDirection);
    void grow();
    void shrink(boolean reset);
}
