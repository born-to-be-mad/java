package by.dma.synchronizers.countdownlatch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for {code CountDownLatchReseter}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
class CountDownLatchReseterTest {
    @Test
    public void whenCountDownLatch_noReset() {
        CountDownLatchReseter reseter = new CountDownLatchReseter(7, 20);
        int lineCount = reseter.countWaits();
        assertTrue(lineCount <= 7);
    }
}
