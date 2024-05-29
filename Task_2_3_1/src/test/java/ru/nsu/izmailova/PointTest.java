package ru.nsu.izmailova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.izmailova.mechanic.Point;


public class PointTest {

    /**
     * Check translate method
     */
    @Test
    @SuppressWarnings("checkstyle:magicnumber")
    public void test1() {

        Point point = new Point(25, 7);
        Point testPoint = point.translate(10, 23);

        assertEquals(testPoint, new Point(35, 30));
    }

    /**
     * Check equals method
     */
    @Test
    public void test2() {

        Point point1 = new Point(1, 2);
        Point point2 = new Point(2, 1);

        assertNotEquals(point1, point2);
    }

}
