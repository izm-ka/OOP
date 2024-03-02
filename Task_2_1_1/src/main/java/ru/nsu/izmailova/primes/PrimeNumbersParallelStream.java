package ru.nsu.izmailova.primes;

import java.util.List;

/**
 * Parallel streaming class used for finding composite number in the list of numbers.
 */
public class PrimeNumbersParallelStream extends NotPrime {
    /**
     * Function to find if there is a composite numbers in the list.
     *
     * @param numbers list of numbers to check
     * @return true if there is composite number, and false otherwise
     */

    @Override
    public boolean hasNotPrime(List<Integer> numbers) {
        return numbers.parallelStream().anyMatch(NotPrime::notPrime);
    }

}
