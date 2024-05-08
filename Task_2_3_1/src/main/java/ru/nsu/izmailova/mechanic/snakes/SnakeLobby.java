package ru.nsu.izmailova.mechanic.snakes;

import ru.nsu.izmailova.mechanic.Playfield;
import ru.nsu.izmailova.mechanic.Point;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class to perfom all snakes behavior.
 */
public class SnakeLobby {

    private final Playfield playfield;
    private final int botCnt;

    private final ArrayList<Snake> snakes = new ArrayList<>();
    private final ArrayList<SnakeBotAI> snakeAI = new ArrayList<>();

    private final int modeAI;

    private static final int SPAWN_X = 27;
    private static final int SPAWN_Y = 24;
    private static final int SNAKE_BOT_SPAWN_LEN_WITHOUT_HEAD = 5;
    private static final int SNAKE_BUFFER_LEN = 3;

    /**
     * Constructor.
     * @param playfield playfield to get info from
     * @param botCnt required bot number
     */
    public SnakeLobby(Playfield playfield, int botCnt, int modeAI) {
        this.botCnt = botCnt;
        this.playfield = playfield;
        this.modeAI = modeAI;
    }

    /**
     * Method to reset and respawn snakes.
     */
    public void reset() {
        snakes.clear();
        snakeAI.clear();
        snakes.add(new Snake(new Point(playfield.getRows() / 2, playfield.getCols() / 2)));

        if (modeAI == 1) {
            Snake s = new Snake(new Point(SPAWN_X, SPAWN_Y));
            snakes.add(s);
            snakeAI.add(new ForwardSnakeBotAI(s, playfield));
        } else {
            for (int i = 0; i < botCnt; i++) {
                Snake s = new Snake(getRandomPoint());
                s.setDown();

                for (int j = 0; j < SNAKE_BOT_SPAWN_LEN_WITHOUT_HEAD; j++) {
                    s.extend();
                }

                snakes.add(s);
                snakeAI.add(new SimpleSnakeBotAI(s, playfield));
            }
        }
    }

    /**
     * Method that checks snake intersections and performs.
     * according actions based on intersections with different objects
     */
    public void update() {
        Point point;

        for (Snake s: snakes) {

            if (snakes.indexOf(s) != 0) {
                snakeAI.get(snakes.indexOf(s) - 1).update();
            }

            if (playfield.getObstacles().contains(s.getHead())) {
                s.setSafe(false);
            }

            for (Snake ss : snakes) {
                if (snakes.indexOf(s) != snakes.indexOf(ss) && ss.isSafe()) {
                    if (ss.getPoints().contains(s.getHead())) {
                        int index1 = ss.getPoints().indexOf(s.getHead());

                        if (index1 >= ss.length - 2 && snakes.indexOf(ss) != 0) {
                            ss.getPoints().clear();
                            ss.setSafe(false);
                            s.extend();

                            break;
                        }

                        if (snakes.indexOf(ss) == 0) {
                            if (index1 < ss.length - SNAKE_BUFFER_LEN && ss.length > SNAKE_BUFFER_LEN) {
                                ss.deleteTail(index1);
                                s.extend();
                            }
                        } else {
                            ss.deleteTail(index1);
                            s.extend();
                        }
                        break;
                    }
                }
            }
            if ((!s.isSafe() || s.getPoints().size() == 0) && snakes.indexOf(s) != 0) {
                int index = snakes.indexOf(s);
                Snake snake = new Snake(getRandomPoint());
                snake.setDown();

                for (int j = 0; j < SNAKE_BOT_SPAWN_LEN_WITHOUT_HEAD; j++) {
                    snake.extend();
                }

                snakes.set(index, snake);
                if (modeAI == 0) {
                    snakeAI.set(index - 1, new SimpleSnakeBotAI(snake, playfield));
                } else {
                    snakeAI.set(index - 1, new ForwardSnakeBotAI(snake, playfield));
                }
            }

            if (playfield.getFood().contains(s.getHead())) {
                playfield.removeFood(s.getHead());
                s.extend();
            }
        }


        for (Snake s : snakes) {
            if (s.isSafe()) {
                point = wrap(s.getHead().translate(s.xVelocity, s.yVelocity));
                s.move(point);
            }
        }
    }

    /**
     * Wraps point across borders.
     * @param point to check if it wraps
     * @return wrapped point or not
     */
    public Point wrap(Point point) {
        int x = point.getX();
        int y = point.getY();
        if (x >= playfield.getRows()) {
            x = 0;
        }
        if (y >= playfield.getCols()) {
            y = 0;
        }
        if (x < 0) {
            x = playfield.getRows() - 1;
        }
        if (y < 0) {
            y = playfield.getCols() - 1;
        }
        return new Point(x, y);
    }

    /**
     * Method to get free random point on the playfield.
     * @return free point
     */
    public Point getRandomPoint() {
        Random random = new Random();
        Point point = new Point(random.nextInt(playfield.getRows()), random.nextInt(playfield.getCols()));
        do {
            point.setX(random.nextInt(playfield.getRows()));
            point.setY(random.nextInt(playfield.getCols()));
        } while (snakes.stream().anyMatch(s -> s.getPoints().contains(point))
                || playfield.getObstacles().stream().anyMatch(p -> p.equals(point))
                || playfield.getFood().stream().anyMatch(p -> p.equals(point)));
        return point;
    }

    /**
     * Gets user snake.
     * @return user snake
     */
    public Snake getUserSnake() {
        return snakes.get(0);
    }

    /**
     * Gets all snakes.
     * @return array with snakes
     */
    public ArrayList<Snake> getSnakes() {
        return snakes;
    }
}
