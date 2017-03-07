package a1;

import java.awt.*;

/**
 * SuperFood Item
 */
public class SuperFood implements Item {
    private Cell cell;
    final private Color colour = Color.CYAN;
    private IBoard board;

    public Constants.ItemType getType() {
        return Constants.ItemType.SUPERFOOD;
    }

    public SuperFood(Cell cell, IBoard board) {
        this.cell = cell;
        this.board = board;
    }

    public Color getColour() {
        return colour;
    }
    public Cell getCell() {
        return cell;
    }

    public void eat() {
        board.setScore(board.getScore() + 50);
    }
}
