package a1;

/**
 * Constants shared by game
 */
public class Constants {
    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    enum ItemType {
        FOOD,
        SUPERFOOD,
        POTION
    }

    static int GAME_WIDTH = 800;
    static int GAME_HEIGHT = 600;
    static int DEFAULT_FPS = 30;
    static int MIN_FPS = 10;
    static int MAX_FPS = 100;
    static int MIN_SPEED = 1;
    static int MAX_SPEED = 10;
    static int DEFAULT_SPEED = 4;

    static int CELL_SIZE = 10;

    static final String GAME_TITLE = "SNAKE";
    static final String GAME_AUTHOR = "Xinxin Xie - x22xie";
    static String GAME_INSTRUCTIONS = "Move the snake using arrow keys or the a, w, d, s keys. " +
            "Pressing 'p' will pause/resume the game. Pressing 'r' restarts the game. " +
            "Eat the fruit to gain points. Hit the walls or run into yourself and die. Don't die.";
    static final String GAME_START_TEXT = "Start";
    static final String GAME_END_TEXT = "DED.";
    static final String GAME_END_SUBTEXT = "Press 'r' to restart.";
}
