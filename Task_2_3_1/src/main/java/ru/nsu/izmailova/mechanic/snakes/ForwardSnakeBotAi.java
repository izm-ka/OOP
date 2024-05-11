package ru.nsu.izmailova.mechanic.snakes;

import ru.nsu.izmailova.mechanic.Playfield;

/**
 * Forward snake bot AI.
 * Snake always moves forward
 */
public class ForwardSnakeBotAi extends SnakeBotAi {

    public ForwardSnakeBotAi(Snake snake, Playfield playfield) {
        super(snake, playfield);
        snake.setDown();
    }

    @Override
    public void update() {}
}
