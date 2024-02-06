package ru.nsu.izmailova.primes;

import java.util.Arrays;

/**
 * Test for measuring average time of executing big tests.
 */
public class RunPerfomance {
    /**
     * Main method.
     */
    public static void main(String[] args) throws InterruptedException {
        PerfomanceTest test = new PerfomanceTest();
        Long[][] data = new Long[10][5];
        for (int i = 0; i < 5; i++) {
            System.out.println("Test repeat № " + (i + 1) + " - started");
            data[0][i] = test.sequentialTest();
            data[1][i] = test.parallelStreamTest();
            data[2][i] = test.threadsTest();
            data[3][i] = test.threadsTest4();
            data[4][i] = test.threadsTest8();
            data[5][i] = test.threadsTest16();
            data[6][i] = test.threadsTest32();
            data[7][i] = test.threadsTest64();
            data[8][i] = test.threadsTest128();
            data[9][i] = test.threadsTest256();
            System.out.println("Test repeat № " + (i + 1) + " - finished");
        }
        System.out.println("============Tests "
                + "finished===========");
        System.out.println("Sequential average: "
                + Arrays.stream(data[0])
                .mapToLong(Long::longValue)
                .average());
        System.out.println("ParallelStream average: "
                + Arrays.stream(data[1])
                .mapToLong(Long::longValue)
                .average());
        System.out.println("1 thread average: "
                + Arrays.stream(data[2])
                .mapToLong(Long::longValue)
                .average());
        System.out.println("4 thread average: "
                + Arrays.stream(data[3])
                .mapToLong(Long::longValue)
                .average());
        System.out.println("8 thread average: "
                + Arrays.stream(data[4])
                .mapToLong(Long::longValue)
                .average());
        System.out.println("16 thread average: "
                + Arrays.stream(data[5])
                .mapToLong(Long::longValue)
                .average());
        System.out.println("32 thread average: "
                + Arrays.stream(data[6])
                .mapToLong(Long::longValue)
                .average());
        System.out.println("64 thread average: "
                + Arrays.stream(data[7])
                .mapToLong(Long::longValue)
                .average());
        System.out.println("128 thread average: "
                + Arrays.stream(data[8])
                .mapToLong(Long::longValue)
                .average());
        System.out.println("256 thread average: "
                + Arrays.stream(data[9])
                .mapToLong(Long::longValue)
                .average());
    }
}