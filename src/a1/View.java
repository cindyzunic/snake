package a1;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Handles rendering the view
 */
public class View extends Canvas implements IView {
    private static IController controller;
    private static IBoard board;
    private int fps;

    private KeyboardInput keyInput;

    private JFrame frame;
    private JPanel panel = new JPanel(new GridLayout(3,1));

    private JPanel buttonPanel = new JPanel();
    private JLabel titleText = new JLabel(Constants.GAME_TITLE);
    private JLabel subText = new JLabel(Constants.GAME_AUTHOR);
    private JTextArea instructions = new JTextArea(Constants.GAME_INSTRUCTIONS);
    private JButton startButton = new JButton(Constants.GAME_START_TEXT);


    public View(IController controller, IBoard board) {
        this.controller = controller;
        this.board = board;

        //set up keylistener
        keyInput = new KeyboardInput();
        keyInput.setController(controller);
        addKeyListener(keyInput);

        // set up window
        Dimension gameRes = new Dimension(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);

        frame = new JFrame();

        frame.getContentPane().setLayout(new GridLayout(5,1));
        frame.getContentPane().setBackground(Color.white);
        frame.setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT + 20);
        frame.setResizable(false);
        frame.setTitle(Constants.GAME_TITLE);

        startButton.addActionListener(
                ae -> {
                    frame.remove(panel);
                    frame.remove(buttonPanel);
                    setSize(gameRes);
                    setLocation(0,0);
                    frame.repaint();

                    setFocusable(true);
                    requestFocus();
                    controller.startGame();
                }
        );

        startButton.setSize(88, 31);
        startButton.setHorizontalAlignment(0);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.white);

        titleText.setFont( new Font(titleText.getFont().getFontName(), Font.BOLD, 20));
        panel.add(titleText);
        panel.add(subText);

        instructions.setLineWrap(true);
        instructions.setBackground(Color.white);
        panel.add(instructions);
        frame.add(panel);
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(startButton);
        frame.add(buttonPanel);

        frame.add(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void restart(IBoard board) {
        this.board = board;
        repaint();
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public int getFps() {
        return fps;
    }

    public void pause() {
        BufferStrategy bs = getBufferStrategy();

        if(bs == null) {
            createBufferStrategy(2);
            bs = getBufferStrategy();
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 40));
        g.drawString("PAUSED", Constants.GAME_WIDTH / 2 - 70, Constants.GAME_HEIGHT / 2 - 40);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 10));
        g.drawString("press 'p' to resume play", Constants.GAME_WIDTH / 2 - 50, Constants.GAME_HEIGHT / 2 - 20);

        g.dispose();
        bs.show();
    }

    public void repaint() {
        BufferStrategy bs = getBufferStrategy();

        if(bs == null) {
            createBufferStrategy(2);
            bs = getBufferStrategy();
        }

        Graphics g = bs.getDrawGraphics();
        super.paint(g);

        //paint score and info
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 20));
        g.drawString("SCORE: ", 10, Constants.GAME_HEIGHT - 10);
        g.drawString("FPS: ", Constants.GAME_WIDTH - 100, Constants.GAME_HEIGHT - 30);
        g.drawString("Speed: ", Constants.GAME_WIDTH - 100, Constants.GAME_HEIGHT - 10);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 20));
        g.drawString(Integer.toString(board.getScore()),  100, Constants.GAME_HEIGHT - 10);
        g.drawString(Integer.toString(fps), Constants.GAME_WIDTH - 50, Constants.GAME_HEIGHT - 30);
        g.drawString(Integer.toString(controller.getSpeed()), Constants.GAME_WIDTH - 30, Constants.GAME_HEIGHT - 10);

        //paint food
        g.setColor(Color.RED);
        g.fillRect(board.getFood().x, board.getFood().y, Constants.CELL_SIZE, Constants.CELL_SIZE);

        //paint power ups
        for( Item item : board.getPowerUps() ) {
            g.setColor(item.getColour());
            g.fillRect(item.getCell().x, item.getCell().y, Constants.CELL_SIZE, Constants.CELL_SIZE);
        }

        //paint snake
        g.setColor(Color.BLACK);

        Cell[] snakeBody = board.getOccupied();
        Cell head = snakeBody[0];

        for(int i = 0; i < board.getSnakeLength(); i++) {
            if(i > 1 && snakeBody[i].x == head.x && snakeBody[i].y == head.y) {
                endGame(); //collision with self
                return;
            }

            g.fillRect(snakeBody[i].x, snakeBody[i].y, Constants.CELL_SIZE, Constants.CELL_SIZE);
        }

        g.dispose();
        bs.show();

        if(head.x > Constants.GAME_WIDTH || head.y > Constants.GAME_HEIGHT || head.x < 0 || head.y < 0) {
            endGame();
        }
    }

    private void endGame() {
        BufferStrategy bs = getBufferStrategy();

        if(bs == null) {
            createBufferStrategy(2);
            bs = getBufferStrategy();
        }

        Graphics g = bs.getDrawGraphics();
        super.paint(g);

        g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 20));
        g.drawString(Constants.GAME_END_TEXT, Constants.GAME_WIDTH / 2 - 10, Constants.GAME_HEIGHT / 2 - 25);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 10));
        g.drawString(Constants.GAME_END_SUBTEXT, Constants.GAME_WIDTH / 2 - 25, Constants.GAME_HEIGHT / 2);
        g.dispose();
        bs.show();

        controller.endGame();
    }
}


