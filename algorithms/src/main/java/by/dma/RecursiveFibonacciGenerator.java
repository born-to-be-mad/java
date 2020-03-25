package by.dma;

/**
 * The fibonacci series is a series in which each number is the sum of the previous two numbers.
 * The number at a particular position in the fibonacci series can be obtained using a recursive method.
 *
 * @author dzmitry.marudau
 * @since 2020.2
 */
public class RecursiveFibonacciGenerator {
    public long calculate(long number) {
        if (number < 0) {
            throw new IllegalArgumentException("Only natural numbers allowed");
        }
        if (number == 0 || number == 1) {
            return number;
        }
        return calculate(number - 2) + calculate(number - 1);
    }
}
