package ru.nsu.izmailova.heapsort;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class heapSortTest {
    @Test
    void basicTest() {
        int[] testArr;
        int[] ansArr;
        testArr = new int[] {5, 4, 3, 2, 1};
        ansArr = new int[] {1, 2, 3, 4, 5};
        heapSort.heapify(testArr);
        assertArrayEquals(testArr, ansArr);
    }

    @Test
    void oneElementTest() {
        int[] testArr;
        int[] ansArr;
        testArr = new int[] {0};
        ansArr = new int[] {0};
        heapSort.heapify(testArr);
        assertArrayEquals(testArr, ansArr);
    }

    @Test
    void emptyTest() {
        int[] testArr;
        int[] ansArr;
        testArr = new int[] {};
        ansArr = new int[] {};
        heapSort.heapify(testArr);
        assertArrayEquals(testArr, ansArr);
    }

    @Test
    void randomTest() {
        int[] randArr = new int[1000];
        int[] ansArr = new int[1000];
        Random r = new Random();
        for (int i = 0; i < randArr.length; i++) {
            randArr[i] = r.nextInt();
            ansArr[i] = randArr[i];
        }
        Arrays.sort(ansArr);
        heapSort.heapify(randArr);
        assertArrayEquals(randArr, ansArr);
    }

    @Test
    void bigTest() {
        int n = 1000000;
        int[] testArr = new int[n];
        Random r = new Random();
        testArr[0] = Integer.MAX_VALUE;
        testArr[1] = Integer.MIN_VALUE;
        int[] ansArr = new int[n];
        ansArr[0] = testArr[0];
        ansArr[1] = testArr[1];
        for (int i = 2; i < n; i++) {
            testArr[i] = r.nextInt();
            ansArr[i] = testArr[i];
        }
        Arrays.sort(ansArr);
        heapSort.heapify(testArr);
        assertArrayEquals(testArr, ansArr);
    }

    @Test
    void negativeTest() {
        int[] testArr;
        int[] ansArr;
        testArr = new int[] {-5, 2, 3, -4, -1, 1, -2, 0, -3};
        ansArr = new int[] {-5, -4, -3, -2, -1, 0, 1, 2, 3};
        heapSort.heapify(testArr);
        assertArrayEquals(testArr, ansArr);
    }
}