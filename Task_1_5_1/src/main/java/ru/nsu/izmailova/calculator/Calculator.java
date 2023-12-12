package ru.nsu.izmailova.calculator;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Class to define a logic of calculator.
 */
public class Calculator {
    List<String> expression;
    Deque<Double> result = new LinkedList<>();


    /**
     * Constructor for calculator.
     *
     * @param input expression to calculate
     */
    public Calculator(List<String> input) {
        expression = input;
    }


    /**
     * Method to calculate an expression.
     *
     * @return result of calculation
     */
    public Double calculate() {

        for (int i = expression.size() - 1; i >= 0; i--) {

            double a;
            double b;
            switch (expression.get(i)) {
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
                    checkNumericValue(expression.get(i));
                    result.push(Double.parseDouble(expression.get(i)));
                    break;
            }
        }
        return result.pop();
    }
    private void checkDivisionByZero(double divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
    }

    private void checkLogArgument(double argument) {
        if (argument <= 0) {
            throw new IllegalArgumentException("Argument of logarithm should be positive!");
        }
    }

    private void checkSqrtArgument(double argument) {
        if (argument < 0) {
            throw new IllegalArgumentException("The sqrt of negative number is not defined");
        }
    }

    private void checkNumericValue(String value) {
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Incorrect input: " + value);
        }
    }
}