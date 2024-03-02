package ru.nsu.izmailova.primes;

import java.util.List;

/**
 * Abstract class for determining if number is not prime.
 */
public abstract class NotPrime {

    public abstract boolean hasNotPrime(List<Integer> numbers) throws InterruptedException;

    /**
     * Checks whether the number is not prime.
     *
     * @param n the number to check
     * @return true if the number is composite
     */
    public static boolean notPrime(int n) {
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

