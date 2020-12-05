package by.dma.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * See {@code CountDownLatch}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class CountDownLatchReseter {
    private int count;
    private int threadCount;
    private final AtomicInteger updateCount;

    CountDownLatchReseter(int count, int threadCount) {
        updateCount = new AtomicInteger(0);
        this.count = count;
        this.threadCount = threadCount;
    }

    public int countWaits() {
        CountDownLatch countDownLatch = new CountDownLatch(count);
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                long prevValue = countDownLatch.getCount();
                countDownLatch.countDown();
                if (countDownLatch.getCount() != prevValue) {
                    updateCount.incrementAndGet();
                }
            });
        }

        executorService.shutdown();
        return updateCount.get();
    }
}
