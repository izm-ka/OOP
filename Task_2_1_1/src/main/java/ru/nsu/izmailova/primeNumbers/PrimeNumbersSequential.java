package ru.nsu.izmailova.primeNumbers;

import java.util.List;

public class PrimeNumbersSequential {
    public static boolean sequentialSolve(List<Integer> numbers) {
        return numbers.stream().anyMatch(NotPrime::notPrime);
    }
}
