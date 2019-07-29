package by.dma.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:06
 * @since : 2019.07
 **/
class ArrayUtilsTest {

    @Test
    void isSortedPrimitiveArray() {
        assertTrue(ArrayUtils.isSorted(new int[]{-5, 0, 1, 3}));
        assertFalse(ArrayUtils.isSorted(new int[]{-5, 4, 1, -2, 3}));

        assertTrue(ArrayUtils.isSorted(new int[]{-5, 0, 1, 3}, 4));
        assertFalse(ArrayUtils.isSorted(new int[]{-5, 4, 1, -2, 3}, 5));
    }
}