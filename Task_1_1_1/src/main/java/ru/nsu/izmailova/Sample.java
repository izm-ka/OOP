package ru.nsu.izmailova;

/**
 * this class contains the heap sort algorithm
 */
public class Sample {
    /**
     * method to swap two elements.
     * @param arr the array that elements are swapping
     * @param i index of the first element
     * @param j index of the second element
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * method to create a max heap
     * @param arr heap
     * @param n the size of the heap
     * @param i index of the root of the subtree
     */
    public static void heap(int[] arr, int n, int i) {
        int parent = i;
        int lChild = 2 * i + 1;
        int rChild = 2 * i + 2;

        if (lChild < n && arr[lChild] > arr[parent]) {
            parent = lChild;
        }

        if (rChild < n && arr[rChild] > arr[parent]) {
            parent = rChild;

        }

        if (parent != i) {
            swap(arr, i, parent);
            heap(arr, n, parent);
        }
    }

    /**
     * main method to sort an array
     * @param arr array to be sorted
     */
    public static void heapSort(int[] arr)
    {
        int n = arr.length;
        int root = 0;

        //create a max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heap(arr, n, i);
        }

        //extract the maximum element and re-heapify
        for (int i = n - 1; i >= 0; i--) {
            swap(arr, i, root);
            heap(arr, i, root);
        }

    }
}