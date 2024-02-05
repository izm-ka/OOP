package ru.nsu.izmailova.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for calculator.
 */
public class CalculatorTest {
    @Test
    public void additionTest() throws Exception {
        Calculator exp = new Calculator("+ 1 2");
        Assertions.assertEquals(3, exp.calculate());
    }

    @Test
    public void subtractionTest() throws Exception {
        Calculator exp = new Calculator("- 1 2");
        Assertions.assertEquals(-1, exp.calculate());
    }

    @Test
    public void multiplyTest() throws Exception {
        Calculator exp = new Calculator("* 4 2");
        Assertions.assertEquals(8, exp.calculate());
    }

    @Test
    public void divisionTest() throws Exception {
        Calculator exp = new Calculator("/ 2 0");
        Exception thrown = Assertions.assertThrows(Exception.class, exp::calculate);
        Assertions.assertEquals("Division by zero is not allowed", thrown.getMessage());

        exp = new Calculator("/ 2 2");
        Assertions.assertEquals(1, exp.calculate());
    }

    @Test
    public void powTest() throws Exception {
        Calculator exp = new Calculator("pow 2 3");
        Assertions.assertEquals(8, exp.calculate());
    }

    @Test
    public void sqrtTest() throws Exception {
        Calculator exp = new Calculator("sqrt -1");
        Exception thrown = Assertions.assertThrows(Exception.class, exp::calculate);
        Assertions.assertEquals("The sqrt of negative number is not defined", thrown.getMessage());

        exp = new Calculator("sqrt 4");
        Assertions.assertEquals(2, exp.calculate());
    }

    @Test
    public void logTest() throws Exception {
        Calculator exp = new Calculator("log 0");
        Exception thrown = Assertions.assertThrows(Exception.class, exp::calculate);
        Assertions.assertEquals("Argument of logarithm should be positive!", thrown.getMessage());

        exp = new Calculator("log 1");
        Assertions.assertEquals(0, exp.calculate());
    }

    @Test
    public void sinTest() throws Exception {
        Calculator exp = new Calculator("sin 0");
        Assertions.assertEquals(0, exp.calculate());
    }

    @Test
    public void cosTest() throws Exception {
        Calculator exp = new Calculator("cos 0");
        Assertions.assertEquals(1, exp.calculate());
    }

    @Test
    public void incorrectInputTest() throws Exception {
        Calculator exp = new Calculator("asddedd");
        Exception thrown = Assertions.assertThrows(Exception.class, exp::calculate);
        Assertions.assertEquals("Incorrect input: asddedd", thrown.getMessage());
    }

    @Test
    public void complexTest() throws Exception {
        Calculator exp = new Calculator("sin cos + + sqrt 2 2 - pow 6 4 log 5");
        Assertions.assertEquals(-0.8116709407298298, exp.calculate());
    }

    @Test
    public void orderOperationsTest() throws Exception {
        Calculator calc = new Calculator("+ 2 * 3 4");
        Assertions.assertEquals(14.0, calc.calculate());

        calc = new Calculator("- 10 / 20 4");
        Assertions.assertEquals(5.0, calc.calculate());
    }
}