package ru.nsu.izmailova.heapsort;

/** .
 * this class contains the heap sort algorithm
 */
public class Heapsort {
    /** .
     * method to swap two elements.
     *
     * @param arr the array that elements are swapping
     * @param i index of the first element
     * @param j index of the second element
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /** .
     * method to create a max heap
     *
     * @param arr heap
     * @param n the size of the heap
     * @param i index of the root of the subtree
     */
    private static void heap(int[] arr, int n, int i) {
        int parent = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < n && arr[leftChild] > arr[parent]) {
            parent = leftChild;
        }

        if (rightChild < n && arr[rightChild] > arr[parent]) {
            parent = rightChild;

        }

        if (parent != i) {
            swap(arr, i, parent);
            heap(arr, n, parent);
        }
    }

    /** .
     * main method to sort an array
     *
     * @param arr array to be sorted
     */
    public static void heapify(int[] arr) {
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

    public static void main(String[] args) {

    }
}
