package ru.nsu.izmailova.primes;

/**
 * NotPrime class used for finding composite number.
 */
public class NotPrime {
    /**
     * Function to check whether the number prime or composite.
     *
     * @param n number to check
     * @return true if n is composite, false if n is prime
     */
    public static boolean notPrime(int n) {
        for (int i = 2; i < (int) Math.sqrt(n) + 1; i++) {
            if (n % i == 0) {
                return true;
            }
        }
        return false;
    }
}