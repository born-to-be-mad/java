package by.dma.leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for {@link ReverseInteger}.
 *
 * @author dzmitry.marudau
 * @since 2021.1
 */
class ReverseIntegerTest {

    @Test
    public void reverse() {
        ReverseInteger calculator = new ReverseInteger();
        Assertions.assertEquals(321,calculator.reverse(123));
        Assertions.assertEquals(-123,calculator.reverse(-321));
        Assertions.assertEquals(21,calculator.reverse(120));
        Assertions.assertEquals(0,calculator.reverse(0));
        Assertions.assertEquals(301,calculator.reverse(103));
        Assertions.assertEquals(0,calculator.reverse(1534236469));
        Assertions.assertEquals(0,calculator.reverse(-2147483648));
    }
}
