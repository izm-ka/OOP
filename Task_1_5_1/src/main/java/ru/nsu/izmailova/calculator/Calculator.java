package ru.nsu.izmailova.calculator;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


/**
 * Class to define the logic of calculator.
 */
public class Calculator {
    String expression;
    Deque<Double> result = new LinkedList<>();


    /**
     * Constructor for calculator.
     *
     * @param input expression to calculate
     */
    public Calculator(String input) {
        expression = input;
    }


    /**
     * Method to calculate an expression.
     *
     * @return result of calculation
     */
    public Double calculate() {
        String[] tokens = expression.split(" ");
        for (int i = tokens.length - 1; i >= 0; i--) {
            double a;
            double b;
            switch (tokens[i]) {
                case ("+"):
                    a = result.pop();
                    b = result.pop();
                    result.push(a + b);
                    break;
                case ("-"):
                    a = result.pop();
                    b = result.pop();
                    result.push(a - b);
                    break;
                case ("*"):
                    a = result.pop();
                    b = result.pop();
                    result.push(a * b);
                    break;
                case ("/"):
                    a = result.pop();
                    b = result.pop();
                    checkDivisionByZero(b);
                    result.push(a / b);
                    break;
                case ("pow"):
                    a = result.pop();
                    b = result.pop();
                    result.push(Math.pow(a, b));
                    break;
                case ("log"):
                    a = result.pop();
                    checkLogArgument(a);
                    result.push(Math.log(a));
                    break;
                case ("sqrt"):
                    a = result.pop();
                    checkSqrtArgument(a);
                    result.push(Math.sqrt(a));
                    break;
                case ("sin"):
                    a = result.pop();
                    result.push(Math.sin(a));
                    break;
                case ("cos"):
                    a = result.pop();
                    result.push(Math.cos(a));
                    break;
                default:
                    checkNumericValue(tokens[i]);
                    result.push(Double.parseDouble(tokens[i]));
                    break;
            }
        }
        return result.pop();
    }

    /**
     * Checks for division by zero.
     *
     * @param divisor to check.
     * @throws ArithmeticException if division by zero is detected.
     */
    private void checkDivisionByZero(double divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
    }

    /**
     * Checks the argument of the logarithm for positivity.
     *
     * @param argument to check.
     * @throws IllegalArgumentException if the argument is not positive.
     */
    private void checkLogArgument(double argument) {
        if (argument <= 0) {
            throw new IllegalArgumentException("Argument of logarithm should be positive!");
        }
    }

    /**
     * Checks the argument for square root for negativity.
     *
     * @param argument The argument to check.
     * @throws IllegalArgumentException if the argument is negative.
     */
    private void checkSqrtArgument(double argument) {
        if (argument < 0) {
            throw new IllegalArgumentException("The sqrt of negative number is not defined");
        }
    }

    /**
     * Checks if the value is a valid numeric input.
     *
     * @param value The value to check.
     * @throws IllegalArgumentException if the input is not a valid number.
     */
    private void checkNumericValue(String value) {
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Incorrect input: " + value);
        }
    }
}