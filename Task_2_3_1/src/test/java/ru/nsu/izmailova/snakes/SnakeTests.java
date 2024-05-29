package ru.nsu.izmailova.snakes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import ru.nsu.izmailova.mechanic.Point;
import ru.nsu.izmailova.mechanic.snakes.Snake;

public class SnakeTests {
    /**
     * Check actual length
     */
    @Test
    @SuppressWarnings("checkstyle:magicnumber")
    public void test1() {

        Snake snake = new Snake(new Point(10, 10));
        snake.setUp();
        for (int i = 0; i < 5; i++) {
            snake.extend();
        }

        assertEquals(snake.length, 6);
    }

    /**
     * Check self-intersection
     */
    @Test
    @SuppressWarnings("checkstyle:magicnumber")
    public void test2() {

        Snake snake = new Snake(new Point(0, 0));
        snake.setUp();

        for (int i = 0; i < 8; i++) {
            snake.extend();
            snake.move(snake.getHead().translate(snake.xVelocity, snake.yVelocity));
        }

        snake.setRight();
        moveTwoForward(snake);

        snake.setDown();
        moveTwoForward(snake);

        snake.setLeft();
        moveTwoForward(snake);

        assertFalse(snake.isSafe());
    }

    private void moveTwoForward(Snake snake) {
        snake.move(snake.getHead().translate(snake.xVelocity, snake.yVelocity));
        snake.move(snake.getHead().translate(snake.xVelocity, snake.yVelocity));
    }
}
