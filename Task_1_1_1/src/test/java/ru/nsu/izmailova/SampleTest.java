package ru.nsu.izmailova;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SampleTest {

    @Test
    void basicTest() {
        int[] testArr;
        int[] ansArr;
        ansArr = new int[] {1, 2, 3, 4, 5};
        testArr = new int[] {5, 4, 3, 2, 1};
        Sample.heapSort(testArr);
        assertArrayEquals(testArr, ansArr);
    }


}