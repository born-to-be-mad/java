package by.dma;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link QuickSort}.
 *
 * @author dzmitry.marudau
 * @since 2020.2
 */
class QuickSortTest {

    @Test
    void shouldNotFailIfArrayIsEmpty() {
        QuickSort sorter = new QuickSort();
        Assertions.assertArrayEquals(new int[] {},
                                     sorter.sort(new int[] {}));
    }

    @Test
    void sortForItemInOneElementArray() {
        QuickSort sorter = new QuickSort();
        Assertions.assertArrayEquals(new int[] {5},
                                     sorter.sort(new int[] {5}));
    }

    @Test
    void sortForItemInTwoElementArray() {
        QuickSort sorter = new QuickSort();
        Assertions.assertArrayEquals(new int[] {1, 5},
                                     sorter.sort(new int[] {5, 1}));
        Assertions.assertArrayEquals(new int[] {1, 5},
                                     sorter.sort(new int[] {1, 5}));
    }

    @Test
    void searchForItemInMultipleElementArray() {
        QuickSort sorter = new QuickSort();
        Assertions.assertArrayEquals(new int[] {1, 5, 6, 7, 10},
                                     sorter.sort(new int[] {10, 6, 1, 7, 5}));

        Assertions.assertArrayEquals(new int[] {1, 5, 6, 7, 10},
                                     sorter.sort(new int[] {7, 5, 1, 10, 6}));
    }

    @Test
    void sortList() {
        QuickSort sorter = new QuickSort();
        Assertions.assertEquals(Arrays.asList(1, 5, 6, 7, 10),
                                sorter.sort(Arrays.asList(10, 6, 1, 7, 5)));

        Assertions.assertEquals(Arrays.asList(0, 1, 2, 5, 6, 7, 10),
                sorter.sort(Arrays.asList(10, 2, 6, 1, 7, 0, 5)));
    }
}