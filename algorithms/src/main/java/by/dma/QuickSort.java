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

    private int[] sort(int[] array) {
        //TODO
        int[] copyArray = Arrays.copyOf(array, array.length);
        //1. Pick a pivot.
        //2. Partition the array into two sub-arrays: elements less than the pivot
        //and elements greater than the pivot.
        //3. Call quicksort recursively on the two sub-arrays.

        return copyArray;
    }

}
