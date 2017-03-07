package a1;

/**
 * View interface
 */
public interface IView {
    int getFps();
    void setFps(int fps);
    void repaint();
    void restart(IBoard board);
    void pause();
}
