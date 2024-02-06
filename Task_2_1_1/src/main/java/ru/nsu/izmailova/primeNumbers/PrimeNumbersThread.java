package ru.nsu.izmailova.primeNumbers;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PrimeNumbersThread {

    AtomicBoolean compositeNumber = new AtomicBoolean(false);
    public class CurrentThread extends Thread {
        List<Integer> numbers;

        public CurrentThread(List<Integer> numbers) {
            this.numbers = numbers;
        }

        @Override
        public void run() {
            compositeNumber.compareAndSet(false,
                    compositeNumber.get() || numbers.stream().anyMatch(NotPrime::notPrime));
        }
    }

    public boolean threadSolve(List<Integer> numbers, int threadsNum) throws InterruptedException {
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
