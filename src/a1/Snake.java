package a1;

import java.util.ArrayList;

/**
 * Implementation of Snake Interface
 */
public class Snake implements ISnake {
    Cell head;
    ArrayList<Cell> body;
    final int length = 3; // initial length

    Constants.Direction direction = Constants.Direction.RIGHT;

    public Snake() {
        body = new ArrayList<Cell>();

        for (int i = 0; i < length; i++) {
            body.add(new Cell(length * Constants.CELL_SIZE - (i * Constants.CELL_SIZE), Constants.CELL_SIZE));
        }
    }

    public int getLength() {
        return body.size();
    }

    public ArrayList<Cell> getOccupied() {
        return body;
    }

    public Cell move() {
        for (int i = getLength() - 1; i > 0; i--) {
            body.set(i, body.get(i - 1));
        }

        if(direction == Constants.Direction.UP) {
            body.set(0,
                    new Cell(
                    body.get(0).x,
                    body.get(0).y - Constants.CELL_SIZE
                )
            );
        } else if (direction == Constants.Direction.DOWN) {
            body.set(0, new Cell(
                    body.get(0).x,
                    body.get(0).y + Constants.CELL_SIZE
                )
            );
        } else if (direction == Constants.Direction.LEFT) {
            body.set(0, new Cell(
                    body.get(0).x - Constants.CELL_SIZE,
                    body.get(0).y
                )
            );
        } else {
            body.set(0, new Cell(
                    body.get(0).x + Constants.CELL_SIZE,
                    body.get(0).y
                )
            );
        }

        head = body.get(0);

        return head;
    }

    public void change(Constants.Direction newDirection) {
        if((direction == Constants.Direction.LEFT && newDirection == Constants.Direction.RIGHT) ||
                (direction == Constants.Direction.RIGHT && newDirection == Constants.Direction.LEFT) ) {
            return;
        }

        if((direction == Constants.Direction.UP && newDirection == Constants.Direction.DOWN) ||
                (direction == Constants.Direction.DOWN && newDirection == Constants.Direction.UP) ) {
            return;
        }

        direction = newDirection;
    }

    public void grow() {
        int x = body.get(getLength() - 1).x;
        int y = body.get(getLength() - 1).y;

        if(direction == Constants.Direction.UP) {
            y += Constants.CELL_SIZE;
        } else if (direction == Constants.Direction.DOWN) {
            y -= Constants.CELL_SIZE;
        } else if (direction == Constants.Direction.LEFT) {
            x += Constants.CELL_SIZE;
        } else {
            x -= Constants.CELL_SIZE;
        }

        body.add( new Cell(x, y) );
    }

    //reset - want to reset to initial length; else decrease by one
    public void shrink(boolean reset) {
        if(reset) {
            for(int i = getLength() - 1; i > length - 1; i--) {
                body.remove(i);
            }
            return;
        }

        body.remove(getLength() - 1); //support for future enhancements
    }
}
