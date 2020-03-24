package by.dma;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link SelectionSort}.
 *
 * @author dzmitry.marudau
 * @since 2020.2
 */
class SelectionSortTest {

    @Test
    void shouldNotFailIfArrayIsEmpty() {
        SelectionSort sorter = new SelectionSort();
        Assertions.assertArrayEquals(new int[] {}, sorter.sort(new int[] {}));
    }

    @Test
    void sortForItemInOneElementArray() {
        SelectionSort sorter = new SelectionSort();
        Assertions.assertArrayEquals(new int[] {5}, sorter.sort(new int[] {5}));
    }

    @Test
    void searchForItemInMultipleElementArray() {
        SelectionSort sorter = new SelectionSort();
        Assertions.assertArrayEquals(new int[] {1, 5, 6, 7, 10}, sorter.sort(new int[] {10, 6, 1, 7, 5}));
    }
}