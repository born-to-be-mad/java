package by.dma;

import java.util.Arrays;

/**
 * Selection sort is a neat algorithm , but it is not very fast.
 * * {@impNote Complexity: O( N × N)}.
 *
 * @author dzmitry.marudau
 * @since 2020.1
 */
public class SelectionSort {

    public int[] sort(int[] array) {
        int[] copyArray = Arrays.copyOf(array, array.length);
        int[] newArray = new int[copyArray.length];
        for (int index = 0; index < newArray.length; index++) {
            int smallestIndex = findSmallest(copyArray);
            newArray[index] = copyArray[smallestIndex];
            copyArray[smallestIndex] = Integer.MAX_VALUE;
        }
        return newArray;
    }

    private int findSmallest(int[] array) {
        int smallestNumber = array[0];
        int smallestIndex = 0;
        for (int index = 0; index < array.length; index++) {
            if (array[index] < smallestNumber) {
                smallestNumber = array[index];
                smallestIndex = index;
            }
        }
        return smallestIndex;
    }

    public static void main(String[] args) {
        int[] originalArray = {8, 3, 1, 2, 6, 10};
        int[] sortedArray = new SelectionSort().sort(originalArray);
        System.out.printf("%s was sorted to: %s%n", Arrays.toString(originalArray),
                          Arrays.toString(sortedArray));
    }

}
