package ru.nsu.izmailova.mechanic.snakes;

import ru.nsu.izmailova.mechanic.Playfield;

/**
 * Abstract class for custom snake bots.
 */
public abstract class SnakeBotAi {

    Snake snake;
    Playfield playfield;

    /**
     * Constructor.
     *
     * @param snake to control
     * @param playfield to get info from
     */
    public SnakeBotAi(Snake snake, Playfield playfield) {
        this.snake = snake;
        this.playfield = playfield;
    }

    /**
     * The method in which snake controlling should happen (rotations, etc.),
     * it should provide acceptable snake behaviour
     */
    public abstract void update();
}
