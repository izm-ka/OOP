package ru.nsu.izmailova.mechanic.snakes;

import ru.nsu.izmailova.mechanic.Playfield;
import ru.nsu.izmailova.mechanic.Point;

import java.util.Random;

/**
 * Simple snake bot AI.
 * Checks if point in front of the controlled snake contains an obstacle,
 * If contains, changes rotation based on current one.
 * Also, change direction randomly every action = 10 frames.
 */
public class SimpleSnakeBotAI extends SnakeBotAI{
    private final Random random;
    private final int action = 10;
    private int curAction = action;
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    private static final int ROT_SIDES = 4;

    public SimpleSnakeBotAI(Snake snake, Playfield playfield) {
        super(snake, playfield);
        random = new Random();
    }

    @Override
    public void update() {
        checkEnv();
        if (curAction == action) {
            curAction = 0;
            int rot = random.nextInt(ROT_SIDES);
            switch (rot) {
                default:
                    break;
                case UP:
                    snake.setUp();
                    break;
                case DOWN:
                    snake.setDown();
                    break;
                case LEFT:
                    snake.setLeft();
                    break;
                case RIGHT:
                    snake.setRight();
                    break;
            }
        }
        curAction++;
    }

    /**
     * Checks one point forward and change rotation.
     * If it contains an obstacle
     */
    private void checkEnv() {
        Point env = snake.getHead().translate(snake.xVelocity, snake.yVelocity);

        if (checkObstacle(env)) {
            curAction = 0;
            int rot = random.nextInt(2);

            if (snake.yVelocity == -1 || snake.yVelocity == 1) {
                switch (rot) {
                    default:
                        break;
                    case 0:
                        snake.setLeft();
                        break;
                    case 1:
                        snake.setRight();
                        break;
                }
            } else if (snake.xVelocity == 1 || snake.xVelocity == -1) {
                switch (rot) {
                    default:
                        break;
                    case 0:
                        snake.setUp();
                        break;
                    case 1:
                        snake.setDown();
                        break;
                }
            }
        }
    }

    /**
     * Check if point occupied by obstacles
     * @param point point to check
     * @return true if occupied, otherwise - nope
     */
    private boolean checkObstacle(Point point) {
        return playfield.getObstacles().contains(point);
    }
}
