package by.dma;

import java.util.Arrays;

/**
 * Quick sort is a fast sorting algorithm. It uses D&C technique.
 * {@impNote Complexity: average O(N log N), the worst O( N × N)}.
 *
 * @author : Dzmitry Marudau
 * @created at : 00:39
 * @since : 2020.03
 **/
public class QuickSort {
    public static void main(String[] args) {
        int[] originalArray = {8, 3, 1, 2, 6, 10};
        QuickSort app = new QuickSort();
        int[] sortedArray = app.sort(originalArray);
        System.out.printf("%s was sorted to: %s%n", Arrays.toString(originalArray),
                          Arrays.toString(sortedArray));
    }

    public int[] sort(int[] array) {
        if (array != null && array.length > 0) {
            quickSort(array, 0, array.length - 1);
        }
        return array;
    }

    private void quickSort(int[] array, int start, int end) {
        // Pick a pivot.
        int partition = partition(array, start, end);

        // Move smaller elements to the left and move bigger elements to the right of the pivot
        // Recursively sort left part and right part
        if (partition - 1 > start) {
            quickSort(array, start, partition - 1);
        }
        if (partition + 1 < end) {
            quickSort(array, partition + 1, end);
        }
    }

    private int partition(int[] array, int from, int to) {
        int pivot = array[to];

        for (int index = from; index < to; index++) {
            if (array[index] < pivot) {
                int temp = array[from];
                array[from] = array[index];
                array[index] = temp;
                from++;
            }
        }

        int temp = array[from];
        array[from] = pivot;
        array[to] = temp;

        return from;
    }

}
