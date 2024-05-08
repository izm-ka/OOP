package ru.nsu.izmailova.engine;

import javafx.scene.canvas.GraphicsContext;

/**
 * An abstract class of the game.
 */
public abstract class Game {
    GraphicsContext gc;
    Engine engine;
    boolean isPaused;

    double width;
    double height;

    /**
     * Constructor for creating a new game instance.
     * This initializes the attributes for the game's graphical display.
     *
     * @param width the width of the game area in pixels.
     * @param height the height of the game area in pixels.
     * @param gc the GraphicsContext for rendering the game's graphics
     */
    public Game(double width, double height, GraphicsContext gc) {
        this.width = width;
        this.height = height;
        this.gc = gc;
    }

    final void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setPause(boolean pause) {
        this.isPaused = pause;
    }

    public boolean getPause() {
        return isPaused;
    }

    public abstract void update();
    public abstract void display();
    public abstract void reset();
}
