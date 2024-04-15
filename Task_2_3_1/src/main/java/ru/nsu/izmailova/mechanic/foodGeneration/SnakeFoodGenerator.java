package ru.nsu.izmailova.mechanic.foodGeneration;

import ru.nsu.izmailova.mechanic.Point;

import java.util.ArrayList;
import java.util.Random;

/**
 * Simple food generator.
 * Chooses random point and checks if it's occupied
 * Generates food until maxFoodCnt satisfied or generate one food object
 */
public class SnakeFoodGenerator extends FoodGenerator {

    public SnakeFoodGenerator(ArrayList<Point> pointsToAvoid, int maxFoodCnt, int width, int height) {
        super(pointsToAvoid, maxFoodCnt, width, height);
    }

    @Override
    public void generate(ArrayList<Point> food, boolean one) {
        if (!one) {
            food.clear();
        }
        Random random = new Random();
        int j;
        j = one ? 1 : maxFoodCnt;

        for (int i = 0; i < j; i++) {
            Point point = new Point(random.nextInt(width), random.nextInt(height));

            while (pointsToAvoid.contains(point) || ((point.getX() == width / 2) && point.getY() == height / 2)) {
                point.setX(random.nextInt(width));
                point.setY(random.nextInt(height));
            }
            food.add(point);
        }
    }
}
