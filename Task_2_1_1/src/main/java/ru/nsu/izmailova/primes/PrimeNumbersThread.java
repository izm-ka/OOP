package ru.nsu.izmailova.primes;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Multithreading class used for finding composite number in the list of numbers.
 */
class PrimeNumbersThread extends NotPrime {

    AtomicBoolean compositeNumber = new AtomicBoolean(false);
    private final int threadsNum;

    public PrimeNumbersThread(int threadsNum) {
        this.threadsNum = threadsNum;
    }

    /**
     * Class for creating threads.
     */
    final class CurrentThread extends Thread {
        List<Integer> numbers;
        /**
         * CurrentThread constructor.
         *
         * @param numbers    list of numbers
         */
        CurrentThread(List<Integer> numbers) {
            this.numbers = numbers;
        }

        /**
         * Override run method for thread.
         */
        @Override
        public void run() {
            compositeNumber.compareAndSet(false,
                    compositeNumber.get() || numbers.stream().anyMatch(NotPrime::notPrime));
        }

    }

    /**
     * Function to find if there is a composite numbers in the list.
     *
     * @param numbers    list of numbers to check
     * @return true if there is composite number, and false otherwise
     * @throws InterruptedException when threads conflict
     */
    @Override
    public boolean hasNotPrime(List<Integer> numbers) throws InterruptedException{
        int threadsNum = this.threadsNum;
        int len = numbers.size();
        int part;
        if (numbers.size() >= threadsNum) {
            part = len / threadsNum;
        } else {
            part = 1;
            threadsNum = len;
        }
        CurrentThread[] threads = new CurrentThread[threadsNum];
        for (int i = 0; i < threadsNum; i++) {
            int fromIndex = part * i;
            int toIndex = part * (i + 1);
            if (i == threadsNum - 1) {
                toIndex = len;
            }
            threads[i] = new CurrentThread(numbers.subList(fromIndex, toIndex));
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        return compositeNumber.get();
    }
}