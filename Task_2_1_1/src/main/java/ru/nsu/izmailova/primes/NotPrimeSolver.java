package ru.nsu.izmailova.primes;

/**
 * Class for solving whether a number is prime or not.
 */
public class NotPrimeSolver extends NotPrime {
    @Override
    public boolean isNotPrime(int n) {
        if (n == 1) {
            return true;
        }
        for (int i = 2; i < (int) Math.sqrt(n) + 1; i++) {
            if (n % i == 0) {
                return true;
            }
        }
        return false;
    }
}