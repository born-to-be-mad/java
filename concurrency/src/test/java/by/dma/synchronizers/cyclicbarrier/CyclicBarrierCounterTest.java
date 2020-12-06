package by.dma.synchronizers.cyclicbarrier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 *  Test for {code CyclicBarrierCounter}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
class CyclicBarrierCounterTest {

    @Test
    public void whenCyclicBarrier_notCompleted() {
        CyclicBarrierCounter counter = new CyclicBarrierCounter(2);
        boolean isCompleted = counter.callTwiceInSameThread();
        assertFalse(isCompleted);
    }
}
