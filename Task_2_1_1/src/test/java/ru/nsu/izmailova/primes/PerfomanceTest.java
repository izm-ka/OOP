package ru.nsu.izmailova.primes;

import java.util.List;
import java.util.ArrayList;

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

    public long sequentialTest() {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersSequential.sequentialSolve(primeList);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("Sequential test: " + timeFinish);
        return timeFinish;
    }

    public long parallelStreamTest() {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersParallelStream.parallelStreamSolve(primeList);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("ParallelStream test: " + timeFinish);
        return timeFinish;
    }

    public long threadsTest() throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersThread test = new PrimeNumbersThread();
        test.threadSolve(primeList, 1);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("1 thread test: " + timeFinish);
        return timeFinish;
    }

    public long threadsTest4() throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersThread test = new PrimeNumbersThread();
        test.threadSolve(primeList, 4);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("4 threads test: " + timeFinish);
        return timeFinish;
    }

    public long threadsTest8() throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersThread test = new PrimeNumbersThread();
        test.threadSolve(primeList, 8);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("8 threads test: " + timeFinish);
        return timeFinish;
    }

    public long threadsTest16() throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersThread test = new PrimeNumbersThread();
        test.threadSolve(primeList, 16);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("16 threads test: " + timeFinish);
        return timeFinish;
    }

    public long threadsTest32() throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersThread test = new PrimeNumbersThread();
        test.threadSolve(primeList, 32);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("32 threads test: " + timeFinish);
        return timeFinish;
    }

    public long threadsTest64() throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersThread test = new PrimeNumbersThread();
        test.threadSolve(primeList, 64);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("64 threads test: " + timeFinish);
        return timeFinish;
    }

    public long threadsTest128() throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersThread test = new PrimeNumbersThread();
        test.threadSolve(primeList, 128);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("128 threads test: " + timeFinish);
        return timeFinish;
    }

    public long threadsTest256() throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        PrimeNumbersThread test = new PrimeNumbersThread();
        test.threadSolve(primeList, 256);
        long timeFinish = System.currentTimeMillis() - timeStart;
        System.out.println("256 threads test: " + timeFinish);
        return timeFinish;
    }
}
