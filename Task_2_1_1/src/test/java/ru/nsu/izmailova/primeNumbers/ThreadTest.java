package ru.nsu.izmailova.primeNumbers;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests that checks the correctness of ThreadSolve class.
 */
public class ThreadTest {
    @Test
    public void primeTest1() throws InterruptedException {
        List<Integer> list = Arrays.asList(1, 2, 3);
        PrimeNumbersThread test = new PrimeNumbersThread();
        boolean act = test.threadSolve(list, 1);
        boolean exp = false;
        Assertions.assertEquals(exp, act);
    }

    @Test
    public void primeTest2() throws InterruptedException {
        List<Integer> list = Arrays.asList(999917, 999931, 999953, 999959,
                999961, 999979, 999983);
        PrimeNumbersThread test = new PrimeNumbersThread();
        boolean act = test.threadSolve(list, 2);
        boolean exp = false;
        Assertions.assertEquals(exp, act);
    }

    @Test
    public void primeTest3() throws InterruptedException {
        List<Integer> list = Arrays.asList(945031, 945037, 945059, 945089, 945103,
                945143, 945151, 945179, 945209, 945211);
        PrimeNumbersThread test = new PrimeNumbersThread();
        boolean act = test.threadSolve(list, 3);
        boolean exp = false;
        Assertions.assertEquals(exp, act);
    }

    @Test
    public void compositeTest1() throws InterruptedException {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        PrimeNumbersThread test = new PrimeNumbersThread();
        boolean act = test.threadSolve(list, 2);
        boolean exp = true;
        Assertions.assertEquals(exp, act);
    }

    @Test
    public void compositeTest2() throws InterruptedException {
        List<Integer> list = Arrays.asList(94530, 945030, 945050, 945210, 945210);
        PrimeNumbersThread test = new PrimeNumbersThread();
        boolean act = test.threadSolve(list, 3);
        boolean exp = true;
        Assertions.assertEquals(exp, act);
    }

    @Test
    public void compositeTest3() throws InterruptedException {
        List<Integer> list = Arrays.asList(945032, 945037, 945059, 945089, 945103,
                945143, 945151, 945179, 945209, 945211);
        PrimeNumbersThread test = new PrimeNumbersThread();
        boolean act = test.threadSolve(list, 3);
        boolean exp = true;
        Assertions.assertEquals(exp, act);
    }
}