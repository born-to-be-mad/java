package by.dma;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * TODO
 *
 * @author dzmitry.marudau
 * @since 2020.2
 */
class BinarySearchTest {

    @Test
    void shouldNotFailIfArrayIsEmpty() {
        BinarySearch search = new BinarySearch();
        Assertions.assertNull(search.binarySearch(new int[] {}, 1));
    }

    @Test
    void searchForItemInOneElementArray() {
        BinarySearch search = new BinarySearch();
        Assertions.assertEquals(0, search.binarySearch(new int[] {5}, 5));
        Assertions.assertNull(search.binarySearch(new int[] {5}, 4));
        Assertions.assertNull(search.binarySearch(new int[] {5}, 6));
    }

    @Test
    void searchForItemInMultipleElementArray() {
        BinarySearch search = new BinarySearch();
        Assertions.assertEquals(5, search.binarySearch(IntStream.range(0,100).toArray(), 5));
        Assertions.assertEquals(105, search.binarySearch(IntStream.range(-100,100).toArray(), 5));
    }
}