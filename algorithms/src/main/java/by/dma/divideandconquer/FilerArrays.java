package by.dma.divideandconquer;

/**
 * Function implemented according to the divide and conquer paradigm.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class FilerArrays {

    public int filter(int[] array, int left, int right, int value) {
        if (left >= right) {
            return 0;
        }
        if (left == right - 1) {
            if (array[left] == value) {
                return 1;
            } else {
                return 0;
            }
        } else {
            int middle = (left + right) / 2;
            return filter(array, left, middle, value) + filter(array, middle, right, value);
        }
    }
}
