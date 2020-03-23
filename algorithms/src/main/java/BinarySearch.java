import java.util.Arrays;

/**
 * With binary search, you guess the middle number and eliminate half the remaining numbers every time
 *
 * @author dzmitry.marudau
 * @since 2020.1
 */
public class BinarySearch {
    /**
     * Search for the integer number in th array.If the
     * item is in the array, the function returns its position.
     *
     * @param sortedArray the sorted array of integers
     * @param item        the integer to search for
     */
    private static Integer binarySearch(int[] sortedArray, int item) {
        int low = 0;
        int high = sortedArray.length - 1;

        while (low <= high) {
            int middle = (low + high) / 2;
            int guess = sortedArray[middle];
            if (guess == item) {
                return middle;
            }
            if (guess > item) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        int[] myList = {1, 3, 6, 9, 15};

        System.out.printf("Searching for %d in %s: result position %s%n", 3, Arrays.toString(myList),
                          binarySearch(myList, 3));
        System.out.printf("Searching for %d in %s: result position %s%n", 5, Arrays.toString(myList),
                          binarySearch(myList, 5));
    }
}
