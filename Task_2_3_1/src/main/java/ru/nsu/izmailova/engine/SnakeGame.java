package ru.nsu.izmailova.engine;

import javafx.scene.canvas.GraphicsContext;
import ru.nsu.izmailova.gui.Painter;
import ru.nsu.izmailova.mechanic.Playfield;
import ru.nsu.izmailova.mechanic.snakes.SnakeLobby;

/**
 * Game class for the Snake game.
 */
public class SnakeGame extends Game {
    private static final int BOT_CNT = 3;
    private final Playfield playfield;
    private final Painter painter;
    private boolean keyIsPressed;
    private final SnakeLobby snakeLobby;

    public SnakeGame(double width, double height, GraphicsContext gc, Painter painter) {
        super(width, height, gc);
        this.playfield = new Playfield(width, height, false);
        this.snakeLobby = new SnakeLobby(this.playfield, BOT_CNT, 0);
        this.painter = painter;
        painter.setPlayfield(this.playfield);
        painter.setSnakeLobby(this.snakeLobby);
        reset();
    }

    /**
     * Extended from {@link Game}
     */
    @Override
    public void update() {
        keyIsPressed = false;
        snakeLobby.update();
    }

    /**
     * Extended from {@link Game}
     */
    @Override
    public void display() {
        painter.paint(isPaused);
    }

    /**
     * Get snakeLobby
     * @return snakeLobby
     */
    public SnakeLobby getSnakeLobby() {
        return snakeLobby;
    }

    /**
     * Extended from {@link Game}
     */
    @Override
    public void reset() {
        playfield.reset();
        snakeLobby.reset();
    }

    /**
     * Get keyPress flag
     * @return true - key was already pressed, otherwise - nope
     */
    public boolean isKeyPressed() {
        return keyIsPressed;
    }

    /**
     * Set keyPressed flag to true
     */
    public void setKeyPressed() {
        keyIsPressed = true;
    }
}
