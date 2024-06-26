package ru.nsu.izmailova.engine;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.util.Duration;

/**
 * The game engine is necessary for the correct call
 * of the update functions of the playground.
 */
public class Engine {
    private final int frameRate;
    private final Game game;
    private final Timeline gameLoop;

    /**
     * Constructor.
     *
     * @param frameRate frame rate of the game (speed)
     * @param game instance
     */
    public Engine(int frameRate, Game game) {
        this.frameRate = frameRate;
        this.game = game;
        game.setEngine(this); //экзмепляр движка для игры
        gameLoop = createLoop();
    }

    /**
     * Handles a run cycle of the game.
     * выполняет один цикл игры, вызывается на кажом кадре цикла
     *
     * @param e the event that triggered this run cycle
     */
    private void run(Event e) {
        game.update();
        game.display();
    }

    /**
     * Starts or restarts the game.
     */
    public void start() {
        gameLoop.playFromStart(); //j,]trn nbgf Timeline (animation)
        game.setPause(false);
    }

    /**
     * Stops the game.
     */
    public void stop() {
        gameLoop.stop();
    }

    /**
     * Pauses the game.
     */
    public void pause() {
        gameLoop.pause();
        game.setPause(true);
        game.display();
    }

    /**
     * Resets the game.
     */
    public void reset() {
        this.stop();
        game.reset();
        this.start();
    }

    /**
     * Creation of the game loop according to specified frame rate.
     *
     * @return gameloop
     */
    private Timeline createLoop() {
        final Duration d = Duration.millis(1000 / frameRate);
        final KeyFrame oneFrame = new KeyFrame(d, this::run);
        Timeline t = new Timeline(frameRate, oneFrame);
        t.setCycleCount(Animation.INDEFINITE);
        return t;
    }
}
