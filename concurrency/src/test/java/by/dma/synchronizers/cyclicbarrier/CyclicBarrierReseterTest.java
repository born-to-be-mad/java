package by.dma.synchronizers.cyclicbarrier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Test for {code CyclicBarrierReseter}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
class CyclicBarrierReseterTest {
    @Test
    public void whenCyclicBarrier_reset() {
        CyclicBarrierReseter reseter = new CyclicBarrierReseter(7, 20);
        int lineCount = reseter.countWaits();
        assertTrue(lineCount > 7);
    }
}
