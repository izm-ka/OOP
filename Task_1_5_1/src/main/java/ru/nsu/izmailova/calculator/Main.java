package ru.nsu.izmailova.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Main function for input an expression.
 */
public class Main {

    /**
     * Main static method to start calculator.
     */
    public static void main(String[] args) {
        System.out.println("================== CALCULATOR ==================");
        System.out.println("Use q to exit.");

        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(System.in))) {

            while (true) {
                System.out.print("Enter your expression: ");
                String userInput = input.readLine().trim();
                if (userInput.equals("q")) {
                    break;
                }

                if (!userInput.isEmpty()) {
                    try {
                        var calc = new Calculator(userInput);
                        double result = calc.calculate();
                        System.out.println("Result: " + result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Empty expression");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}