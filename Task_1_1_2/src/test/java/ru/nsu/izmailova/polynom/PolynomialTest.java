package ru.nsu.izmailova.polynom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test class for Polynomial.
 */
public class PolynomialTest {

    @Test
    public void voidEvaluate() {
        Polynomial p = new Polynomial(new int[]{1, 2, 3});
        assertEquals(6, p.evaluate(1));
    }
    
    @Test
    public void voidGetCoefficients() {
        int[] coefficients = {1, 2, 3};
        Polynomial p = new Polynomial(coefficients);
        assertEquals(coefficients, p.getCoefficients());
    }
    
    @Test
    void simpleSum() {
        Polynomial p1 = new Polynomial(new int[]{1, 2, 3});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 1});
        assertEquals(p1.add(p2), new Polynomial(new int[]{4, 4, 4}));
    }

    @Test
    void diffLengthSum() {
        Polynomial p1 = new Polynomial(new int[]{1, 2});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 1});
        assertEquals(p1.add(p2), new Polynomial(new int[]{4, 4, 1}));
    }

    @Test
    void emptyCoeffsSum() {
        Polynomial p1 = new Polynomial(new int[]{});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 1});
        assertEquals(p1.add(p2), p2);
    }

    @Test
    void lowerZeroSum() {
        Polynomial p1 = new Polynomial(new int[]{0, 0, 0, 1});
        Polynomial p2 = new Polynomial(new int[]{3, -2, -1});
        assertEquals(p1.add(p2), new Polynomial(new int[]{3, -2, -1, 1}));
    }

    @Test
    void simpleSub() {
        Polynomial p1 = new Polynomial(new int[]{1, 2, 3});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 1});
        assertEquals(p1.subtract(p2), new Polynomial(new int[]{-2, 0, 2}));
    }

    @Test
    void lowerZeroSub() {
        Polynomial p1 = new Polynomial(new int[]{0, 0, 0, 1});
        Polynomial p2 = new Polynomial(new int[]{3, -2, -1});
        assertEquals(p1.subtract(p2), new Polynomial(new int[]{-3, 2, 1, 1}));
    }

    @Test
    void simpleMlt() {
        Polynomial p1 = new Polynomial(new int[]{0, 0, 0, 5});
        Polynomial p2 = new Polynomial(new int[]{1, 2, 3});
        assertEquals(p1.multiply(p2), new Polynomial(new int[]{0, 0, 0, 5, 10, 15})
        );
    }

    @Test
    void negativeMlt() {
        Polynomial p1 = new Polynomial(new int[]{-2});
        Polynomial p2 = new Polynomial(new int[]{1, 2});
        assertEquals(p1.multiply(p2), new Polynomial(new int[]{-2, -4})
        );
    }

    @Test
    void simpleDiff() {
        Polynomial p1 = new Polynomial(new int[]{1, 2, 3});
        assertEquals(p1.differentiate(1), new Polynomial(new int[]{2, 2})
        );
    }

    @Test
    void highPowerDiff() {
        Polynomial p1 = new Polynomial(new int[]{7, 6, 5, 4, 3, 2, 1});
        assertEquals(p1.differentiate(5), new Polynomial(new int[]{5040, 720})
        );
    }

    @Test
    void powerZeroDiff() {
        Polynomial p1 = new Polynomial(new int[]{7, 6, 5, 4, 3, 2, 1});
        assertEquals(p1.differentiate(100), new Polynomial(new int[]{0})
        );
    }

    @Test
    void negativeDiff() {
        Polynomial p1 = new Polynomial(new int[]{2, 1});
        assertEquals(p1.differentiate(1), new Polynomial(new int[]{2}));
    }

    @Test
    void simpleEquals() {
        Polynomial p1 = new Polynomial(new int[]{1, 2, 3});
        Polynomial p2 = new Polynomial(new int[]{1, 2, 3});
        assertTrue(p1.equals(p2));
    }

    @Test
    void falseEquals() {
        Polynomial p1 = new Polynomial(new int[]{1, 2, 3});
        Polynomial p2 = new Polynomial(new int[]{-1, -2, -3});
        assertFalse(p1.equals(p2));
    }

    @Test
    void emptyEquals() {
        Polynomial p1 = new Polynomial(new int[]{1, 2, 3});
        Polynomial p2 = new Polynomial(new int[]{});
        assertFalse(p1.equals(p2));
    }

    @Test
    void voidToString() {
        Polynomial p = new Polynomial(new int[]{1, 2, 3});
        assertEquals("1x^2 + 2x + 3", p.toString());
    }
}

