package by.dma.countdownlatch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for {code CountdownLatchCounter}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
class CountdownLatchCounterTest {

    @Test
    public void whenCountDownLatch_completed() {
        CountdownLatchCounter counter = new CountdownLatchCounter(2);
        boolean isCompleted = counter.callTwiceInSameThread();
        assertTrue(isCompleted);
    }
}
