package ru.nsu.izmailova.mechanic.snakes;

import ru.nsu.izmailova.mechanic.Point;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    int length;
    private boolean safe;
    private final ArrayList<Point> points;
    Point head;
    int xVelocity;
    int yVelocity;

    /**
     * The constructor of the snake. It takes the initial point, for the head.
     * @param initialPoint - point to put the snake's head on.
     */
    public Snake(Point initialPoint) {
        length = 1;
        points = new ArrayList<>();
        points.add(initialPoint);
        head = initialPoint;
        safe = true;
        xVelocity = 0;
        yVelocity = 0;
    }

    /**
     * This method is called after food has been consumed.
     * It increases the length of the snake by one.
     * @param point - point where the food was and the new location for the head.
     */
    public void growTo(Point point) {
        length++;
        checkAndAdd(point);
    }

    /**
     * Called during every update.
     * It removes the oldest point and adds the given point.
     * @param point - point to add.
     */
    void shiftTo(Point point) {
        checkAndAdd(point);

        points.remove(0);
    }

    /**
     * Checks for an intersection and marks the safe flag accordingly.
     * @param point - point to move.
     */
    private void checkAndAdd(Point point) {
        if (points.contains(point)) {
            safe = false;
        }

        points.add(point);
        head = point;
    }

    /**
     * Get points occupied by the snake.
     * @return points occupied by the snake.
     */
    public List<Point> getPoints() {
        return points;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public Point getHead() {
        return head;
    }

    public boolean isStill() {
        return xVelocity == 0 & yVelocity == 0;
    }

    public void move(Point point) {
        if (!isStill()) {
            shiftTo(point);
        }
    }

    /**
     * Make the snake extend/grow to the square where it's headed.
     */
    public void extend() {
        if (!isStill()) {
            growTo(head.translate(xVelocity, yVelocity));
        }
    }

    /**
     * Set moving direction to up
     */
    public void setUp() {
        if ((yVelocity == 1) && (length > 1)) {
            return;
        }
        xVelocity = 0;
        yVelocity = -1;
    }

    /**
     * Set moving direction to down
     */
    public void setDown() {
        if ((yVelocity == -1) && (length > 1)) {
            return;
        }
        xVelocity = 0;
        yVelocity = 1;
    }

    /**
     * Set moving direction to left
     */
    public void setLeft() {
        if ((xVelocity == 1) && (length > 1)) {
            return;
        }
        xVelocity = -1;
        yVelocity = 0;
    }

    /**
     * Set moving direction to right
     */
    public void setRight() {
        if ((xVelocity == -1) && (length > 1)) {
            return;
        }
        xVelocity = 1;
        yVelocity = 0;
    }

    /**
     * Method to delete blocks from the snake
     * (e.g. if snake is being eaten)
     * @param idx  index of the block to delete from (0 to idx)
     */
    public void deleteTail(int idx) {
        if (idx >= 0) {
            points.subList(0, idx + 1).clear();
            length = points.size();
        }
    }

}
