package a1;

import java.util.ArrayList;
import java.util.Random;
import static java.lang.Math.abs;

/**
 * Board object for tracking global game info such as score, items, etc
 */
public class Board implements IBoard {

    private Random rand;
    private int score;
    private Cell food;
    private ArrayList<Item> powerUps;
    private ISnake snake;

    public Board() {
        powerUps = new ArrayList<Item>();
        food = getRandCell();

        snake = new Snake();
        score = 0;
    }

    public Cell[] getOccupied() {
        return snake.getOccupied().toArray( new Cell[snake.getLength()] );
    }

    public int getSnakeLength() {
        return snake.getLength();
    }

    public void moveSnake() {
        Cell head = snake.move();

        if(head.x == food.x && head.y == food.y) {
            eat();
            return;
        }

        for ( Item item : powerUps ) {
            if(item.getCell().x == head.x && item.getCell().y == head.y) {
                item.eat();
                powerUps.remove(item);
                return;
            }
        }
    }

    public void changeDir(Constants.Direction direction) {
        snake.change(direction);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Cell getFood() {
        return food;
    }

    public ArrayList<Item> getPowerUps() {
        return powerUps;
    }

    private void eat() {
        snake.grow();
        food = getRandCell();
        score += 10;

        if(score % 50 == 0) {
            powerUps.add(
                    new SuperFood(getRandCell(), this)
            );
        }

        if(score % 50 == 0 & score % 75 == 0) {
            powerUps.add(
                    new Potion(getRandCell(), snake)
            );
        }
    }

    private Cell getRandCell () {
        rand = new Random();
        int x = abs( rand.nextInt() ) % Constants.GAME_WIDTH;
        int y = abs( rand.nextInt() ) % Constants.GAME_HEIGHT;

        x = x - (x % Constants.CELL_SIZE);
        x = x < 10 ? 10 : x;
        y = y - (y % Constants.CELL_SIZE);
        y = y < 10 ? 10 : y;

        return new Cell(x, y);
    }
}
