package ru.nsu.izmailova;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SampleTest {
    @Test
    void basicTest() {
        int[] testArr;
        int[] ansArr;
        testArr = new int[] {5, 4, 3, 2, 1};
        ansArr = new int[] {1, 2, 3, 4, 5};
        Sample.heapSort(testArr);
        assertArrayEquals(testArr, ansArr);
    }

    @Test
    void oneElementTest() {
        int[] testArr;
        int[] ansArr;
        testArr = new int[] {0};
        ansArr = new int[] {0};
        Sample.heapSort(testArr);
        assertArrayEquals(testArr, ansArr);
    }

    @Test
    void emptyTest() {
        int[] testArr;
        int[] ansArr;
        testArr = new int[] {};
        ansArr = new int[] {};
        Sample.heapSort(testArr);
        assertArrayEquals(testArr, ansArr);
    }

    @Test
    void randomTest() {
        int[] randArr = new int[1000];
        int[] ansArr;
        Random r = new Random();
        for (int i = 0; i < randArr.length; i++) {
            randArr[i] = r.nextInt();
        }
        ansArr = randArr;
        Arrays.sort(ansArr);
        Sample.heapSort(randArr);
        assertArrayEquals(randArr, ansArr);
    }

    @Test
    void bigTest() {
        int n = 1000000;
        int[] testArr = new int[n];
        Random r = new Random();
        testArr[0] = Integer.MAX_VALUE;
        testArr[1] = Integer.MIN_VALUE;
        for (int i = 2; i < n; i++) {
            testArr[i] = r.nextInt();
        }
        int[] ansArr;
        ansArr = testArr;
        Arrays.sort(ansArr);
        Sample.heapSort(testArr);
        assertArrayEquals(testArr, ansArr);
    }

    @Test
    void negativeTest() {
        int[] testArr;
        int[] ansArr;
        testArr = new int[] {-5, 2, 3, -4, -1, 1, -2, 0, -3};
        ansArr = new int[] {-5, -4, -3, -2, -1, 0, 1, 2, 3};
        Sample.heapSort(testArr);
        assertArrayEquals(testArr, ansArr);
    }
}