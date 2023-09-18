package ru.nsu.izmailova;

public class Sample {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

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

    public static void heapSort(int[] arr)
    {
        int n = arr.length;
        int root = 0;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heap(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            swap(arr, i, root);
            heap(arr, i, root);
        }

    }
}