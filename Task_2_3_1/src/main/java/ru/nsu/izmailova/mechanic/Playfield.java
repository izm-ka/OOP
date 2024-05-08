package ru.nsu.izmailova.mechanic;

import java.util.ArrayList;
import ru.nsu.izmailova.mechanic.foodGeneration.FoodGenerator;
import ru.nsu.izmailova.mechanic.foodGeneration.SnakeFoodGenerator;
import ru.nsu.izmailova.mechanic.obstacleGeneration.ObstacleGenerator;
import ru.nsu.izmailova.mechanic.obstacleGeneration.SnakeObstacleGenerator;

/**
 * Playfield class.
 */
public class Playfield {

    public static final int SIZE = 20;
    public static final int FOOD_CNT = 5;
    public static final int WALL_LENGTH = 7;
    public static final int WALL_CNT = 12;

    private final int cols;
    private final int rows;

    private final ArrayList<Point> food = new ArrayList<>();
    private final ArrayList<Point> obstacles = new ArrayList<>();

    private final FoodGenerator foodGenerator;
    private final ObstacleGenerator obstacleGenerator;

    /**
     * Constructor.
     *
     * @param width playground width
     * @param height playground height
     * @param test true if test mode activated (0 food and 0 obstacles) - for testing
     */
    public Playfield(final double width, final double height,
                     final boolean test) {
        rows = (int) width / SIZE;
        cols = (int) height / SIZE;

        if (test) {
            obstacleGenerator = new SnakeObstacleGenerator(0, 0, rows, cols);
            foodGenerator = new SnakeFoodGenerator(obstacles, 0, rows, cols);
        } else {
            obstacleGenerator = new SnakeObstacleGenerator(
                    WALL_LENGTH, WALL_CNT, rows, cols);
            foodGenerator = new SnakeFoodGenerator(
                    obstacles, FOOD_CNT, rows, cols);
        }
        reset();
    }

    /**
     * Method to reset and respawn objects.
     */
    public void reset() {
        obstacleGenerator.generate(obstacles);
        foodGenerator.generate(food, false);
    }

    /**
     * Method to remove and add new food.
     *
     * @param point food to remove
     */
    public void removeFood(final Point point) {
        food.remove(point);
        foodGenerator.generate(food, true);
    }

    /**
     * Get columns.
     *
     * @return number of columns
     */
    public int getCols() {
        return cols;
    }

    /**
     * Get rows.
     *
     * @return number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Get width of playground.
     *
     * @return width
     */
    public double getWidth() {
        return rows * SIZE;
    }

    /**
     * Get height of playground.
     *
     * @return height
     */
    public double getHeight() {
        return cols * SIZE;
    }

    /**
     * Get array with food.
     *
     * @return array with food
     */
    public ArrayList<Point> getFood() {
        return food;
    }

    /**
     * Get array with obstacles.
     *
     * @return array with obstacles
     */
    public ArrayList<Point> getObstacles() {
        return obstacles;
    }
}
