package ru.nsu.izmailova.primes;

/**
 * Abstract class for determining if a number is not prime.
 */
public abstract class NotPrime {

    public abstract boolean isNotPrime(int n);

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
