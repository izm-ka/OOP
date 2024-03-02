package ru.nsu.izmailova.primes;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests that checks the correctness of PrimeNumbersSequential class.
 */
public class SequentialTest {
    @Test
    public void primeTest1() {
        PrimeNumbersSequential testStream = new PrimeNumbersSequential();
        List<Integer> list = Arrays.asList(6, 8, 7, 13, 5, 9, 4);
        boolean act = testStream.hasNotPrime(list);
        boolean exp = true;
        Assertions.assertEquals(exp, act);
    }

    @Test
    public void primeTest2() {
        PrimeNumbersSequential testStream = new PrimeNumbersSequential();
        List<Integer> list = Arrays.asList(20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053);
        boolean act = testStream.hasNotPrime(list);
        boolean exp = false;
        Assertions.assertEquals(exp, act);
    }

    @Test
    public void primeTest3() {
        PrimeNumbersSequential testStream = new PrimeNumbersSequential();
        List<Integer> list = Arrays.asList(945031, 945037, 945059, 945089, 945103,
                945143, 945151, 945179, 945209, 945211);
        boolean act = testStream.hasNotPrime(list);
        boolean exp = false;
        Assertions.assertEquals(exp, act);
    }

    @Test
    public void compositeTest1() {
        PrimeNumbersSequential testStream = new PrimeNumbersSequential();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        boolean act = testStream.hasNotPrime(list);
        boolean exp = true;
        Assertions.assertEquals(exp, act);
    }

    @Test
    public void compositeTest2() {
        PrimeNumbersSequential testStream = new PrimeNumbersSequential();
        List<Integer> list = Arrays.asList(94530, 945030, 945050, 945210, 945210);
        boolean act = testStream.hasNotPrime(list);
        boolean exp = true;
        Assertions.assertEquals(exp, act);
    }

    @Test
    public void compositeTest3() {
        PrimeNumbersSequential testStream = new PrimeNumbersSequential();
        List<Integer> list = Arrays.asList(945032, 945037, 945059, 945089, 945103,
                945143, 945151, 945179, 945209, 945211);
        boolean act = testStream.hasNotPrime(list);
        boolean exp = true;
        Assertions.assertEquals(exp, act);
    }
}