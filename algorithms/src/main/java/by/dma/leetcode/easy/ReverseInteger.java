package by.dma.leetcode.easy;

/**
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
 *
 * @author dzmitry.marudau
 * @since 2021.1
 */
public class ReverseInteger {

    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            int digit = x % 10;
            res *= 10;
            res += digit;
            x /= 10;
        }
        if (res > Integer.MAX_VALUE) { res = 0; }
        if (res < Integer.MIN_VALUE) { res = 0; }
        return (int) res;
    }
}
