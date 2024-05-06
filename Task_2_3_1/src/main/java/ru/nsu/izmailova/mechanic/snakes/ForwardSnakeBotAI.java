package ru.nsu.izmailova.mechanic.snakes;

import ru.nsu.izmailova.mechanic.Playfield;

/**
 * Forward snake bot AI.
 * Snake always moves forward
 */
public class ForwardSnakeBotAI extends SnakeBotAI {

    public ForwardSnakeBotAI(Snake snake, Playfield playfield) {
        super(snake, playfield);
        snake.setDown();
    }

    @Override
    public void update() {}
}
