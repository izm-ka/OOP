package ru.nsu.izmailova.mechanic;

/**
 * Point class.
 */
public final class Point {
    private static final int PRIME_NUM = 31;
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int xCord) {
        this.x = xCord;
    }

    public void setY(int yCord) {
        this.y = yCord;
    }

    public Point translate(int dx, int dy) {
        return new Point(x + dx, y + dy);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Point)) {
            return false;
        }
        Point point = (Point) other;
        return x == point.x & y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = PRIME_NUM * result + y;
        return result;
    }

    public String toString() {
        return x + ", " + y;
    }

}
