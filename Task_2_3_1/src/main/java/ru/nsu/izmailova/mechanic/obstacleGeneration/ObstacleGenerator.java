package ru.nsu.izmailova.mechanic.obstacleGeneration;

import java.util.ArrayList;
import ru.nsu.izmailova.mechanic.Point;

/**
 * Abstract class for custom obstacle generation.
 */
public abstract class ObstacleGenerator {

    int maxWallLength;
    int maxWallCnt;
    int width;
    int height;

    /**
     * Constructor.
     *
     * @param maxWallLength maximum wall length
     * @param maxWallCnt maximum wall number
     * @param width playground width
     * @param height playground height
     */
    ObstacleGenerator(int maxWallLength, int maxWallCnt, int width, int height) {
        this.maxWallLength = maxWallLength;
        this.maxWallCnt = maxWallCnt;
        this.width = width;
        this.height = height;
    }

    /**
     * The method in which obstacle generation should happen,
     * accepts obstacles in obstacle array should be cleared and replaced with new values
     * inside this method.
     *
     * @param obstacles array with obstacles
     */
    public abstract void generate(ArrayList<Point> obstacles);
}
