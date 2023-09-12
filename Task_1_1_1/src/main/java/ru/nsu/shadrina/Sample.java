package ru.nsu.izmailova.heapsort;


public class Sample {

    public void swap(int[] arr, int i, int j) {
        arr[i] = (arr[i] + arr[j]) - (arr[j] = arr[i]);
    }

    public void heap(int[] arr, int n, int i) {
        int parent = i;
        int l_child = 2*i + 1;
        int r_child = 2*i + 2;

        if (l_child < n && arr[l_child] > arr[parent])
            parent = l_child;

        if (r_child < n && arr[r_child] > arr[parent])
            parent = r_child;

        if (parent != i) {
            swap(arr, i, parent);
            heap(arr, n, i);
        }
    }

    public void HeapSort(int arr[])
    {
        int n = arr.length;
        int root = 0;

        for (int i = n/2 - 1; i >= 0; i--)
            heap(arr,n,i);

        for (int i = n - 1; i >= 0; i--) {
            swap(arr, i, root);

            heap(arr, i, root);
        }

    }
}