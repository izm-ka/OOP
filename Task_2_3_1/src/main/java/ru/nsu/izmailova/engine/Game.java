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
