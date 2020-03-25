package by.dma;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tets for {@link RecursiveFibonacciGenerator}.
 *
 * @author dzmitry.marudau
 * @since 2020.2
 */
class RecursiveFibonacciGeneratorTest {

    @Test
    void calculate() {
        RecursiveFibonacciGenerator generator = new RecursiveFibonacciGenerator();
        Assertions.assertEquals(0, generator.calculate(0));
        Assertions.assertEquals(1, generator.calculate(1));
        Assertions.assertEquals(1, generator.calculate(2));
        Assertions.assertEquals(2, generator.calculate(3));
        Assertions.assertEquals(3, generator.calculate(4));
        Assertions.assertEquals(13, generator.calculate(7));
        Assertions.assertEquals(144, generator.calculate(12));

    }
}