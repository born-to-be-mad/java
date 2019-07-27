package by.dma.arrays;

import java.util.Comparator;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:54
 * @since : 2019.07
 **/
public class ArrayUtils {

    private ArrayUtils() {
    }

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1])
                return false;
        }
        return true;
    }

    public static boolean isSorted(int[] array, int length) {
        if (array == null || length < 2)
            return true;
        if (array[length - 2] > array[length - 1])
            return false;
        return isSorted(array, length - 1);
    }

    public static boolean isSorted(Comparable[] array) {
        for (int i = 0; i < array.length - 1; ++i) {
            if (array[i].compareTo(array[i + 1]) > 0)
                return false;
        }
        return true;
    }

    public static boolean isSorted(Comparable[] array, int length) {
        if (array == null || length < 2)
            return true;
        if (array[length - 2].compareTo(array[length - 1]) > 0)
            return false;
        return isSorted(array, length - 1);
    }

    public static boolean isSorted(Object[] array, Comparator comparator) {
        for (int i = 0; i < array.length - 1; ++i) {
            if (comparator.compare(array[i], (array[i + 1])) > 0)
                return false;
        }
        return true;
    }

    public static boolean isSorted(Object[] array, Comparator comparator, int length) {
        if (array == null || length < 2)
            return true;
        if (comparator.compare(array[length - 2], array[length - 1]) > 0)
            return false;
        return isSorted(array, comparator, length - 1);
    }
}
