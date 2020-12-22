package by.dma.divideandconquer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test fpr {@code FilerArrays}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class FilerArraysTest {

    FilerArrays service = new FilerArrays();

    @Test
    void shouldNotFailIfArrayIsEmpty() {

        //Assertions.assertEquals(5, service.filter(new int[]{2, 2, 2, 2, 2}, 0, 4, 2));
        Assertions.assertEquals(2, service.filter(new int[]{1, 1, 1, 2, 2}, 1, 3, 1));
        //Assertions.assertEquals(1, service.filter(new int[]{1, 2, 3, 4, 5}, 0, 4, 5));
        Assertions.assertEquals(2, service.filter(new int[]{1, 2, 3, 5, 5}, 0, 5, 5));
        //Assertions.assertEquals(3, service.filter(new int[]{1, 2, 3, 3, 3}, 0, 4, 3));
    }

}
