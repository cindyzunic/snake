package a1;

public class Game {

    private static int fps = Constants.DEFAULT_FPS;
    private static int speed = Constants.DEFAULT_SPEED;

    private static IController controller;

    public static void main(String[] args) {

        if(args != null && args.length != 0) {
            //try parse args as ints
            try {
                int fpsArg = Integer.parseInt(args[0], 10);
                int speedArg = Integer.parseInt(args[1], 10);

                //set fps & speed
                if (fps >= Constants.MIN_FPS && fps <= Constants.MAX_FPS) {
                    fps = fpsArg;
                }

                if(speed >= Constants.MIN_SPEED && speed <= Constants.MAX_SPEED) {
                    speed = speedArg;
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
                fps = Constants.DEFAULT_FPS;
                speed = Constants.DEFAULT_SPEED;
            }
        }

        Game game = new Game();
        controller.setGame(game);
    }

    public Game() {
        IBoard board = new Board();

        controller = new Controller(board);

        IView view = new View(controller, board);
        controller.setView(view);

        controller.setDefaults(fps, speed);
    }
}
