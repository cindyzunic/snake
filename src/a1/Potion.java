package a1;

import java.awt.*;

/**
 * Potion item
 */
public class Potion implements Item {

    private Cell cell;
    private ISnake snake;
    final private Color colour = new Color(111, 67, 186);

    public Potion(Cell cell, ISnake snake) {
        this.cell = cell;
        this.snake = snake;
    }

    public Cell getCell() {
        return cell;
    }

    public Color getColour() {
        return colour;
    }

    public void eat() {
        snake.shrink(true);
    }

    public Constants.ItemType getType() {
        return Constants.ItemType.POTION;
    }
}
