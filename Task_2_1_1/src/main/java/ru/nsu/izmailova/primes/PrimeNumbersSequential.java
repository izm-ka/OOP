package ru.nsu.izmailova.primes;

import java.util.List;

/**
 * Sequential class used for finding composite number in the list of numbers.
 */
public class PrimeNumbersSequential extends NotPrime {
    /**
     * Function to find if there is a composite numbers in the list.
     *
     * @param numbers list of numbers to check
     * @return true if there is composite number, and false otherwise
     */
    @Override
    public boolean hasNotPrime(List<Integer> numbers) {
        return numbers.stream().anyMatch(NotPrime::notPrime);
    }
}
