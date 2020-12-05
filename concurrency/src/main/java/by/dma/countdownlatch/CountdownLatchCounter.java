package by.dma.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * See {@code CountDownLatch}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class CountdownLatchCounter {
    private int count;

    public CountdownLatchCounter(int count) {
        this.count = count;
    }

    public boolean callTwiceInSameThread() {
        CountDownLatch countDownLatch = new CountDownLatch(count);
        Thread thread = new Thread(() -> {
            countDownLatch.countDown();
            countDownLatch.countDown();
        });
        thread.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return countDownLatch.getCount() == 0;
    }
}
