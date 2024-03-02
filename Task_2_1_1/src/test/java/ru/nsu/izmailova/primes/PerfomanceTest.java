package ru.nsu.izmailova.primes;

import java.util.ArrayList;
import java.util.List;

/**
 * Complex tests for time measuring.
 */
public class PerfomanceTest {
    private final List<Integer> primeList = initPrimeList();

    /**
     * Initialization of list contains only prime numbers.
     *
     * @return list with prime numbers
     */
    private List<Integer> initPrimeList() {
        List<Integer> result = new ArrayList<>();
        long size = 10000000;
        for (long i = 0; i < size; i++) {
            result.add(847993);
        }
        return result;
    }

    /**
     * Test.
     *
     * @return timeFinish
     */
    public long sequentialTest() {
        PrimeNumbersSequential testStream = new PrimeNumbersSequential();
        long timeStart = System.currentTimeMillis();
        testStream.hasNotPrime(primeList);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("Sequential test: " + timeFinish);
        return timeFinish;
    }

    /**
     * Test.
     *
     * @return timeFinish
     */
    public long parallelStreamTest() {
        PrimeNumbersParallelStream testStream = new PrimeNumbersParallelStream();
        long timeStart = System.currentTimeMillis();
        testStream.hasNotPrime(primeList);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("ParallelStream test: " + timeFinish);
        return timeFinish;
    }

    /**
     * Test.
     *
     * @return timeFinish
     */
    public long threadsTest() throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersThread test = new PrimeNumbersThread(1);
        test.hasNotPrime(primeList);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("1 thread test: " + timeFinish);
        return timeFinish;
    }

    /**
     * Test.
     *
     * @return timeFinish
     */
    public long threadsTest4() throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersThread test = new PrimeNumbersThread(4);
        test.hasNotPrime(primeList);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("4 threads test: " + timeFinish);
        return timeFinish;
    }

    /**
     * Test.
     *
     * @return timeFinish
     */
    public long threadsTest8() throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersThread test = new PrimeNumbersThread(8);
        test.hasNotPrime(primeList);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("8 threads test: " + timeFinish);
        return timeFinish;
    }

    /**
     * Test.
     *
     * @return timeFinish
     */
    public long threadsTest16() throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersThread test = new PrimeNumbersThread(16);
        test.hasNotPrime(primeList);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("16 threads test: " + timeFinish);
        return timeFinish;
    }

    /**
     * Test.
     *
     * @return timeFinish
     */
    public long threadsTest32() throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersThread test = new PrimeNumbersThread(32);
        test.hasNotPrime(primeList);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("32 threads test: " + timeFinish);
        return timeFinish;
    }

    /**
     * Test.
     *
     * @return timeFinish
     */
    public long threadsTest64() throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersThread test = new PrimeNumbersThread(64);
        test.hasNotPrime(primeList);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("64 threads test: " + timeFinish);
        return timeFinish;
    }

    /**
     * Test.
     *
     * @return timeFinish
     */
    public long threadsTest128() throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersThread test = new PrimeNumbersThread(128);
        test.hasNotPrime(primeList);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("128 threads test: " + timeFinish);
        return timeFinish;
    }

    /**
     * Test.
     *
     * @return timeFinish
     */
    public long threadsTest256() throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersThread test = new PrimeNumbersThread(256);
        test.hasNotPrime(primeList);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("256 threads test: " + timeFinish);
        return timeFinish;
    }
}
